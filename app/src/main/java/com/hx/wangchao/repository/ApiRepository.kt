package com.hx.wangchao.repository

import com.hx.baselibrary.Constants
import com.hx.baselibrary.network.RetrofitFlowWrapper
import com.hx.wangchao.api.ApiService

/**
 * api的仓库
 */
object ApiRepository {
    private val retrofitFlowWrapper = RetrofitFlowWrapper.getInstance()
    private val apiService =
        retrofitFlowWrapper.create(Constants.BASE_URL, ApiService::class.java)

    suspend fun login(
        tenant: String,
        username: String,
        password: String,
    ) = retrofitFlowWrapper.makeApiRequest(apiService.login(
        "22d6d05b-f9fb-4dec-a8ee-363a29d6cdbc",
        tenant,
        username,
        password,
    ))


}