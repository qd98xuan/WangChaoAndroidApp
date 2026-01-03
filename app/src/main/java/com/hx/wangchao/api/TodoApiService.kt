package com.hx.wangchao.api

import com.hx.wangchao.Entity.TodoListEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

/**
 * 待办的接口
 */
interface TodoApiService {
    @GET("/todo/lesson/today")
    fun getTodayLessons(
        @Header("Authorization") authorization: String,
    ): Flow<Response<TodoListEntity>>

    @PUT("/lesson/active")
    fun activateLesson(
        @Header("Authorization") authorization: String,
        @Field("lessonId") lessonId: String,
        @Field("teacherId") teacherId: String,
        @Field("spaceId") spaceId: String,
    ): Flow<Response<Unit>>
}