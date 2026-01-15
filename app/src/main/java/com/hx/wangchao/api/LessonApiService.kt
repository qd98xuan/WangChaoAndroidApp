package com.hx.wangchao.api

import com.hx.wangchao.Entity.AddPerformanceEntity
import com.hx.wangchao.Entity.LessonPerformanceEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * 课堂相关的接口
 */
interface LessonApiService {
    // Lesson上课表现列表
    @GET("/lesson/performance/list")
    fun getLessonPerformanceList(
        @Header("Authorization") authorization: String
    ): Flow<Response<ArrayList<LessonPerformanceEntity>>>

    // Lesson上课表现增加
    @POST("/lesson/performance")
    fun addLessonPerformance(
        @Header("Authorization") authorization: String,
        @Body body: AddPerformanceEntity
    ): Flow<Response<String>>

}