package com.hx.wangchao.activitys.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.hx.baselibrary.base.convertSize
import com.hx.baselibrary.base.convertSpSize
import com.hx.wangchao.R
import com.hx.wangchao.activitys.TextFieldMain
import com.hx.wangchao.activitys.toDoList.SelectView
import com.hx.wangchao.ui.theme.c_047B83
import com.hx.wangchao.ui.theme.c_333333
import com.hx.wangchao.ui.theme.c_666666
import com.hx.wangchao.ui.theme.c_999999
import com.hx.wangchao.ui.theme.c_9BCACD

@Composable
fun PartTwo(modifier: Modifier, onLastClick: () -> Unit, onNextClick: () -> Unit) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 40.convertSize())
        ) {
            Image(
                painterResource(R.drawable.num_2_green),
                contentDescription = null,
                modifier = Modifier.size(52.convertSize())
            )
            Text(
                "校长信息",
                fontSize = 46.convertSpSize(),
                color = c_333333,
                modifier = Modifier.padding(start = 23.convertSize())
            )
            Text(
                "设置校长账号与联系方式",
                fontSize = 40.convertSpSize(),
                color = c_666666,
                modifier = Modifier.padding(start = 17.convertSize())
            )
        }
        Text(
            "校长姓名",
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
                start = 35.convertSize(),
                top = 12.convertSize(),
                end = 35.convertSize()
            ),
            "请输入姓名"
        ) { }
        Text(
            "校长手机号",
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
                start = 35.convertSize(),
                top = 12.convertSize(),
                end = 35.convertSize()
            ),
            "请输入手机号"
        ) { }
        Text(
            "短信验证码",
            fontSize = 40.convertSpSize(),
            color = c_333333,
            modifier = Modifier.padding(
                start = 40.convertSize(),
                top = 35.convertSize(),
                end = 35.convertSize()
            )
        )
        GetVerificationCode(
            modifier = Modifier.padding(
                start = 35.convertSize(),
                top = 12.convertSize(),
                end = 35.convertSize()
            ), "请输入6位验证码", onValueChange = {

            }, onClick = {

            })
        Text(
            "登录密码",
            fontSize = 40.convertSpSize(),
            color = c_333333,
            modifier = Modifier.padding(
                start = 35.convertSize(),
                top = 35.convertSize(),
                end = 35.convertSize()
            )
        )
        TextFieldMain(
            modifier = Modifier.padding(
                start = 35.convertSize(),
                top = 12.convertSize(),
                end = 35.convertSize()
            ),
            "请设置不少于6位的登录密码"
        ) { }
        Text(
            "确认登录密码",
            fontSize = 40.convertSpSize(),
            color = c_333333,
            modifier = Modifier.padding(
                start = 35.convertSize(),
                top = 35.convertSize(),
                end = 35.convertSize()
            )
        )
        TextFieldMain(
            modifier = Modifier.padding(
                start = 35.convertSize(),
                top = 12.convertSize(),
                end = 35.convertSize()
            ),
            "请再次输入登录密码"
        ) { }

        Row(
            modifier = Modifier
                .padding(top = 52.convertSize())
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(R.drawable.num_1_green),
                modifier = Modifier
                    .size(40.convertSize()),
                contentDescription = null
            )
            Image(
                painterResource(R.drawable.num_2_green),
                modifier = Modifier
                    .padding(start = 23.convertSize(), end = 23.convertSize())
                    .size(52.convertSize()),
                contentDescription = null
            )
            Image(
                painterResource(R.drawable.num_3_gray),
                modifier = Modifier
                    .size(40.convertSize()),
                contentDescription = null
            )
        }
        Row(
            modifier = Modifier.padding(
                start = 35.convertSize(),
                end = 35.convertSize(),
                top = 52.convertSize(),
                bottom = 69.convertSize()
            ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MainBtn(
                modifier = Modifier
                    .weight(1f)
                    .height(127.convertSize())
                    .border(2.convertSize(), c_047B83, RoundedCornerShape(29.convertSize())),
                text = "上一步",
                backgroundColor = Color.White,
                textColor = c_047B83
            ) {
                onLastClick()
            }
            MainBtn(
                modifier = Modifier
                    .padding(start = 32.convertSize())
                    .weight(1f)
                    .height(127.convertSize()),
                text = "下一步",
                backgroundColor = c_047B83,
                textColor = Color.White
            ) {
                onNextClick()
            }
        }
    }
}

/**
 * 获取验证码组件
 */
@Composable
fun GetVerificationCode(
    modifier: Modifier,
    hintText: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit
) {
    var input by remember {
        mutableStateOf("")
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
        Box(
            modifier = Modifier
                .width(288.convertSize())
                .height(109.convertSize())
                .align(Alignment.CenterEnd)
                .padding(end = 12.convertSize(), top = 12.convertSize(), bottom = 12.convertSize())
                .background(color = c_047B83, shape = RoundedCornerShape(29.convertSize()))
                .clickable {
                    onClick()
                }
        ) {
            Text(
                "获取验证码",
                fontSize = 52.convertSpSize(),
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}