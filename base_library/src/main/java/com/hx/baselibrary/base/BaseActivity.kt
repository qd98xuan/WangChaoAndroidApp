package com.hx.baselibrary.base

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.hx.baselibrary.base.ScreenType
import com.hx.baselibrary.base.convertSize
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 基础的activity
 */
open class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

/**
 * Dp的拓展方法，可以转换当前控件的分辨率的值
 * convertSize(ScreenType.ScreenTypeBig)
 */
fun Number.convertSize(): Dp {
    return when (ScreenUtils.getAppScreenWidth()) {
        1280 -> {
            this.toInt().dp
        }

        1920 -> {
            (this.toInt() * 1.4).dp
        }

        480 -> {
            (this.toInt() * 0.37).dp
        }

        else -> {
            this.toInt().dp
        }
    }
//    when (screenType) {
//        ScreenType.ScreenTypeSmall -> {
//            return this.toInt().dp
//        }
//
//        ScreenType.ScreenTypeBig -> {
//            return (this.toInt() * 1.4).dp
//        }
//    }
}

/**
 * Sp的拓展方法，可以转换当前控件的分辨率的值
 * convertSpSize(ScreenType.ScreenTypeBig)
 */
fun Number.convertSpSize(): TextUnit {
    return when (ScreenUtils.getAppScreenWidth()) {
        1280 -> {
            this.toInt().sp
        }

        1920 -> {
            (this.toInt() * 1.4).sp
        }

        480 -> {
            (this.toInt() * 0.37).sp
        }

        else -> {
            this.toInt().sp
        }
    }
//    when(screenType) {
//        ScreenType.ScreenTypeSmall->{
//            return this.toInt().sp
//        }
//        ScreenType.ScreenTypeBig->{
//            return (this.toInt()*1.4).sp
//        }
//    }
}


enum class ScreenType {
    ScreenTypeSmall,
    ScreenTypeBig
}

