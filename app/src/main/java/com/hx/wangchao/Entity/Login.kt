package com.hx.wangchao.Entity

/**
 * 登录返回实体类
 */
data class Login(
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String,
    val userInfo: TeacherInfo
)

data class TeacherInfo(
    val avatar: Any,
    val email: Any,
    val id: String,
    val lock: Boolean,
    val phone: String,
    val realname: String,
    val tenantId: String,
    val username: String
)

/**
 * 登录上传的实体类
 */
data class LoginRequest(
    val tenant: String,
    val username: String,
    val password: String
)