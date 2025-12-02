package com.hx.wangchao.utils

import android.content.Context

object ScreenUtils {
    fun Int.px(context: Context): Int {
        val scale = context.resources.displayMetrics.density
        return (this / scale + 0.5f).toInt()
    }
}