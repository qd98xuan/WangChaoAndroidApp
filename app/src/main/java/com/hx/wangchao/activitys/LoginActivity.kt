package com.hx.wangchao.activitys

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ToastUtils
import com.hx.baselibrary.base.convertSize
import com.hx.baselibrary.base.convertSpSize
import com.hx.wangchao.R
import com.hx.wangchao.activitys.register.PartOne
import com.hx.wangchao.activitys.register.PartThree
import com.hx.wangchao.activitys.register.PartTwo
import com.hx.wangchao.ui.theme.c_047B83
import com.hx.wangchao.ui.theme.c_333333
import com.hx.wangchao.ui.theme.c_999999
import com.hx.wangchao.ui.theme.c_9BCACD
import com.hx.wangchao.ui.theme.c_C1DFE1
import com.hx.wangchao.viewModels.LoginViewmodel

/**
 * 登录页
 */
class LoginActivity : BaseAppActivity() {
    val loginViewmodel by lazy { ViewModelProvider(this)[LoginViewmodel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginSuccess by remember {
                loginViewmodel.loginSuccess
            }
            LaunchedEffect(loginSuccess) {
                if (loginSuccess) {
                    // 登录成功，跳转主页面
                    ToastUtils.showShort("登录成功")
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(R.mipmap.splash_bg),
                        contentScale = ContentScale.FillBounds
                    )
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painterResource(R.drawable.me),
                        contentDescription = null,
                        modifier = Modifier
                            .align(
                                Alignment.TopStart
                            )
                            .padding(start = 63.convertSize(), top = 251.convertSize())
                            .size(230.convertSize())
                    )
                    Text(
                        "望潮教培助手",
                        fontSize = 58.convertSpSize(),
                        color = Color.White,
                        modifier = Modifier
                            .align(
                                Alignment.TopStart
                            )
                            .padding(start = 357.convertSize(), top = 271.convertSize())
                    )
                    Text(
                        "八手九脑，辅助您方便快捷高质量完成教学任务",
                        fontSize = 35.convertSpSize(),
                        color = Color.White,
                        modifier = Modifier
                            .align(
                                Alignment.TopStart
                            )
                            .padding(start = 357.convertSize(), top = 363.convertSize())
                            .width(588.convertSize())
                    )
                }

                LoginBox(
                    modifier = Modifier.padding(
                        top = 101.convertSize(),
                        start = 35.convertSize(),
                        end = 35.convertSize()
                    ),
                    loginViewmodel
                )
            }
        }
    }
}

@Composable
fun LoginBox(modifier: Modifier, loginViewmodel: LoginViewmodel) {
    // 模式是注册还是登录
    var mode by remember {
        mutableStateOf("登录")
    }
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .paint(
                    painterResource(if (mode == "登录") R.drawable.login_title else R.drawable.register_title),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(154.convertSize())
                    .clickable {
                        mode = "登录"
                    })
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(154.convertSize())
                    .clickable {
                        mode = "注册"
                    })
        }
        if (mode == "登录") {
            LoginView(modifier = Modifier, loginViewmodel)
        } else {
            RegisterView(modifier = Modifier)
        }
    }
}

// 登录视图
@Composable
fun LoginView(modifier: Modifier, loginViewmodel: LoginViewmodel) {
    var tenant by remember {
        loginViewmodel.tenant
    }
    var username by remember {
        loginViewmodel.username
    }
    var password by remember {
        loginViewmodel.password
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(arrayListOf(c_C1DFE1, Color.White)),
                shape = RoundedCornerShape(
                    bottomEnd = 29.convertSize(),
                    bottomStart = 29.convertSize()
                )
            )
    ) {
        Text(
            "机构编码",
            fontSize = 40.convertSpSize(),
            color = c_333333,
            modifier = Modifier.padding(start = 40.convertSize())
        )
        TextFieldMain(
            modifier = Modifier.padding(
                start = 35.convertSize(),
                top = 12.convertSize(),
                end = 35.convertSize()
            ),
            "如:WC-2025-001",
            tenant
        ) {
            tenant = it
        }
        Text(
            "手机号",
            fontSize = 40.convertSpSize(),
            color = c_333333,
            modifier = Modifier.padding(
                start = 40.convertSize(),
                top = 35.convertSize(),
                end = 35.convertSize()
            )
        )
        TextFieldMain(
            modifier = Modifier.padding(
                start = 40.convertSize(),
                top = 12.convertSize(),
                end = 35.convertSize()
            ),
            "请输入手机号",
            username
        ) {
            username = it
        }
        Row(
            modifier = Modifier
                .padding(
                    start = 40.convertSize(),
                    end = 37.convertSize(),
                    top = 35.convertSize()
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "手机号",
                fontSize = 40.convertSpSize(),
                color = c_333333,
            )
            Text(
                "忘记密码？",
                fontSize = 40.convertSpSize(),
                color = c_047B83,
            )
        }
        TextFieldMain(
            modifier = Modifier.padding(
                start = 40.convertSize(),
                top = 12.convertSize(),
                end = 35.convertSize()
            ),
            "请输入登录密码",
            password
        ) {
            password = it
        }

        Box(
            modifier = Modifier
                .padding(
                    start = 37.convertSize(),
                    end = 37.convertSize(),
                    top = 63.convertSize(),
                    bottom = 69.convertSize()
                )
                .fillMaxWidth()
                .height(127.convertSize())
                .background(color = c_047B83, shape = RoundedCornerShape(29.convertSize()))
                .clickable {
                    loginViewmodel.login()
                }
        ) {
            Text(
                "登 录",
                fontSize = 52.convertSpSize(),
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

    }
}

// 注册视图
@Composable
fun RegisterView(modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(bottom = 29.convertSize())
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                brush = Brush.verticalGradient(arrayListOf(c_C1DFE1, Color.White)),
                shape = RoundedCornerShape(
                    bottomEnd = 29.convertSize(),
                    bottomStart = 29.convertSize()
                )
            )
    ) {
        var part by remember {
            mutableStateOf(1)
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                when (part) {
                    1 -> {
                        PartOne(modifier = Modifier) {
                            part = 2
                        }
                    }

                    2 -> {
                        PartTwo(modifier = Modifier, onLastClick = {
                            part = 1
                        }, onNextClick = {
                            part = 3
                        })
                    }

                    3 -> {
                        PartThree(modifier = Modifier, onLastClick = {
                            part = 2
                        }, onNextClick = {
                            // 注册完成，返回登录页
                            part = 1
                        })
                    }
                }
            }
        }

    }
}

@Composable
fun TextFieldMain(
    modifier: Modifier,
    hintText: String,
    text: String = "",
    onValueChange: (String) -> Unit
) {
    var input by remember {
        mutableStateOf(text)
    }
    Box(
        modifier = modifier
            .height(104.convertSize())
            .border(
                width = 3.convertSize(),
                color = c_9BCACD,
                shape = RoundedCornerShape(12.convertSize())
            )
    ) {
        if (input == "") {
            Text(
                hintText,
                color = c_999999,
                fontSize = 37.convertSpSize(),
                modifier = Modifier
                    .align(
                        Alignment.CenterStart
                    )
                    .padding(start = 35.convertSize())
            )
        }
        BasicTextField(
            input,
            onValueChange = {
                input = it
                onValueChange(input)
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(
                    Alignment.CenterStart
                )
                .padding(start = 35.convertSize()),
            singleLine = true,
            textStyle = TextStyle(fontSize = 37.convertSpSize(), color = c_333333),
        )
    }
}