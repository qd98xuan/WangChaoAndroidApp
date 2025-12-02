package com.hx.wangchao.utils

import java.util.concurrent.TimeUnit

object TimeConvertUtils {
    // 把秒转换成00分00秒
    fun convertSecondTo00M00S(second: Int): String {
        var second = second
        val minute = second / 60
        second = second % 60
        return "${minute}分${second}秒"
    }

    // 把秒转换成00:00
    fun convertSecondTo00_00(second: Int): String {
        var second = second
        var minute = (second / 60).toString()
        if (minute.length == 1) {
            minute = "0$minute"
        }
        second = second % 60
        var secondS = second.toString()
        if (secondS.length == 1) {
            secondS = "0$secondS"
        }
        return "${minute}:${secondS}"
    }

    // 把秒转换成00:00:00
    fun convertSecondTo00_00_00(second: Long): String {
        var second = second
        var hour = (second / 3600).toString()
        if (hour.length == 1) {
            hour = "0${hour}"
        }
        second %= 3600
        var minute = (second / 60).toString()
        if (minute.length == 1) {
            minute = "0$minute"
        }
        second %= 60
        var secondS = second.toString()
        if (secondS.length == 1) {
            secondS = "0$secondS"
        }

        return "${hour}:${minute}:${secondS}"
    }

    fun formatSecondsToHMS(seconds: Long): String {
        val hours = TimeUnit.SECONDS.toHours(seconds)
        val minutes = TimeUnit.SECONDS.toMinutes(seconds) % 60
        val secs = seconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, secs)
    }
}