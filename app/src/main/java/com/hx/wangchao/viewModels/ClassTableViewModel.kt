package com.hx.wangchao.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hx.baselibrary.network.BaseResponse
import com.hx.baselibrary.network.Result
import com.hx.wangchao.Entity.CalendarItem
import com.hx.wangchao.Entity.TodoListEntity
import com.hx.wangchao.Entity.TodoListEntityItem
import com.hx.wangchao.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

/**
 * 课表的ViewModel
 */
class ClassTableViewModel : ViewModel() {
    // 获取7日课程安排
    val lessons = mutableStateListOf<TodoListEntityItem>()
    val lessonState = Channel<BaseResponse<String>>(Channel.BUFFERED)

    // 日历数据
    val timeList = mutableStateListOf<ArrayList<CalendarItem>>()
    fun getWeeklyLessons() {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.getWeeklyLessons().collect {
                when (it) {
                    is Result.Success<*> -> {
                        lessons.clear()
                        lessons.addAll(it.data as ArrayList<TodoListEntityItem> )
                        lessons.groupBy(TodoListEntityItem::period).forEach { timePeriod ->
                            val list = arrayListOf<CalendarItem>()
                            timePeriod.value.forEach { it ->
                                list.add(
                                    CalendarItem(
                                        timePeriod.key,
                                        it.classTitle.get(0).toString(),
                                        daysFromToday(it.beginTime).toInt() - 1
                                    )
                                )
                            }
                            timeList.add(list)
                        }
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

    fun daysFromToday(dateStr: String): Long {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val inputDate = LocalDateTime.parse(dateStr, formatter)
        val now = LocalDateTime.now()
        return ChronoUnit.DAYS.between(inputDate.toLocalDate(), now.toLocalDate())
    }

}