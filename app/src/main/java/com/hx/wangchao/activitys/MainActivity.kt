package com.hx.wangchao.activitys

import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ToastUtils
import com.hx.baselibrary.base.convertSize
import com.hx.baselibrary.base.convertSpSize
import com.hx.wangchao.R
import com.hx.wangchao.activitys.classTable.ClassTable
import com.hx.wangchao.activitys.toDoList.ActivateDialog
import com.hx.wangchao.activitys.toDoList.DelayDialog
import com.hx.wangchao.activitys.toDoList.RollCallDialog
import com.hx.wangchao.activitys.toDoList.RollcallItem
import com.hx.wangchao.activitys.toDoList.ToDoList
import com.hx.wangchao.activitys.toDoList.TodoTask
import com.hx.wangchao.ui.theme.c_047B83
import com.hx.wangchao.ui.theme.c_56000000
import com.hx.wangchao.ui.theme.c_666666
import com.hx.wangchao.ui.theme.c_C1DFE1
import com.hx.wangchao.ui.theme.c_F2F8F9
import com.hx.wangchao.utils.main
import com.hx.wangchao.viewModels.BaseDataViewModel
import com.hx.wangchao.viewModels.ClassTableViewModel
import com.hx.wangchao.viewModels.MainType
import com.hx.wangchao.viewModels.MainViewModel
import com.hx.wangchao.viewModels.TodoPageDialogType
import com.hx.wangchao.viewModels.TodoViewModel
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * 主页面
 */
class MainActivity : BaseAppActivity() {
    val mainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    // 待办ViewModel
    val todoViewModel by lazy { ViewModelProvider(this)[TodoViewModel::class.java] }

    // 课表ViewModel
    val classTable by lazy { ViewModelProvider(this)[ClassTableViewModel::class.java] }

    // 基础数据ViewModel
    val baseDataViewModel by lazy { ViewModelProvider(this)[BaseDataViewModel::class.java] }

    // 课堂相关的ViewModel
    val lessonViewModel by lazy { ViewModelProvider(this)[com.hx.wangchao.viewModels.LessonViewModel::class.java] }

    // 上传文件的ViewModel
    val ossViewModel by lazy { ViewModelProvider(this)[com.hx.wangchao.viewModels.OSSViewModel::class.java] }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 获取老师列表
        baseDataViewModel.getTeacherList()
        // 获取场地列表
        baseDataViewModel.getSpaceList()
        setContent {
            var selectIndex by remember {
                mainViewModel.selectIndex
            }
            var title by remember {
                mainViewModel.title
            }
            LaunchedEffect(Unit) {
                baseDataViewModel.baseDatatStatus.receiveAsFlow().collect {
                    if (it.code != 200) {
                        ToastUtils.showShort(it.message)
                    }
                }
                todoViewModel.todoTaskChannel.receiveAsFlow().collect {
                    if (it.code != 200) {
                        ToastUtils.showShort(it.message)
                    }
                }
            }
            LaunchedEffect(selectIndex) {
                title = mainViewModel.navList[selectIndex].title
                if (selectIndex == 0) {
                    // 获取老师列表
                    baseDataViewModel.getTeacherList()
                    // 获取场地列表
                    baseDataViewModel.getSpaceList()
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            arrayListOf(
                                c_C1DFE1,
                                c_F2F8F9
                            )
                        )
                    )
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    val userName by remember {
                        mainViewModel.userName
                    }
                    val todoTaskList = remember {
                        mainViewModel.todoTaskList
                    }
                    // 主页面的类型
                    val mainType by remember {
                        mainViewModel.mainType
                    }
                    when (mainType) {
                        MainType.TYPE_NULL -> {
                            when (selectIndex) {
                                // 待办
                                0 -> {
                                    ToDoList(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f, fill = true),
                                        mainViewModel,
                                        todoViewModel,
                                        lessonViewModel
                                    )
                                }
                                // 课表
                                1 -> {
                                    ClassTable(
                                        Modifier
                                            .fillMaxWidth()
                                            .weight(1f, fill = true),
                                        mainViewModel,
                                        classTable
                                    )
                                }

                                2 -> {}
                            }
                        }
                        MainType.TYPE_TODOTASK -> {
                            TodoTask(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f, fill = true),
                                userName = userName, todoTaskList,
                                mainViewModel,
                                lessonViewModel,
                                ossViewModel
                            )
                        }

