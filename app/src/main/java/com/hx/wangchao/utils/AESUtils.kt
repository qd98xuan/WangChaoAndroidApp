package com.hx.wangchao.utils

import android.util.Base64
import com.hx.baselibrary.Constants
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * 登录AES加密工具类
 * 算法AES
 * 模式CFB
 * 密钥：thanks,pig4cloud
 *
 */
class AESUtils {
    private val ALGORITHM = "AES/CFB/NoPadding"
    fun encrypt(pwd: String): String {
        // 将密钥转换为字节数组
        val keyBytes = Constants.KEY_AES.toByteArray(Charsets.ISO_8859_1)

        // 创建密钥规范（AES 需要 16/24/32 字节，这里使用前 16 字节）
        val secretKey = SecretKeySpec(keyBytes. copyOf(16), "AES")

        // IV 使用与密钥相同的值
        val iv = IvParameterSpec(keyBytes.copyOf(16))

        // 初始化加密器
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher. ENCRYPT_MODE, secretKey, iv)

        // 执行加密
        val encrypted = cipher.doFinal(pwd. toByteArray(Charsets.UTF_8))

        // 返回 Base64 编码的结果
        return Base64.encodeToString(encrypted, Base64.NO_WRAP)
    }
}