package com.hx.wangchao.repository

import com.hx.baselibrary.Constants
import com.hx.baselibrary.network.RetrofitFlowWrapper
import com.hx.wangchao.api.ApiService

/**
 * api的仓库
 */
object ApiRepository {
    private val retrofitFlowWrapper = RetrofitFlowWrapper.getInstance()
    private val iotService =
        retrofitFlowWrapper.create(Constants.BASE_URL_DEBUG_8097, ApiService::class.java)
    private val apiService =
        retrofitFlowWrapper.create(Constants.BASE_URL_DEBUG_8095, ApiService::class.java)

}