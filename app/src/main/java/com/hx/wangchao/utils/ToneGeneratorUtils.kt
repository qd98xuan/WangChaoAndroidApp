package com.hx.wangchao.utils

import android.media.AudioManager
import android.media.ToneGenerator

/**
 * 播放声音的工具类
 */
class ToneGeneratorUtils {
    companion object {
        private lateinit var toneGenerator: ToneGenerator
        private lateinit var toneGeneratorUtils: ToneGeneratorUtils

        // 初始化
        fun getInstance(): ToneGeneratorUtils {
            if (!this::toneGeneratorUtils.isInitialized) {
                toneGeneratorUtils = ToneGeneratorUtils()
            }
            if (!this::toneGenerator.isInitialized) {
                toneGenerator = ToneGenerator(AudioManager.STREAM_SYSTEM, ToneGenerator.MAX_VOLUME)
            }
            return toneGeneratorUtils
        }
    }

    // 播放滴声
    fun playDi() {
        toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP)
    }
}