                        MainType.TYPE_QINGJIA -> {

                        }
                    }
                    Navigation(
                        modifier = Modifier
                            .height(158.convertSize()),
                        selectColor = c_047B83,
                        defaultColor = c_666666
                    ) {
                        selectIndex = it
                    }
                }
                var todoPageDialogType by remember {
                    mainViewModel.todoPageDialogType
                }
                // 弹窗的遮罩
                if (todoPageDialogType != TodoPageDialogType.TYPE_NULL) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = c_56000000)
                            .clickable {
                                todoPageDialogType = TodoPageDialogType.TYPE_NULL
                            })
                }
                when (todoPageDialogType) {
                    TodoPageDialogType.TYPE_ACTIVATE -> {
                        ActivateDialog(
                            modifier = Modifier.align(Alignment.BottomCenter),
                            mainViewModel,
                            baseDataViewModel,
                            todoViewModel
                        )
                    }

                    TodoPageDialogType.TYPE_DELAY -> {
                        DelayDialog(
                            modifier = Modifier.align(Alignment.BottomCenter),
                            todoViewModel,
                            mainViewModel
                        )
                    }

                    TodoPageDialogType.TYPE_ROLLCALL -> {
                        RollCallDialog(
                            modifier = Modifier.align(Alignment.BottomCenter),
                            mainViewModel,
                            todoViewModel
                        )
                    }

                    else -> {}
                }
            }
        }
    }

    /**
     * 导航栏
     */
    @Composable
    fun Navigation(
        modifier: Modifier,
        selectColor: Color,
        defaultColor: Color,
        onClick: (selectIndex: Int) -> Unit
    ) {
        val navList = remember {
            mainViewModel.navList
        }
        Card(
            modifier = modifier
                .zIndex(1f)
                .shadow(16.convertSize(), shape = RoundedCornerShape(0.convertSize())),
            colors = cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(0.convertSize())

        ) {
            Row(modifier = Modifier.padding(top = 23.convertSize())) {
                navList.forEachIndexed { index, item ->
                    NavigationItem(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                onClick(index)
                            },
                        isSelected = mainViewModel.selectIndex.value == index,
                        title = item.title,
                        icon = item.icon,
                        selectColor = selectColor,
                        defaultColor = defaultColor
                    )

                }
            }
        }
    }

    @Composable
    fun NavigationItem(
        modifier: Modifier,
        isSelected: Boolean,
        title: String,
        icon: Int,
        selectColor: Color,
        defaultColor: Color
    ) {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painterResource(icon),
                modifier = Modifier.size(63.convertSize()),
                contentDescription = "",
                colorFilter = ColorFilter.tint(if (isSelected) selectColor else defaultColor)
            )
            Text(
                text = title,
                fontSize = 32.convertSpSize(),
                modifier = Modifier.padding(top = 12.convertSize()),
                color = if (isSelected) selectColor else defaultColor
            )
        }
    }
}

/**
 * 状态栏
 */
@Composable
fun StatusBar(
    modifier: Modifier,
    title: String,
    userName: String,
    showBack: Boolean = false,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = c_047B83)
            .fillMaxWidth()
            .height(156.convertSize())
    ) {
        if (showBack) {
            Image(
                painterResource(R.drawable.back_btn), contentDescription = "back",
                modifier = Modifier
                    .padding(start = 40.convertSize())
                    .width(26.convertSize())
                    .height(49.convertSize())
                    .align(Alignment.CenterStart)
                    .clickable {
                        onBackClick()
                    }
            )
        }
        Text(
            title,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 92.convertSize()),
            fontSize = 46.convertSpSize()
        )
        Text(
            "望潮教培助手",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 23.convertSize(), end = 170.convertSize()),
            fontSize = 46.convertSpSize()
        )
        Text(
            text = "辛苦了！${userName}",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 29.convertSize(), end = 170.convertSize()),
            fontSize = 35.convertSpSize()
        )
        Image(
            painterResource(R.drawable.me),
            contentDescription = "avatar",
            modifier = Modifier
                .size(0.convertSize())
                .align(
                    Alignment.CenterEnd
                )
                .padding(end = 29.convertSize())
        )
    }
}