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
import com.hx.wangchao.activitys.toDoList.SelectView
import com.hx.wangchao.ui.theme.c_047B83
import com.hx.wangchao.ui.theme.c_333333
import com.hx.wangchao.ui.theme.c_666666
import com.hx.wangchao.ui.theme.c_999999

@Composable
fun PartOne(modifier: Modifier, onNextClick: () -> Unit) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 40.convertSize())
        ) {
            Image(
                painterResource(R.drawable.num_1_green),
                contentDescription = null,
                modifier = Modifier.size(52.convertSize())
            )
            Text(
                "学校信息",
                fontSize = 46.convertSpSize(),
                color = c_333333,
                modifier = Modifier.padding(start = 23.convertSize())
            )
            Text(
                "填写学校基础资料",
                fontSize = 40.convertSpSize(),
                color = c_666666,
                modifier = Modifier.padding(start = 17.convertSize())
            )
        }
        Text(
            "学校名称",
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
            "如:望潮教育(浦东校区)"
        ) { }
        Text(
            "学校编码",
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
            "如:WC-2025-001"
        ) { }
        Text(
            "学校地址",
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
            "请输入地址详细至街道、门牌号等"
        ) { }
        Text(
            "定位",
            fontSize = 40.convertSpSize(),
            color = c_333333,
            modifier = Modifier.padding(
                start = 35.convertSize(),
                top = 35.convertSize(),
                end = 35.convertSize()
            )
        )
        Text(
            "邮政编码",
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
            "如:200120"
        ) { }
        Text(
            "所属行业",
            fontSize = 40.convertSpSize(),
            color = c_333333,
            modifier = Modifier.padding(
                start = 35.convertSize(),
                top = 12.convertSize(),
                end = 35.convertSize()
            )
        )
        SelectView(
            modifier = Modifier
                .padding(top = 17.convertSize(), start = 35.convertSize(), end = 35.convertSize())
                .fillMaxWidth(),
            text = "张雪峰",
            onClick = {

            }
        )
        Text(
            "机构图片",
            fontSize = 40.convertSpSize(),
            color = c_333333,
            modifier = Modifier.padding(
                start = 35.convertSize(),
                top = 12.convertSize(),
                end = 35.convertSize()
            )
        )
        Image(
            painterResource(R.drawable.upload_organization),
            modifier = Modifier
                .width(363.convertSize())
                .height(95.convertSize())
                .padding(
                    top = 17.convertSize(),
                    start = 35.convertSize()
                ),
            contentDescription = null
        )
        Text(
            "最多可拍摄并上传 3 张机构环境图片",
            fontSize = 40.convertSpSize(),
            color = c_999999,
            modifier = Modifier.padding(
                start = 46.convertSize(),
                top = 17.convertSize()
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
                    .size(52.convertSize()),
                contentDescription = null
            )
            Image(
                painterResource(R.drawable.num_2_gray),
                modifier = Modifier
                    .padding(start = 23.convertSize(), end = 23.convertSize())
                    .size(40.convertSize()),
                contentDescription = null
            )
            Image(
                painterResource(R.drawable.num_3_gray),
                modifier = Modifier
                    .size(40.convertSize()),
                contentDescription = null
            )
        }
        MainBtn(
            modifier = modifier
                .padding(
                    top = 52.convertSize(),
                    start = 35.convertSize(),
                    end = 35.convertSize(),
                    bottom = 69.convertSize()
                )
                .fillMaxWidth()
                .height(127.convertSize())
                , text = "下一步",
            c_047B83,
            Color.White
        ) {
            onNextClick()
        }


    }
}

@Composable
fun MainBtn(modifier: Modifier, text: String, backgroundColor: Color,textColor: Color, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(29.convertSize()))
            .clickable {
                onClick()
            }
    ) {
        Text(
            text,
            fontSize = 52.convertSpSize(),
            color = textColor,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}