package com.hx.wangchao.utils

import com.hx.wangchao.application.BaseApplication
import java.io.IOException

/**
 * 从assets中读取json文件
 */
object JsonReader {
    // 从assets文件夹中读取JSON文件并返回其内容
    fun loadJSONFromAsset(fileName: String?): String? {
        var json: String? = null
        try {
            val assetManager = BaseApplication.thisActivity.assets
            val inputStream = assetManager.open(fileName!!)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = buffer.decodeToString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }
}
