package com.hx.wangchao.activitys.toDoList

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.hx.baselibrary.base.convertSize
import com.hx.baselibrary.base.convertSpSize
import com.hx.wangchao.R
import com.hx.wangchao.activitys.StatusBar
import com.hx.wangchao.activitys.TextFieldMain
import com.hx.wangchao.ui.theme.c_047B83
import com.hx.wangchao.ui.theme.c_333333
import com.hx.wangchao.ui.theme.c_999999
import com.hx.wangchao.ui.theme.c_9BCACD
import com.hx.wangchao.ui.theme.c_E5E5E5
import com.hx.wangchao.utils.ScreenUtils.px
import com.hx.wangchao.viewModels.MainViewModel
import com.hx.wangchao.viewModels.TodoPageDialogType
import com.hx.wangchao.viewModels.TodoTaskStatus
import com.hx.wangchao.viewModels.TodoViewModel

/**
 * 待办页面
 */
@Composable
fun ToDoList(modifier: Modifier, mainViewModel: MainViewModel, todoViewModel: TodoViewModel) {
    Column(modifier = modifier) {
        val userName by remember {
            mainViewModel.userName
        }
        val title by remember {
            mainViewModel.title
        }
        StatusBar(modifier = Modifier, title, userName) {

        }
        // 获取今日课程
        LaunchedEffect(Unit) {
            todoViewModel.getTodayLessons()
        }

        // 当天的课程安排
        val lessons by remember {
            todoViewModel.lessons
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                MainTiele(
                    modifier = Modifier.padding(
                        top = 72.convertSize(),
                        bottom = 20.convertSize()
                    ),
                    icon = R.drawable.book_mark,
                    title = "今日课程"
                )
            }
            items(lessons?.size ?: 0) {
                lessons?.get(it)?.let { lesson ->
                    TodoItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 20.convertSize(),
                                end = 20.convertSize(),
                                bottom = 35.convertSize()
                            )
                            .height(348.convertSize()),
                        title = "${lesson.courseTitle}·${lesson.title}",
                        time = "今天 15:30-17:00",
                        position = "教室: ${lesson.classTitle}",
                        isActivate = if (lesson.status== TodoTaskStatus.ACTIVE.status) 1 else 2,
                        todoIndex = 0,
                        //这是四个取值，plan是计划，这个状态下可以做激活active和推迟postponed操作，active状态可以做推迟和完成动作
                        leftItemName = if (lesson.status == TodoTaskStatus.PLAN.status) "推迟" else if (lesson.status == TodoTaskStatus.ACTIVE.status) "推迟" else "",
                        rightItemName = if (lesson.status == TodoTaskStatus.PLAN.status) "激活" else if (lesson.status == TodoTaskStatus.ACTIVE.status) "点名" else "",
                        onLeftBtnClick = {
                            when(lesson.status) {
                                TodoTaskStatus.PLAN.status->{
                                    todoViewModel.activeLessonId = lesson.id
                                    mainViewModel.todoPageDialogType.value =
                                        TodoPageDialogType.TYPE_DELAY
                                }
                                TodoTaskStatus.ACTIVE.status-> {
                                    todoViewModel.activeLessonId = lesson.id
                                    mainViewModel.todoPageDialogType.value =
                                        TodoPageDialogType.TYPE_DELAY
                                }
                                else -> {}
                            }
                        },
                        onRightBtnClick = {
                            // 打开激活弹窗
                            when(lesson.status) {
                                TodoTaskStatus.PLAN.status->{
                                    todoViewModel.activeLessonId = lesson.id
                                    mainViewModel.todoPageDialogType.value =
                                        TodoPageDialogType.TYPE_ACTIVATE
                                }
                                TodoTaskStatus.ACTIVE.status-> {
                                    // 点名
                                    todoViewModel.activeLessonId = lesson.id
                                    mainViewModel.todoPageDialogType.value =
                                        TodoPageDialogType.TYPE_ROLLCALL
                                    todoViewModel.getAttendanceList()
                                }
                                else -> {}
                            }
                        }
                    )
                }
            }
            item {
                MainTiele(
                    modifier = Modifier
                        .padding(
                            top = 20.convertSize(),
                            bottom = 20.convertSize()
                        ),
                    icon = R.drawable.todo,
                    title = "待办任务"
                )
            }



            item {
                MainTiele(
                    modifier = Modifier
                        .padding(
                            top = 20.convertSize(),
                            bottom = 20.convertSize()
                        ),
                    icon = R.drawable.group,
                    title = "客户关系"
                )
            }
        }
        SearchView(
            modifier = Modifier.padding(start = 35.convertSize(), end = 35.convertSize()),
            "请输入学生姓名进行查询"
        ) {

        }
        CustomerRelationItem(
            modifier = Modifier.padding(top = 35.convertSize()),
            name = "王十二",
            phoneNum = "138****1234",
            textLeft = "添加记录",
            textRight = "沟通记录",
            onLeftBtnClick = {},
            onRightBtnClick = {}
        )
        CustomerRelationItem(
            modifier = Modifier.padding(top = 35.convertSize()),
            name = "王十二",
            phoneNum = "138****1234",
            textLeft = "添加记录",
            textRight = "沟通记录",
            onLeftBtnClick = {},
            onRightBtnClick = {}
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
    Box(
        modifier = modifier.paint(
            painterResource(if (isActivate == 1) R.mipmap.activate_item_bg else R.mipmap.normal_item_bg),
            contentScale = ContentScale.FillBounds
        )
    ) {
        // 激活状态
        if (isActivate != 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 6.convertSize(), top = 6.convertSize())
                    .width(253.convertSize())
                    .height(86.convertSize())
                    .paint(
                        painterResource(if (isActivate == 1) R.drawable.rectangle_activate else R.drawable.rectangle_unactivate),
                        contentScale = ContentScale.FillBounds
                    )
            ) {
                Text(
                    if (isActivate == 1) "已激活" else "未激活",
                    color = if (isActivate == 1) Color.White else c_047B83,
                    fontSize = 40.convertSpSize(),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 30.convertSize())
                )
            }
        }
        Text(
            title,
            fontSize = 46.convertSpSize(),
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 52.convertSize(), top = 37.convertSize())
        )
        Text(
            time,
            fontSize = 43.convertSpSize(),
            color = Color(0xFF666666),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 107.convertSize(), top = 123.convertSize())
        )
        Image(
            painterResource(R.drawable.clock),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 52.convertSize(), top = 135.convertSize())
                .size(43.convertSize())
        )
        Image(
            painterResource(R.drawable.home),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 52.convertSize(), top = 262.convertSize())
                .size(43.convertSize(), 46.convertSize())
        )
        Text(
            position,
            fontSize = 43.convertSpSize(),
            color = Color(0xFF666666),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 107.convertSize(), top = 252.convertSize())
        )
        Text(
            leftItemName,
            fontSize = 43.convertSpSize(),
            color = Color(0xFF8E9143),
            modifier = Modifier
                .clickable {
                    onLeftBtnClick()
                }
                .align(Alignment.BottomEnd)
                .padding(end = 205.convertSize(), bottom = 15.convertSize())
        )
        Text(
            rightItemName,
            fontSize = 43.convertSpSize(),
            color = Color.White,
            modifier = Modifier
                .clickable {
                    onRightBtnClick()
                }
                .align(Alignment.BottomEnd)
                .padding(end = 25.convertSize(), bottom = 15.convertSize())
        )
    }
}

