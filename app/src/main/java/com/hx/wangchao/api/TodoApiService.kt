package com.hx.wangchao.api

import com.hx.wangchao.Entity.ActiveRequestParam
import com.hx.wangchao.Entity.TodoListEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

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
}