package com.hx.wangchao.api

import com.hx.wangchao.Entity.OSSPresignEntity
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * OSS相关的接口
 */
interface OSSApiService {
    //  第一步：获取预签名上传URL
    @GET("/oss/bucket/object/presigned-put-url")
    fun getPresignedPutUrl(
        @Header("Authorization") authorization: String,
        @Query("objectName") objectName: String
    ): Flow<Response<OSSPresignEntity>>

    // 第二步：获取对象访问 URL
    @Multipart
    @PUT
    fun uploadFileToOSS(
        @Url url: String,
        @Part("file") file: MultipartBody.Part
    ): Flow<Response<Unit>>



}