package com.hx.wangchao.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hx.baselibrary.network.BaseResponse
import com.hx.baselibrary.network.Result
import com.hx.wangchao.Entity.TodoListEntity
import com.hx.wangchao.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * 课表的ViewModel
 */
class ClassTableViewModel : ViewModel() {
    // 获取7日课程安排
    val lessons = mutableStateOf<TodoListEntity?>(null)
    val lessonState = Channel<BaseResponse<String>>(Channel.BUFFERED)
    fun getWeeklyLessons() {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.getWeeklyLessons().collect {
                when (it) {
                    is Result.Success<*> -> {
                        lessons.value = it.data as TodoListEntity?
                    }

                    is Result.Loading -> {
                    }

                    is Result.Error -> {
                        lessonState.send(BaseResponse(it.code, null, it.msg))
                    }
                }
            }
        }
    }

}