package com.hx.wangchao.api

import com.hx.wangchao.Entity.Login
import com.hx.wangchao.Entity.LoginRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT

/**
 * 登录接口
 */
interface PermissionApiService {
    // 登录
    @PUT("/auth/login")
    fun login(
        @Header("Authorization") authorization: String,
        @Body loginRequest: LoginRequest
    ): Flow<Response<Login>>
}