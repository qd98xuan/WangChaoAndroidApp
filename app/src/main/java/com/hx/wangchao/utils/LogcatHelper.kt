package com.hx.wangchao.utils

import android.content.Context
import android.os.Environment
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader


class LogcatHelper private constructor(context: Context) {
    private var mLogDumper: LogDumper? = null
    private val mPId: Int

    /**
     * 初始化目录
     */
    fun init(context: Context?) {
//        // 优先保存到SD卡中；此处我没有使用这个方式，直接保存到了固定路径download下
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            PATH_LOGCAT = Environment.getExternalStorageDirectory()
//                    .getAbsolutePath() + File.separator + "log";
//        } else {// 如果SD卡不存在，就保存到本应用的目录下
//            PATH_LOGCAT = context.getFilesDir().getAbsolutePath()
//                    + File.separator + "log";
//        }
        val file = File(PATH_LOGCAT)
        if (!file.exists()) {
            file.mkdirs()
        }
    }

    init {
        init(context)
        mPId = android.os.Process.myPid()
    }

    fun start() {
        if (mLogDumper == null) mLogDumper = LogDumper(mPId.toString(), PATH_LOGCAT)
        mLogDumper!!.start()
    }

    fun stop() {
        if (mLogDumper != null) {
            mLogDumper!!.stopLogs()
            mLogDumper = null
        }
    }

    private inner class LogDumper(private val mPID: String, dir: String?) : Thread() {
        private var logcatProc: Process? = null
        private var mReader: BufferedReader? = null
        private var mRunning = true
        var cmds: String? = null
        private var out: FileOutputStream? = null

        init {
            try {
                out = FileOutputStream(
                    File(
                        dir, "log-"
                                + fileName + ".log"
                    )
                )
            } catch (e: FileNotFoundException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }
            /**
             *
             * 日志等级：*:v , *:d , *:w , *:e , *:f , *:s
             *
             * 显示当前mPID程序的 E和W等级的日志.
             *
             */
            // cmds = "logcat *:e *:w | grep \"(" + mPID + ")\"";
            cmds = "logcat  | grep \"($mPID)\"" //打印所有日志信息
            // cmds = "logcat -s way";//打印标签过滤信息
//            cmds = "logcat *:e *:i | grep \"(" + mPID + ")\"";
        }

        fun stopLogs() {
            mRunning = false
        }

        override fun run() {
            saveLogToDir()
        }
        // 保存日志到本地

        private fun saveLogToDir() {
            try {
                logcatProc = Runtime.getRuntime().exec(cmds)
                mReader = BufferedReader(
                    InputStreamReader(
                        logcatProc?.getInputStream()
                    ), 1024
                )
                var line: String? = null
                while (true) {
                    if (!mRunning) {
                        break
                    }
                    if (mRunning && mReader!!.readLine().also { line = it } != null) {
                        if (line!!.length == 0) {
                            continue
                        }
                        if (out != null && line!!.contains(mPID)) {
                            out!!.write(
                                """$line""".toByteArray()
                            )
                        }
                    } else {
                        saveLogToDir()
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                if (logcatProc != null) {
                    logcatProc!!.destroy()
                    logcatProc = null
                }
                if (mReader != null) {
                    try {
                        mReader!!.close()
                        mReader = null
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                if (out != null) {
                    try {
                        out!!.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    out = null
                }
            }
        }
    }

    val fileName: String
        //如果每次都想打印不同日志名称，把下方注释打开
        get() = "new_station_${System.currentTimeMillis()}"


    companion object {
        private var INSTANCE: LogcatHelper? = null
        var PATH_LOGCAT = ""


        fun getInstance(context: Context): LogcatHelper? {
            PATH_LOGCAT =
                context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.path + "/log"
            if (INSTANCE == null) {
                INSTANCE = LogcatHelper(context)
            }
            return INSTANCE
        }
    }
}