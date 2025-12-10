package com.hx.wangchao.activitys.toDoList

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.hx.baselibrary.base.convertSize
import com.hx.baselibrary.base.convertSpSize
import com.hx.wangchao.R
import com.hx.wangchao.ui.theme.c_047B83

/**
 * 待办页面
 */
@Composable
fun ToDoList(modifier: Modifier) {
    Column(modifier = modifier) {
        MainTiele(
            modifier = Modifier,
            icon = R.drawable.book_mark,
            title = "今日课程"
        )
        MainTiele(
            modifier = Modifier,
            icon = R.drawable.todo,
            title = "待办任务"
        )
        MainTiele(
            modifier = Modifier,
            icon = R.drawable.group,
            title = "客户关系"
        )
    }
}


/**
 * 页面主要title
 */
@Composable
fun MainTiele(modifier: Modifier, icon: Int, title: String) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 20.convertSize())
                .size(50.convertSize(), 52.convertSize())
        )
        Text(
            title,
            fontSize = 52.convertSpSize(),
            color = c_047B83,
            modifier = Modifier.padding(start = 18.convertSize())
        )
    }
}

/**
 * 任务的通用Item
 * isActivate 0-不显示 1-已激活 2-未激活
 * todoIndex 0-不显示
 */
@Composable
fun TodoItem(
    modifier: Modifier,
    title: String,
    time: String,
    isActivate: Int,
    position: String,
    todoIndex: Int,
    leftItemName: String,
    rightItemName: String,
    onLeftBtnClick: () -> Unit,
    onRightBtnClick: () -> Unit
) {
    Card(modifier = modifier) {
        Box(
            modifier = Modifier.paint(
                painterResource(if (isActivate == 1) R.mipmap.activate_item_bg else R.mipmap.normal_item_bg),
                contentScale = ContentScale.FillBounds
            )
        ) {
            // 激活状态
            if (isActivate != 0) {
                Box(
                    modifier = Modifier
                        .width(253.convertSize())
                        .height(86.convertSize())
                        .paint(painterResource(if (isActivate == 1) R.drawable.rectangle_activate else R.drawable.rectangle_unactivate))
                ) {
                    Text(
                        if (isActivate == 1) "已激活" else "未激活",
                        color = if (isActivate == 1) Color.White else c_047B83,
                        fontSize = 40.convertSpSize(),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

        }
    }

}