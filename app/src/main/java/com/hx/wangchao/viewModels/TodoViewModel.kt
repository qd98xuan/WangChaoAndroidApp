package com.hx.wangchao.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hx.baselibrary.network.BaseResponse
import com.hx.baselibrary.network.Result
import com.hx.wangchao.Entity.ActiveRequestParam
import com.hx.wangchao.Entity.AttendanceList
import com.hx.wangchao.Entity.AttendanceSubmitEntity
import com.hx.wangchao.Entity.DropdownEntity
import com.hx.wangchao.Entity.TodoListEntity
import com.hx.wangchao.activitys.toDoList.RollCall
import com.hx.wangchao.activitys.toDoList.RollCallEntity
import com.hx.wangchao.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * 待办列表，待办事项的ViewModel
 *
 */
class TodoViewModel : ViewModel() {
    // 获取今日课程安排
    private val _lessonState = MutableStateFlow<BaseResponse<TodoListEntity>?>(null)
    val lessonState: StateFlow<BaseResponse<TodoListEntity>?> = _lessonState
    val lessons = mutableStateOf<TodoListEntity?>(null)

    // 激活课程的老师
    val activeTeacher = mutableStateOf(DropdownEntity("", ""))

    // 激活课程的场地
    val activeSpace = mutableStateOf(DropdownEntity("", ""))

    // 激活课程的id
    var activeLessonId = ""

    fun getTodayLessons() {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.getTodayLessons().collect {
                val response = when (it) {
                    is Result.Success<*> -> {
                        lessons.value = it.data as TodoListEntity?
                        BaseResponse(200, it.data, "获取今日课程安排成功")
                    }

                    is Result.Loading -> BaseResponse(-1, null, "加载中")
                    is Result.Error -> BaseResponse(it.code, null, it.msg)
                }
                _lessonState.value = response as BaseResponse<TodoListEntity>?
            }
        }
    }

    // 激活课程
    private val _activateLessonState = MutableStateFlow<BaseResponse<String>?>(null)
    val activateLessonState: StateFlow<BaseResponse<String>?> = _activateLessonState
    fun activateLesson() {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.activateLesson(
                ActiveRequestParam(
                    activeLessonId,
                    activeTeacher.value.key,
                    activeSpace.value.key
                )
            ).collect {
                val response = when (it) {
                    is Result.Success<*> -> {
                        BaseResponse(200, "激活课程成功", "激活课程成功")
                    }

                    is Result.Loading -> BaseResponse(-1, null, "加载中")
                    is Result.Error -> BaseResponse(it.code, null, it.msg)
                }
                _activateLessonState.value = response as BaseResponse<String>?
            }
        }
    }

    // 推迟课程
    private val _postponeLessonState = MutableStateFlow<BaseResponse<String>?>(null)
    val postponeLessonState: StateFlow<BaseResponse<String>?> = _postponeLessonState
    fun postponeLesson(memo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.postponeLesson(
                mapOf(
                    Pair("lessonId", activeLessonId),
                    Pair("memo", memo)
                )
            ).collect {
                val response = when (it) {
                    is Result.Success<*> -> {
                        BaseResponse(200, "推迟课程成功", "推迟课程成功")
                    }

                    is Result.Loading -> BaseResponse(-1, null, "加载中")
                    is Result.Error -> BaseResponse(it.code, null, it.msg)
                }
                _postponeLessonState.value = response as BaseResponse<String>?
            }
        }
    }

    // 状态完成
    private val _completeLessonState = MutableStateFlow<BaseResponse<String>?>(null)
    val completeLessonState: StateFlow<BaseResponse<String>?> = _completeLessonState
    fun completeLesson() {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.completeLesson(
                mapOf(
                    Pair("lessonId", activeLessonId)
                )
            ).collect {
                val response = when (it) {
                    is Result.Success<*> -> {
                        BaseResponse(200, "完成课程成功", "完成课程成功")
                    }

                    is Result.Loading -> BaseResponse(-1, null, "加载中")
                    is Result.Error -> BaseResponse(it.code, null, it.msg)
                }
                _completeLessonState.value = response as BaseResponse<String>?
            }
        }
    }

    // 出勤点名列表
    private val _attendanceListState = MutableStateFlow<BaseResponse<AttendanceList>?>(null)
    val attendanceListState: StateFlow<BaseResponse<AttendanceList>?> = _attendanceListState
    val rollCallList = mutableStateListOf<RollCallEntity>()
    fun getAttendanceList() {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.getAttendanceList(
                mapOf(
                    Pair("lessonId", activeLessonId)
                )
            ).collect {
                val response = when (it) {
                    is Result.Success<*> -> {
                        (it.data as AttendanceList).forEach { student ->
                            rollCallList.add(RollCallEntity(name = student.accountRealName, accountId = student.accountId, isSelectedRollCall = when(student.status) {
                                "NORMAL" -> RollCall.CHUQIN
                                "LEAVE" -> RollCall.CHIDAO
                                "MAKEUP" -> RollCall.BUKE
                                "ABSENCE" -> RollCall.QUEQIN
                                "" -> RollCall.QINGJIA
                                else -> RollCall.CHUQIN
                            }))
                        }
                        BaseResponse(200, it.data, "获取出勤点名列表成功")
                    }

                    is Result.Loading -> BaseResponse(-1, null, "加载中")
                    is Result.Error -> BaseResponse(it.code, null, it.msg)
                }
                _attendanceListState.value = response as BaseResponse<AttendanceList>?
            }
        }
    }

    // 出勤点名
    private val _attendanceState = MutableStateFlow<BaseResponse<String>?>(null)
    val attendanceState: StateFlow<BaseResponse<String>?> = _attendanceState
    fun attendance(data:AttendanceSubmitEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.callAttendance(
                data
            ).collect {
                val response = when (it) {
                    is Result.Success<*> -> {
                        BaseResponse(200, "出勤点名成功", "出勤点名成功")
                    }

                    is Result.Loading -> BaseResponse(-1, null, "加载中")
                    is Result.Error -> BaseResponse(it.code, null, it.msg)
                }
                _attendanceState.value = response as BaseResponse<String>?
            }
        }
    }
}