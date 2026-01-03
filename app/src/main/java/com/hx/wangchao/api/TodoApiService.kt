package com.hx.wangchao.api

import com.hx.wangchao.Entity.TodoListEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

/**
 * 待办的接口
 */
interface TodoApiService {
    @GET("/todo/lesson/today")
    fun getTodayLessons(
        @Header("Authorization") authorization: String
    ): Flow<Response<TodoListEntity>>
}