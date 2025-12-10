package com.hx.wangchao.application

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.MutableLiveData
import cn.huidu.toolkit.HuiduTech
import com.hx.baselibrary.Constants
import com.hx.baselibrary.base.BaseActivity
import com.hx.baselibrary.exception.AppCrashHandler
import com.liulishuo.filedownloader.FileDownloader
import com.hx.wangchao.utils.SSLUtils
import com.tencent.mmkv.MMKV

/**
 * 基础的application类
 */
class BaseApplication : Application(), LifecycleOwner {
    val lifecycleRegistry = LifecycleRegistry(this)
    var isStartService = false

    companion object {
        lateinit var thisActivity: BaseActivity
        lateinit var application: BaseApplication

        // 开启服务的坚挺
        var startServiceListener = MutableLiveData<String>()
    }

    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        application = this

        AppCrashHandler.getInstance(applicationContext)
        MMKV.initialize(applicationContext)
        SSLUtils.disableSSLCertificateChecking()
        FileDownloader.setupOnApplicationOnCreate(this)
        FileDownloader.setup(this)
//        LogcatHelper.getInstance(this)?.start()
        // 自动开启服务
        isStartService = true

    }
}