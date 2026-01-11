package com.hx.wangchao.Entity

/**
 * 日历图的实体类
 */
data class CalendarItem(
    // 名称
    val name: String,
    // 索引在哪个位置就放置到哪里0-6
    val index: Int,
    // 是否已经推迟
    val isDelay: Boolean = false
)