package com.hx.wangchao.Entity

/**
 * 出勤点名列表实体类
 */
class AttendanceList : ArrayList<AttendanceListItem>()

data class AttendanceListItem(
    val accountId: String,
    val accountRealName: String,
    val classTitle: String,
    val createdBy: String,
    val createdOn: String,
    val id: String,
    val lessonId: String,
    val lessonTitle: String,
    val status: String,
    val tenantId: Any,
    val tenantName: Any,
    val updatedBy: Any,
    val updatedOn: Any
)

/**
 * 出勤点名提交实体类
 */
data class AttendanceSubmitEntity(
    val lessonId: String,
    val accountId: String,
    val status: String
)