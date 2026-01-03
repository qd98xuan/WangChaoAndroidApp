package com.hx.wangchao.api

import com.hx.wangchao.Entity.TodoListEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

/**
 * 课程表的接口
 */
interface ClassTableApiService {
    @GET("/classes/lesson/weekly")
    fun getWeeklyLessons(
        @Header("Authorization") authorization: String,
    ): Flow<Response<TodoListEntity>>
}