package com.hx.wangchao.Entity

/**
 * 待办任务-本周课程实体类
 */
data class TodoTaskWeeklyEntity(
    val appId: Any,
    val beginTime: String,
    val classId: String,
    val classTitle: String,
    val courseId: String,
    val courseTitle: String,
    val createdBy: Any,
    val createdOn: String,
    val endTime: String,
    val homework: Any,
    val id: String,
    val memo: Any,
    val spaceId: String,
    val spaceTitle: String,
    val status: String,
    val teacherId: String,
    val teacherRealName: String,
    val teacherTel: Any,
    val title: String,
    val updatedBy: Any,
    val updatedOn: String
)