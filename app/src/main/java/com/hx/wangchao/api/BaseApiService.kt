package com.hx.wangchao.api

import com.hx.wangchao.Entity.SpaceEntity
import com.hx.wangchao.Entity.TeacherEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * 基础的接口服务
 */
interface BaseApiService {
    // 获取教师列表
    @GET("/teacher/list")
    fun getTeacherList(
        @Header("Authorization") authorization: String,
    ): Flow<Response<TeacherEntity>>

    // 获取场地列表
    @GET("/space/list")
    fun getSpaceList(
        @Header("Authorization") authorization: String,
    ): Flow<Response<SpaceEntity>>
}