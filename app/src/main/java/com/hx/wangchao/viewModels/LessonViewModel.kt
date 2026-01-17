package com.hx.wangchao.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hx.baselibrary.network.Result
import com.hx.wangchao.Entity.AddPerformanceEntity
import com.hx.wangchao.Entity.LessonPerformanceEntity
import com.hx.wangchao.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 课堂相关的ViewModel
 */
class LessonViewModel : ViewModel() {
    // 当前选择的课程id
    var lessonId = ""
    // 课堂表现列表
    val performanceList = mutableStateListOf<LessonPerformanceEntity>()
    fun getLessonPerformanceList() {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.getLessonPerformanceList().collect {
                when (it) {
                    is Result.Success<*> -> {
                        performanceList.clear()
                        performanceList.addAll(it.data as ArrayList<LessonPerformanceEntity>)
                    }

                    is Result.Error -> {

                    }

                    is Result.Loading -> {

                    }
                }
            }
        }
    }

    // 保存课堂表现
    fun addLessonPerformance(data: AddPerformanceEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.addLessonPerformance(data).collect {
                when (it) {
                    is Result.Success<*> -> {

                    }

                    is Result.Error -> {

                    }

                    is Result.Loading -> {
                    }
                }
            }
        }
    }

    // 布置作业
    fun submitHomework(body: Map<String, String>) {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.submitHomework(body).collect {
                when (it) {
                    is Result.Success<*> -> {

                    }

                    is Result.Error -> {

                    }

                    is Result.Loading -> {
                    }
                }
            }
        }
    }

    // 获取作业明细
    val homeworkDetail = mutableStateOf("")
    fun getHomeworkDetail(lessonId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.getHomeworkDetail(lessonId).collect {
                when (it) {
                    is Result.Success<*> -> {
                        homeworkDetail.value = it.data as String
                    }

                    is Result.Error -> {

                    }

                    is Result.Loading -> {
                    }
                }
            }
        }
    }

    // 课堂检测列表
    val classTestPaperList = mutableStateListOf<LessonPerformanceEntity>()
    fun getLessonTestPaperList(lessonId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.getLessonTestPaperList(lessonId).collect {
                when (it) {
                    is Result.Success<*> -> {
                        classTestPaperList.clear()
                        classTestPaperList.addAll(it.data as ArrayList<LessonPerformanceEntity>)
                    }

                    is Result.Error -> {

                    }

                    is Result.Loading -> {
                    }
                }
            }
        }
    }
}