// 客户关系列表
@Composable
fun CustomerRelationItem(
    modifier: Modifier,
    name: String,
    phoneNum: String,
    textLeft: String,
    textRight: String,
    onLeftBtnClick: () -> Unit,
    onRightBtnClick: () -> Unit
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(170.convertSize())
    ) {
        Text(
            name,
            fontSize = 43.convertSpSize(),
            color = c_333333,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 46.convertSize())
        )
        Text(
            phoneNum,
            fontSize = 43.convertSpSize(),
            color = c_333333,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 46.convertSize())
                .padding(bottom = 35.convertSize())
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 291.convertSize())
                .width(253.convertSize())
                .height(95.convertSize())
                .paint(
                    painterResource(R.drawable.rectangle_green),
                    contentScale = ContentScale.FillBounds
                )
                .clickable {
                    onLeftBtnClick()
                }) {
            Text(
                textLeft,
                fontSize = 43.convertSpSize(),
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 49.convertSize())
                    .clickable {
                        onRightBtnClick()
                    }
                    .padding(end = 25.convertSize(), bottom = 15.convertSize())
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 35.convertSize())
                .width(253.convertSize())
                .height(95.convertSize())
                .paint(
                    painterResource(R.drawable.rectangle_white),
                    contentScale = ContentScale.FillBounds
                )
                .clickable {
                    onRightBtnClick()
                }) {
            Text(
                textRight,
                fontSize = 43.convertSpSize(),
                color = c_047B83,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 49.convertSize())
                    .clickable {
                        onRightBtnClick()
                    }
                    .padding(end = 25.convertSize(), bottom = 15.convertSize())
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 35.convertSize(), end = 35.convertSize(), bottom = 1.convertSize())
                .height(2.convertSize())
                .fillMaxWidth()
                .background(c_E5E5E5)
        )
    }
}

// 查询组件
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(modifier: Modifier, hintText: String, onClick: () -> Unit) {
    Row(modifier = modifier.fillMaxWidth()) {
        var input by remember {
            mutableStateOf("")
        }
        TextFieldMain(modifier = Modifier.weight(1f), hintText) {
            input = it
        }

        Image(
            painterResource(R.drawable.search),
            modifier = Modifier
                .padding(start = 29.convertSize())
                .size(104.convertSize())
                .clickable {
                    onClick()
                },
            contentDescription = null
        )
    }
}