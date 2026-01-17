package com.hx.wangchao.Entity

/**
 * 课堂检测实体
 */
data class TestPaperEntity(
    val accountId: Int,
    val accountRealname: String,
    val classId: String,
    val classTitle: String,
    val createdBy: Int,
    val createdOn: String,
    val id: Int,
    val lessonId: Int,
    val lessonTitle: String,
    val memo: String,
    val paperUrl: String,
    val review: String,
    val teacherRealname: String,
    val tenantId: Int,
    val tenantName: String,
    val updatedBy: Int,
    val updatedOn: String
)