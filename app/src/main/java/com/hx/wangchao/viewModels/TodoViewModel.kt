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
import kotlinx.coroutines.channels.Channel
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
    val lessonState = Channel<BaseResponse<String>>(Channel.BUFFERED)
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
                when (it) {
                    is Result.Success<*> -> {
                        lessons.value = it.data as TodoListEntity?
                        lessonState.send(BaseResponse(200, "", "获取今日课程安排成功"))
                    }

                    is Result.Loading -> {}
                    is Result.Error -> {
                        lessonState.send(BaseResponse(it.code, "", it.msg))
                    }
                }
            }
        }
    }

    // 激活课程
    val activateLessonState = Channel<BaseResponse<String>>(Channel.BUFFERED)

    fun activateLesson() {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.activateLesson(
                ActiveRequestParam(
                    activeLessonId,
                    activeTeacher.value.key,
                    activeSpace.value.key
                )
            ).collect {
                when (it) {
                    is Result.Success<*> -> {
                        activateLessonState.send(BaseResponse(200, "激活课程成功", "激活课程成功"))
                    }

                    is Result.Loading -> {}
                    is Result.Error -> {
                        activateLessonState.send(BaseResponse(it.code, "", it.msg))
                    }
                }
            }
        }
    }

    // 推迟课程
    val postponeLessonState = Channel<BaseResponse<String>>(Channel.BUFFERED)
    fun postponeLesson(memo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.postponeLesson(
                mapOf(
                    Pair("lessonId", activeLessonId),
                    Pair("memo", memo)
                )
            ).collect {
                when (it) {
                    is Result.Success<*> -> {
                        postponeLessonState.send(BaseResponse(200, "推迟课程成功", "推迟课程成功"))
                    }

                    is Result.Loading -> {}
                    is Result.Error -> {
                        postponeLessonState.send(BaseResponse(it.code, null, it.msg))
                    }
                }
            }
        }
    }

    // 状态完成
    val completeLessonState = Channel<BaseResponse<String>>(Channel.BUFFERED)
    fun completeLesson() {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.completeLesson(
                mapOf(
                    Pair("lessonId", activeLessonId)
                )
            ).collect {
                when (it) {
                    is Result.Success<*> -> {
                        completeLessonState.send(BaseResponse(200, "完成课程成功", "完成课程成功"))
                    }

                    is Result.Loading -> {}
                    is Result.Error -> completeLessonState.send(BaseResponse(it.code, null, it.msg))
                }
            }
        }
    }

    // 出勤点名列表
    val attendanceListState = Channel<BaseResponse<String>>(Channel.BUFFERED)
    val rollCallList = mutableStateListOf<RollCallEntity>()
    fun getAttendanceList() {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.getAttendanceList(
                mapOf(
                    Pair("lessonId", activeLessonId)
                )
            ).collect {
                when (it) {
                    is Result.Success<*> -> {
                        rollCallList.clear()
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
                        attendanceListState.send(BaseResponse(200, "", "获取出勤点名列表成功"))
                    }

                    is Result.Loading -> {}
                    is Result.Error -> {
                        attendanceListState.send(BaseResponse(it.code, "", it.msg))
                    }
                }
            }
        }
    }

    // 出勤点名
    val attendanceState = Channel<BaseResponse<String>>(Channel.BUFFERED)
    fun attendance(data:AttendanceSubmitEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.callAttendance(
                data
            ).collect {
                when (it) {
                    is Result.Success<*> -> {
                        attendanceState.send(BaseResponse(200, "出勤点名成功", "出勤点名成功"))
                    }

                    is Result.Loading -> {}
                    is Result.Error -> {
                        attendanceState.send(BaseResponse(it.code, null, it.msg))
                    }
                }
            }
        }
    }
}