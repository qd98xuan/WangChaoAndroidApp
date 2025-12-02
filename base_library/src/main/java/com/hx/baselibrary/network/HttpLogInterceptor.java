package com.hx.baselibrary.network;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.hx.baselibrary.utils.LogJsonUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpLogInterceptor implements HttpLoggingInterceptor.Logger {

    private StringBuilder mMessage = new StringBuilder();
    @Override
    public void log(String message) {
        // 请求或者响应开始
        if (message.startsWith("--> POST") || message.startsWith("--> GET")) {
            mMessage.setLength(0);
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if ((message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"))) {
            message = LogJsonUtil.formatJson(LogJsonUtil.decodeUnicode(message));
        }
        mMessage.append(message.concat("\n"));
        // 请求或者响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            LogUtils.d(mMessage.toString());
        }
    }
}
