package com.hx.wangchao.Entity

/**
 * todoList实体类
 */
class TodoListEntity : ArrayList<TodoListEntityItem>()

data class TodoListEntityItem(
    val appId: Any,
    val beginTime: String,
    val classId: String,
    val classTitle: String,
    val courseId: String,
    val courseTitle: String,
    val endTime: String,
    val homework: Any,
    val id: String,
    val leaveCount: Int,
    val regCode: Any,
    val spaceId: String,
    val spaceTitle: String,
    val status: String,
    val teacherId: String,
    val teacherRealName: String,
    val teacherTel: String,
    val tenantId: String,
    val tenantName: Any,
    val title: String
)