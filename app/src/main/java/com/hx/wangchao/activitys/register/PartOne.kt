package com.hx.wangchao.activitys.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.hx.baselibrary.base.convertSize
import com.hx.baselibrary.base.convertSpSize
import com.hx.wangchao.R
import com.hx.wangchao.ui.theme.c_333333
import com.hx.wangchao.ui.theme.c_666666

@Composable
fun PartOne(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
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
    }
}