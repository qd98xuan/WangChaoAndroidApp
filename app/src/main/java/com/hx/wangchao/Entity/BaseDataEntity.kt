package com.hx.wangchao.Entity

class TeacherEntity : ArrayList<TeacherEntityItem>()

data class TeacherEntityItem(
    val avatar: Any,
    val email: Any,
    val id: String,
    val isLock: Boolean,
    val phone: String,
    val realname: String,
    val tenantCode: Any,
    val tenantId: String,
    val tenantName: String,
    val username: String
)


class SpaceEntity : ArrayList<SpaceEntityItem>()

data class SpaceEntityItem(
    val appId: Any,
    val area: Int,
    val code: String,
    val context: String,
    val facility: String,
    val id: String,
    val status: String,
    val tenantId: String,
    val title: String
)