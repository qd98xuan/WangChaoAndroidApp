package com.hx.wangchao.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hx.baselibrary.network.BaseResponse
import com.hx.baselibrary.network.Result
import com.hx.wangchao.Entity.ActiveRequestParam
import com.hx.wangchao.Entity.DropdownEntity
import com.hx.wangchao.Entity.TodoListEntity
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
    val activeTeacher = mutableStateOf(DropdownEntity("",""))
    // 激活课程的场地
    val activeSpace = mutableStateOf(DropdownEntity("",""))
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
}