package com.hx.wangchao.activitys.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.hx.baselibrary.base.convertSize
import com.hx.baselibrary.base.convertSpSize
import com.hx.wangchao.R
import com.hx.wangchao.activitys.TextFieldMain
import com.hx.wangchao.ui.theme.c_047B83
import com.hx.wangchao.ui.theme.c_333333
import com.hx.wangchao.ui.theme.c_666666

@Composable
fun PartThree(modifier: Modifier, onLastClick:()-> Unit, onNextClick:() -> Unit) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 40.convertSize())
        ) {
            Image(
                painterResource(R.drawable.num_3_green),
                contentDescription = null,
                modifier = Modifier.size(52.convertSize())
            )
            Text(
                "App 对接信息",
                fontSize = 46.convertSpSize(),
                color = c_333333,
                modifier = Modifier.padding(start = 23.convertSize())
            )
            Text(
                "用于对接第三方系统(可选)",
                fontSize = 40.convertSpSize(),
                color = c_666666,
                modifier = Modifier.padding(start = 17.convertSize())
            )
        }
        Text(
            "App Title",
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
            "用于展示给用户看的应用名称"
        ) { }
        Text(
            "AppID",
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
            "用于对接第三方系统(示意字段)"
        ) { }
        Text(
            "App Secret",
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
            "密钥(示意字段)"
        ) { }

        Text(
            "当前状态:尚未提交",
            fontSize = 35.convertSpSize(),
            color = c_666666,
            modifier = Modifier.padding(
                start = 35.convertSize(),
                top = 12.convertSize(),
            )
        )

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
                    .size(40.convertSize()),
                contentDescription = null
            )
            Image(
                painterResource(R.drawable.num_3_green),
                modifier = Modifier
                    .size(52.convertSize()),
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