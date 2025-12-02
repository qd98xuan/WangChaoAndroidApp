package com.hx.baselibrary.ui

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.hx.baselibrary.R
import com.hx.baselibrary.base.BaseActivity
import com.hx.baselibrary.databinding.ActivityWebviewBinding

/**
 *  webviewActivity
 */
class WebViewActivity : BaseActivity() {
    lateinit var binding: ActivityWebviewBinding
    var url = ""
    var title = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_webview)
        url = intent.getStringExtra("url") ?: ""
        title = intent.getStringExtra("title") ?: ""
        binding.tvTitle.text = title
        binding.webview.settings.apply {
            javaScriptEnabled = true
        }
        binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {

            }
        }
        binding.webview.loadUrl(url)
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}