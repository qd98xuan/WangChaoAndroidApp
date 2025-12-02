package com.hx.wangchao.utils

import com.blankj.utilcode.util.ConvertUtils

class HexUtils {
    companion object {
        /**
         * 将数据进行分割
         */
        fun spliceData(str: String): ArrayList<String> {
            var i = 0
            var hex = StringBuffer()
            val hexList = arrayListOf<String>()
            str.forEach {
                if (i == 1) {
                    hex.append(it)
                    i = 0
                    hexList.add(hex.toString())
                    hex.setLength(0)
                } else {
                    hex.append(it)
                    i++
                }
            }
            return hexList
        }

        /**
         * 十六进制的低八位和高八位组合成原始的十进制数据
         */
        fun combineHexToDecimal(highByte: String, lowByte: String): Int {
            // 将高八位左移 8 位，然后与低八位进行或操作
            return (highByte.toInt(16) shl 8) or lowByte.toInt(16)
        }
    }
}

/**
 * 获取高八位
 */
fun Int.heigh8(): String {
    var hex = ConvertUtils.int2HexString((this shr 8) and 0xFF)
    if (hex.length == 1) {
        return "0${hex}"
    } else {
        return hex
    }
}

/**
 * 获取低八位
 */
fun Int.low8(): String {
    var hex = ConvertUtils.int2HexString(this and 0xFF)
    if (hex.length == 1) {
        return "0${hex}"
    } else {
        return hex
    }
}

/**
 * int2hexString
 */
fun Int.int2hex(): String {
    val hex = ConvertUtils.int2HexString(this)
    return if (hex.length == 1) {
        "0$hex"
    } else {
        hex
    }
}

/**
 * int2bytearray
 */
fun Int.int2bytearray():ByteArray {
    return ConvertUtils.string2Bytes(ConvertUtils.int2HexString(this))
}

/**
 * hexString2int
 */
fun String.hex2Int():Int {
    return ConvertUtils.hexString2Int(this)
}

/**
 * bytearray2hexString
 */
fun ByteArray.bytearray2hex():String {
    return ConvertUtils.bytes2HexString(this)
}

/**
 * hexString2bytearray
 */
fun String.hex2bytearray():ByteArray {
    return ConvertUtils.hexString2Bytes(this)
}
