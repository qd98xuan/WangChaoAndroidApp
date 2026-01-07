package com.hx.wangchao.activitys.toDoList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.hx.baselibrary.base.convertSize
import com.hx.baselibrary.base.convertSpSize
import com.hx.wangchao.R
import com.hx.wangchao.ui.theme.c_047B83
import com.hx.wangchao.ui.theme.c_333333
import com.hx.wangchao.viewModels.MainViewModel
import com.hx.wangchao.viewModels.TodoPageDialogType
import com.hx.wangchao.viewModels.TodoViewModel

/**
 * 推迟对话框
 */
@Composable
fun DelayDialog(modifier: Modifier,todoViewModel: TodoViewModel,mainViewModel: MainViewModel) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 58.convertSize(), topEnd = 58.convertSize())
            )
    ) {
        Image(
            painterResource(R.drawable.delay_title),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 63.convertSize(), start = 63.convertSize())
                .width(173.convertSize())
                .height(98.convertSize())
        )
        Text(
            text = "推迟原因",
            fontSize = 46.convertSpSize(),
            color = c_047B83,
            modifier = Modifier.padding(top = 31.convertSize(), start = 75.convertSize()),
        )

        // 推迟原因输入框
        var delayReason by remember {
            mutableStateOf("")
        }

        Box(
            modifier = Modifier
                .padding(start = 69.convertSize(), end = 69.convertSize(), top = 17.convertSize())
                .fillMaxWidth()
                .height(386.convertSize())
                .paint(
                    painterResource(R.drawable.content_bg),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            if (delayReason == "") {
                Text(
                    text = "请输入推迟上课的原因(如:天气原因、班级活动调整等)",
                    fontSize = 40.convertSpSize(),
                    color = c_333333,
                    modifier = Modifier
                        .padding(start = 46.convertSize(), top = 35.convertSize())
                        .fillMaxWidth(),
                )
            }
            BasicTextField(
                delayReason,
                onValueChange = {
                    delayReason = it
                },
                modifier = Modifier.padding(start = 46.convertSize(), top = 35.convertSize()),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 40.convertSpSize()
                )
            )
        }

        ConfirmCancelView(
            modifier = Modifier
                .padding(
                    start = 63.convertSize(),
                    end = 63.convertSize(),
                    top = 69.convertSize(),
                    bottom = 26.convertSize()
                ),
            "确认推迟",
            "取消",
            onConfirmClick = {
                todoViewModel.postponeLesson(delayReason)
            },
            onCancelClick = {
                mainViewModel.todoPageDialogType.value = TodoPageDialogType.TYPE_NULL
            }
        )

    }
}