package com.hx.wangchao.Entity

/**
 * 课堂表现实体类
 */
data class LessonPerformanceEntity(
    val accountId: String,
    val accountRealname: String,
    val classTitle: String,
    val createdBy: Any,
    val createdOn: String,
    val id: String,
    val lessonId: String,
    val lessonTitle: String,
    val memo: Any,
    val review: String,
    val teacherRealname: String,
    val updatedBy: Any,
    val updatedOn: Any
)

/**
 * 添加课堂表现实体类
 */
data class AddPerformanceEntity(
    val lessonId: String,
    val accountId: String,
    val review: String,
)