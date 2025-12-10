package com.hx.baselibrary.base

import android.content.res.Resources
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
    val densityDpi = Resources.getSystem().displayMetrics.densityDpi
    val scale = densityDpi / 1080f
    return (this.toFloat() * scale).dp
}

/**
 * Sp的拓展方法，可以转换当前控件的分辨率的值
 * convertSpSize(ScreenType.ScreenTypeBig)
 */
fun Number.convertSpSize(): TextUnit {
    val densityDpi = Resources.getSystem().displayMetrics.densityDpi
    val scale = densityDpi / 1080f
    return (this.toFloat() * scale).sp
}


enum class ScreenType {
    ScreenTypeSmall,
    ScreenTypeBig
}

