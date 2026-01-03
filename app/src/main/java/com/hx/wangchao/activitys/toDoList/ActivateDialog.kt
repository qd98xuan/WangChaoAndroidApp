package com.hx.wangchao.activitys.toDoList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import com.hx.baselibrary.base.convertSize
import com.hx.baselibrary.base.convertSpSize
import com.hx.wangchao.R
import com.hx.wangchao.ui.theme.c_047B83
import com.hx.wangchao.ui.theme.c_333333
import com.hx.wangchao.viewModels.MainViewModel
import com.hx.wangchao.viewModels.TodoPageDialogType

/**
 * 激活对话框
 */
@Composable
fun ActivateDialog(modifier: Modifier,mainViewModel: MainViewModel) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 58.convertSize(), topEnd = 58.convertSize())
            )
    ) {
        Image(
            painterResource(R.drawable.activate_title),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 63.convertSize(), start = 63.convertSize())
                .width(173.convertSize())
                .height(98.convertSize())
        )
        Text(
            text = "上课老师",
            fontSize = 46.convertSpSize(),
            color = c_047B83,
            modifier = Modifier.padding(top = 31.convertSize(), start = 75.convertSize()),
        )
        SelectView(
            modifier = Modifier
                .padding(top = 17.convertSize(), start = 69.convertSize(), end = 69.convertSize())
                .fillMaxWidth(),
            text = "张雪峰",
            onClick = {

            }
        )

        Text(
            text = "场地（可为空）",
            fontSize = 46.convertSpSize(),
            color = c_047B83,
            modifier = Modifier.padding(top = 40.convertSize(), start = 75.convertSize()),
        )

        SelectView(
            modifier = Modifier
                .padding(top = 17.convertSize(), start = 69.convertSize(), end = 69.convertSize())
                .fillMaxWidth(),
            text = "一楼 103",
            onClick = {

            }
        )

        ConfirmCancelView(
            modifier = Modifier
                .padding(
                    start = 63.convertSize(),
                    end = 63.convertSize(),
                    top = 69.convertSize(),
                    bottom = 26.convertSize()
                ),
            "确认激活",
            "取消",
            onConfirmClick = {

            },
            onCancelClick = {
                mainViewModel.todoPageDialogType.value = TodoPageDialogType.TYPE_NULL
            }
        )

    }
}

// 可选择的选择框
@Composable
fun SelectView(modifier: Modifier, text: String, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .height(135.convertSize())
            .paint(
                painterResource(R.drawable.select_item_bg),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Text(
            text = text,
            fontSize = 40.convertSpSize(),
            color = c_333333,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 40.convertSize()),
        )
    }
}

// 确认取消选择框
@Composable
fun ConfirmCancelView(
    modifier: Modifier,
    confirmText: String,
    cancelText: String,
    onConfirmClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(127.convertSize())
                .paint(
                    painterResource(R.drawable.cancel_bg),
                    contentScale = ContentScale.FillBounds
                )
                .clickable {
                    onCancelClick()
                }) {
            Text(
                text = cancelText,
                fontSize = 52.convertSpSize(),
                color = c_047B83,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
                )
        }

        Box(
            modifier = Modifier
                .padding(start = 43.convertSize())
                .weight(1f)
                .height(127.convertSize())
                .paint(
                    painterResource(R.drawable.confirm_bg),
                    contentScale = ContentScale.FillBounds
                )
                .clickable {
                    onConfirmClick()
                }
        ) {
            Text(
                text = confirmText,
                fontSize = 52.convertSpSize(),
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}