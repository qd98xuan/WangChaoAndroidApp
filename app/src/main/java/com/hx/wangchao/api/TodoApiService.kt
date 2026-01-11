package com.hx.wangchao.api

import com.hx.wangchao.Entity.ActiveRequestParam
import com.hx.wangchao.Entity.AttendanceList
import com.hx.wangchao.Entity.AttendanceSubmitEntity
import com.hx.wangchao.Entity.TodoListEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.QueryMap

/**
 * 待办的接口
 */
interface TodoApiService {
    // 获取今日课程安排
    @GET("/todo/lesson/today")
    fun getTodayLessons(
        @Header("Authorization") authorization: String,
    ): Flow<Response<TodoListEntity>>

    // 激活课程
    @PUT("/lesson/active")
    fun activateLesson(
        @Header("Authorization") authorization: String,
        @Body param: ActiveRequestParam
    ): Flow<Response<Unit>>

    // 推迟课程
    @PUT("/lesson/postponed")
    fun postponeLesson(
        @Header("Authorization") authorization: String,
        @QueryMap params: Map<String, String>
    ): Flow<Response<Unit>>

    // 状态完成
    @PUT("/lesson/completed")
    fun completeLesson(
        @Header("Authorization") authorization: String,
        @QueryMap params: Map<String, String>
    ): Flow<Response<Unit>>

    // 出勤点名列表
    @GET("/lesson/attendance/list")
    fun getAttendanceList(
        @Header("Authorization") authorization: String,
        @QueryMap params: Map<String, String>
    ): Flow<Response<AttendanceList>>

    // 出勤点名
    @PUT("/lesson/attendance/call")
    fun callAttendance(
        @Header("Authorization") authorization: String,
        @Body data: ArrayList<AttendanceSubmitEntity>
    ): Flow<Response<Unit>>
}