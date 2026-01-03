package com.hx.wangchao.repository

import com.hx.baselibrary.Constants
import com.hx.baselibrary.mmkv.MMKVUtils
import com.hx.baselibrary.network.RetrofitFlowWrapper
import com.hx.wangchao.Entity.LoginRequest
import com.hx.wangchao.api.PermissionApiService
import com.hx.wangchao.api.TodoApiService
import retrofit2.http.Header

/**
 * api的仓库
 */
object ApiRepository {
    private val retrofitFlowWrapper = RetrofitFlowWrapper.getInstance()
    private val permissionApiService =
        retrofitFlowWrapper.create(Constants.BASE_URL, PermissionApiService::class.java)

    private val todoApiService =
        retrofitFlowWrapper.create(Constants.BASE_URL, TodoApiService::class.java)

    // 登录
    suspend fun login(
        loginRequest: LoginRequest
    ) = retrofitFlowWrapper.makeApiRequest(
        permissionApiService.login(
            "22d6d05b-f9fb-4dec-a8ee-363a29d6cdbc",
            loginRequest
        )
    )

    // 获取今日课程安排
    suspend fun getTodayLessons() =
        retrofitFlowWrapper.makeApiRequest(
            todoApiService.getTodayLessons(
                MMKVUtils.getString(
                    Constants.KEY_TOKEN
                )?:""
            )
        )


}