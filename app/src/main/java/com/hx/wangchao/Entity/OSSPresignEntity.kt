package com.hx.wangchao.Entity

/**
 * OSS预签名实体类
 */
data class OSSPresignEntity(
    val getUrl: String,
    val objectName: String,
    val presignedPutUrl: String
)