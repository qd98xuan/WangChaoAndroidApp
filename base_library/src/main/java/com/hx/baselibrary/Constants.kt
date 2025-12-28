package com.hx.baselibrary

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.hx.baselibrary.BuildConfig.DEBUG
import com.hx.baselibrary.mmkv.MMKVUtils

/**
 *  一些常量
 */
class Constants {
    companion object {
        // 域名的

        // 测试环境地址
        private const val BASE_URL_DEBUG = "https://octopus-app.3ilink.cn"

        // 线上环境地址
        private const val BASE_URL_RELEASE = "http://prod-cn.your-api-server.com"

        var BASE_URL = if (DEBUG) BASE_URL_DEBUG else BASE_URL_RELEASE

        // 用户token
        const val KEY_TOKEN = "KEY_TOKEN"

        const val KEY_REFRESH_TOKEN = "KEY_REFRESH_TOKEN"

        const val KEY_AES = "thanks,pig4cloud"

        const val KEY_USER_INFO = "KEY_USER_INFO"

        fun getUserToken() =
            "Bearer ${MMKVUtils.getString(KEY_TOKEN) ?: ""}"

        // 清除数据
        fun clearData() {
            MMKVUtils.put(KEY_TOKEN, "")
        }


        // 字体
        val BEBAS = FontFamily(Font(R.font.bebas))
        val BOLD = FontFamily(Font(R.font.bold))
        val MEDIUM = FontFamily(Font(R.font.medium))
        val REGULAR = FontFamily(Font(R.font.regular))
        val SCORE = FontFamily(Font(R.font.score))
        val SEMIBOLD = FontFamily(Font(R.font.semibold))
        val BEBAS_NEUE = FontFamily(Font(R.font.bebas_neue))
    }
}