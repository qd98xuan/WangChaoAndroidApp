package com.hx.wangchao.utils

import java.security.SecureRandom
import java.util.Base64
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
    fun encrypt(data: String, key: String): String {
        val keyBytes = ByteArray(16)
        val kb = key.toByteArray(Charsets.UTF_8)
        System.arraycopy(kb, 0, keyBytes, 0, Math.min(kb.size, keyBytes.size))
        val secretKey = SecretKeySpec(keyBytes, "AES")

        // 使用固定的 IV，例如全 0，或者由 key 派生
        val iv = ByteArray(16) // 默认全为 0
        val ivSpec = IvParameterSpec(iv)

        val cipher = Cipher.getInstance("AES/CFB/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec)
        val encrypted = cipher.doFinal(data.toByteArray(Charsets.UTF_8))

        // 不需要再拼接 IV 了，因为 IV 是固定的
        return android.util.Base64.encodeToString(encrypted, android.util.Base64.NO_WRAP)
    }
}