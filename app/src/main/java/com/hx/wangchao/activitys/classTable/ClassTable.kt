package com.hx.wangchao.activitys.classTable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.blankj.utilcode.util.ToastUtils
import com.hx.baselibrary.base.convertSize
import com.hx.baselibrary.base.convertSpSize
import com.hx.wangchao.R
import com.hx.wangchao.activitys.StatusBar
import com.hx.wangchao.activitys.toDoList.MainTiele
import com.hx.wangchao.activitys.toDoList.TodoItem
import com.hx.wangchao.ui.theme.c_666666
import com.hx.wangchao.viewModels.ClassTableViewModel
import com.hx.wangchao.viewModels.MainViewModel
import com.hx.wangchao.viewModels.TodoPageDialogType
import com.hx.wangchao.viewModels.TodoTaskStatus

/**
 * 课表
 */
@Composable
fun ClassTable(
    modifier: Modifier,
    mainViewModel: MainViewModel,
    classTableViewModel: ClassTableViewModel
) {
    Column(modifier = modifier) {
        val userName by remember {
            mainViewModel.userName
        }
        val title by remember {
            mainViewModel.title
        }
        // 周课程状态
        val lessonState by classTableViewModel.lessonState.collectAsState()
        if (lessonState?.code != -1) {
            ToastUtils.showShort(lessonState?.message ?: "")
        }
        StatusBar(modifier = Modifier, title, userName) {

        }
        val lessons by remember {
            classTableViewModel.lessons
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                MainTiele(
                    modifier = Modifier.padding(
                        top = 72.convertSize(),
                        bottom = 20.convertSize()
                    ),
                    icon = R.drawable.calendar,
                    title = "七天课表"
                )
            }
            item {
                Column {
                    MainTiele(
                        modifier = Modifier.padding(
                            top = 20.convertSize(),
                            bottom = 20.convertSize()
                        ),
                        icon = R.drawable.ic_class,
                        title = "七天上课安排"
                    )
                    Text(
                        text = "展示从明天开始的后七天上课任务",
                        modifier = Modifier.padding(
                            start = 20.convertSize(),
                            end = 20.convertSize(),
                            bottom = 20.convertSize(),
                        ),
                        fontSize = 40.convertSpSize(),
                        color = c_666666
                    )
                }
            }
            items(lessons?.size?:0) {
                lessons?.get(it)?.let { lesson->
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
                        rightItemName = if (lesson.status == TodoTaskStatus.PLAN.status) "激活" else if (lesson.status == TodoTaskStatus.ACTIVE.status) "完成" else "",
                        onLeftBtnClick = {
                            when(lesson.status) {
                                TodoTaskStatus.PLAN.status->{

                                }
                                TodoTaskStatus.ACTIVE.status-> {

                                }
                                else -> {}
                            }
                        },
                        onRightBtnClick = {
                            // 打开激活弹窗
                            when(lesson.status) {
                                TodoTaskStatus.PLAN.status->{
                                    mainViewModel.todoPageDialogType.value =
                                        TodoPageDialogType.TYPE_ACTIVATE
                                }
                                TodoTaskStatus.ACTIVE.status-> {

                                }
                                else -> {}
                            }
                        }
                    )
                }
            }
        }
    }

}