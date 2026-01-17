package com.hx.wangchao.api

import com.hx.wangchao.Entity.AddPerformanceEntity
import com.hx.wangchao.Entity.LessonPerformanceEntity
import com.hx.wangchao.Entity.TestPaperEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query

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

    // 布置作业
    @PUT("/lesson/homework/submit")
    fun submitHomework(
        @Header("Authorization") authorization: String,
        @Body body: Map<String, String>
    ): Flow<Response<String>>

    // 获取作业明细
    @GET("/todo/homework/detail")
    fun getHomeworkDetail(
        @Header("Authorization") authorization: String,
        @Query ("lessonId") lessonId: String
    ): Flow<Response<String>>

    // 课堂检测列表
    @GET("/lesson/test-paper/list")
    fun getLessonTestPaperList(
        @Header("Authorization") authorization: String,
        @Query ("lessonId") lessonId: String
    ): Flow<Response<ArrayList<TestPaperEntity>>>


}