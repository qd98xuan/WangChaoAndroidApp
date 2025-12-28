package com.hx.wangchao.api

import com.hx.baselibrary.network.BaseResponse
import com.hx.wangchao.Entity.Login
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Query

/**
 * 接口
 */
interface ApiService {

    // 登录
    @PUT("/auth/login")
    fun login(
        @Header("Authorization") authorization: String,
        @Query("tenant")tenant: String,
        @Query("username")username: String,
        @Query("password")password: String,
    ): Flow<Response<BaseResponse<Login>>>

}