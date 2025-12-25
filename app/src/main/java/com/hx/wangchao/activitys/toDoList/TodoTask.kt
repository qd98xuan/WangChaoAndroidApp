package com.hx.wangchao.activitys.toDoList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.hx.wangchao.activitys.StatusBar
import com.hx.wangchao.ui.theme.c_047B83
import com.hx.wangchao.viewModels.MainViewModel

/**
 * 代办任务
 */
@Composable
fun TodoTask(modifier: Modifier, userName: String) {
    Box(modifier = modifier.fillMaxSize()) {
        Column {
            StatusBar(modifier = Modifier, "待办任务", userName, showBack = true) {

            }
            LazyVerticalGrid(
                GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(23.convertSize())
            ) {
                items(5) {
                    TodoItemCheck(
                        modifier = Modifier,
                        isFinish = false,
                        isSelect = false,
                        title = "课堂测验"
                    ) {

                    }
                }
            }
        }

    }
}

@Composable
fun TodoItemCheck(
    modifier: Modifier,
    isFinish: Boolean,
    isSelect: Boolean,
    title: String,
    onItemClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .paint(
                painterResource(
                    if (isSelect) {
                        R.drawable.todo_item_check_selected
                    } else {
                        if (isFinish) {
                            R.drawable.todo_item_check_finish
                        } else {
                            R.drawable.todo_item_check_normal
                        }
                    }
                ),
                contentScale = ContentScale.FillBounds
            )
            .width(328.convertSize())
            .height(104.convertSize())
            .clickable {
                onItemClick()
            }
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 58.convertSize())
        ) {
            Image(
                painterResource(if (isFinish) R.drawable.check else R.drawable.check_box),
                contentDescription = null,
                modifier = Modifier.size(35.convertSize())
            )
            Text(
                text = title,
                fontSize = 40.convertSpSize(),
                modifier = Modifier.padding(start = 14.convertSize()),
                color = if (isSelect) Color.White else c_047B83
            )
        }
    }
}