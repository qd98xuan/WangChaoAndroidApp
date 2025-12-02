package com.hx.baselibrary.network

import com.blankj.utilcode.util.LogUtils
import com.coder.vincent.sharp_retrofit.call_adapter.flow.FlowCallAdapterFactory
import com.hx.baselibrary.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

/**
 *  Retrofit+Flow 请求封装
 */
class RetrofitFlowWrapper private constructor() {
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(2L, TimeUnit.SECONDS)
        .addNetworkInterceptor(
            HttpLoggingInterceptor(HttpLogInterceptor()).setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        )
        .addInterceptor(ResponseCodeInterceptor())
        .build()

    private fun retrofit(baseUrl:String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(FlowCallAdapterFactory.create())
        .client(okHttpClient)
        .build()


    companion object {
        private var instance: RetrofitFlowWrapper? = null

        @Synchronized
        fun getInstance(): RetrofitFlowWrapper {
            if (instance == null) {
                instance = RetrofitFlowWrapper()
            }
            return instance!!
        }
    }

    fun <T> create(baseUrl:String,service: Class<T>): T {
        return retrofit(baseUrl).create(service)
    }

    suspend fun <T> makeApiRequest(flow: Flow<Response<BaseResponse<T>>>) = flow {
        emit(Result.Loading)
        try {
            val response = flow.first()
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.code == 10000) {
                    emit(Result.Success(body.data))
                } else {
                    emit(Result.Error(body?.message ?: ""))
                }
            } else {
                emit(Result.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.toString()))
        }
    }
}

data class BaseResponse<T>(
    val code: Int,
    val data: T,
    val message: String
)

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val msg: String) : Result<Nothing>()
}