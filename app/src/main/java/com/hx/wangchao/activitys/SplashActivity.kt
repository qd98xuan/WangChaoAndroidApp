package com.hx.wangchao.activitys

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ToastUtils
import com.hx.baselibrary.base.convertSize
import com.hx.wangchao.R
import com.hx.wangchao.viewModels.SplashViewModel
import com.hx.wangchao.viewModels.TodoViewModel
import kotlinx.coroutines.delay

/**
 * 启动页
 */
class SplashActivity : BaseAppActivity() {
    val splashViewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }
    val todoViewMode by lazy {
        ViewModelProvider(this).get(TodoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(true) {
                todoViewMode.getTodayLessons()
            }
            val lessonState by todoViewMode.lessonState.collectAsState()
            LaunchedEffect(lessonState) {
                if (lessonState?.code == 200) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                } else if (lessonState?.code == 401) {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                } else {
                    ToastUtils.showShort(lessonState?.message)
                }
            }
            Box(
                modifier = Modifier
                    .paint(
                        painterResource(R.mipmap.splash_bg),
                        contentScale = ContentScale.FillBounds
                    )
                    .fillMaxSize()
            ) {
                Image(
                    painterResource(R.drawable.splash_text), contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = (LocalConfiguration.current.screenHeightDp / 3).dp)
                        .width(852.convertSize())
                )


            }


        }
    }
}