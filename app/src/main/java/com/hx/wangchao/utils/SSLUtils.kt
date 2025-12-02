package com.hx.wangchao.utils

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object SSLUtils {

    fun disableSSLCertificateChecking() {
        try {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate>? {
                    return null
                }

                override fun checkClientTrusted(certs: Array<java.security.cert.X509Certificate>, authType: String) {
                    // Do nothing, just accept all
                }

                override fun checkServerTrusted(certs: Array<java.security.cert.X509Certificate>, authType: String) {
                    // Do nothing, just accept all
                }
            })

            val sc = SSLContext.getInstance("TLS")
            sc.init(null, trustAllCerts, java.security.SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)

            val allHostsValid = HostnameVerifier { _, _ -> true }
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
