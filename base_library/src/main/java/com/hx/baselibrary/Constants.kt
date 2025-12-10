package com.hx.baselibrary

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.blankj.utilcode.util.DeviceUtils
import com.google.gson.Gson
import com.hx.baselibrary.mmkv.MMKVUtils

/**
 *  一些常量
 */
class Constants {
    companion object {
        // 域名的
        private const val BASE_URL_DEBUG = "http://health.triplemaster.com"

        const val BASE_URL_DEBUG_8097 = BASE_URL_DEBUG
        const val BASE_URL_DEBUG_8095 = BASE_URL_DEBUG

        // 用户token
        const val KEY_TOKEN = "KEY_TOKEN"

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