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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hx.baselibrary.base.convertSize
import com.hx.wangchao.R
import kotlinx.coroutines.delay

/**
 * 启动页
 */
class SplashActivity : BaseAppActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(true) {
                delay(3000)
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
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