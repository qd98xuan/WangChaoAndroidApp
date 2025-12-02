package com.hx.baselibrary.network

import com.hx.baselibrary.Constants
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 拦截返回码
 */
class ResponseCodeInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val proceed = chain.proceed(request)
        when (proceed.code()) {
            // 如果是401那么就是token失效，那么跳转登录页面
            401 -> {
                Constants.clearData()
            }
        }
        return proceed
    }
}