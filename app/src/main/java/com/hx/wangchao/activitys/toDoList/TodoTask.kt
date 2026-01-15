package com.hx.wangchao.activitys.toDoList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import coil.compose.AsyncImage
import com.hx.baselibrary.base.convertSize
import com.hx.baselibrary.base.convertSpSize
import com.hx.wangchao.Entity.AddPerformanceEntity
import com.hx.wangchao.R
import com.hx.wangchao.activitys.StatusBar
import com.hx.wangchao.ui.theme.c_01047B83
import com.hx.wangchao.ui.theme.c_047B83
import com.hx.wangchao.ui.theme.c_10047B83
import com.hx.wangchao.ui.theme.c_999999
import com.hx.wangchao.ui.theme.c_F2F8F9
import com.hx.wangchao.viewModels.LessonViewModel
import com.hx.wangchao.viewModels.MainType
import com.hx.wangchao.viewModels.MainViewModel
import com.hx.wangchao.viewModels.TodoTaskItem
import com.hx.wangchao.viewModels.TodoViewModel

/**
 * 代办任务
 */
@Composable
fun TodoTask(
    modifier: Modifier,
    userName: String,
    todoTaskList: SnapshotStateList<TodoTaskItem>,
    mainViewModel: MainViewModel,
    lessonViewModel: LessonViewModel
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = arrayListOf(c_10047B83, c_01047B83)
                )
            )
    ) {
        Column {
            StatusBar(modifier = Modifier, "待办任务", userName, showBack = true) {
                mainViewModel.mainType.value = MainType.TYPE_NULL
            }
            MainTiele(
                modifier = Modifier,
                icon = R.drawable.book_mark,
                title = "小六数学培优冲刺"
            )
            // 选择的哪一个待办
            var todoSelect by remember {
                mutableStateOf(todoTaskList[0].taskTitle)
            }
            LazyVerticalGrid(
                GridCells.Fixed(3),
                modifier = Modifier
                    .padding(
                        start = 40.convertSize(),
                        end = 40.convertSize(),
                        top = 35.convertSize()
                    ),
                verticalArrangement = Arrangement.spacedBy(23.convertSize())
            ) {
                items(todoTaskList.size) { index ->
                    TodoItemCheck(
                        modifier = Modifier,
                        isFinish = todoTaskList[index].isFinish,
                        isSelect = todoSelect == todoTaskList[index].taskTitle,
                        title = todoTaskList[index].taskTitle
                    ) {
                        todoSelect = todoTaskList[index].taskTitle
                        when (todoSelect) {
                            "课堂测验" -> {}
                            "课堂表现" -> {
                                lessonViewModel.getLessonPerformanceList()
                            }
                            "布置作业" -> {}
                            "作业检查" -> {}
                            "课照上传" -> {}
                        }
                    }
                }
            }
            // 判断选择的哪个待办
            when (todoSelect) {
                "课堂测验" -> ClassTest()
                "课堂表现" -> ClassPerformance(lessonViewModel)
                "布置作业" -> AssignHomework(
                    modifier = Modifier.padding(
                        start = 35.convertSize(),
                        end = 35.convertSize(),
                        top = 35.convertSize()
                    ), ""
                ) {}

                "作业检查" -> HomeworkCheck()
                "课照上传" -> ClassPhotoUpload()
            }
        }

    }
}

// 待办任务选择框
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
                .padding(start = 58.convertSize()),
            verticalAlignment = Alignment.CenterVertically
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

// 课堂表现
@Composable
fun ClassPerformance(lessonViewModel: LessonViewModel) {
    val performanceList = remember {
        lessonViewModel.performanceList
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 35.convertSize(),
                end = 35.convertSize(),
                top = 35.convertSize()
            )
    ) {
        items(performanceList.size) {
            performanceList.get(it).let { student->
                ClassPerformanceItem(
                    modifier = Modifier.padding(
                        bottom = 35.convertSize()
                    ),
                    name = student.accountRealname,
                    text = student.review,
                ) {
                    lessonViewModel.addLessonPerformance(AddPerformanceEntity(
                        lessonViewModel.lessonId,
                        student.accountId,
                        it
                    ))
                }
            }
        }
    }
}

// 课堂表现item
@Composable
fun ClassPerformanceItem(
    modifier: Modifier,
    name: String,
    text: String,
    onTextChange: (text: String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(29.convertSize()))
    ) {
        Row(
            modifier = Modifier.padding(top = 35.convertSize(), start = 40.convertSize()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(R.drawable.good), contentDescription = null,
                modifier = Modifier
                    .width(40.convertSize())
                    .height(46.convertSize())
            )
            Text(
                text = name,
                fontSize = 40.convertSpSize(),
                modifier = Modifier
                    .padding(start = 9.convertSize()),
                color = c_047B83
            )
        }
        Box(
            modifier = Modifier
                .padding(
                    start = 35.convertSize(),
                    end = 35.convertSize(),
                    top = 35.convertSize(),
                    bottom = 52.convertSize()
                )
                .fillMaxWidth()
                .background(c_F2F8F9, shape = RoundedCornerShape(17.convertSize()))
                .border(3.convertSize(), c_F2F8F9, shape = RoundedCornerShape(17.convertSize()))
        ) {
            var text by remember {
                mutableStateOf(text)
            }
            BasicTextField(
                text, onValueChange = {
                    text = it
                    onTextChange(text)
                }, textStyle = TextStyle(fontSize = 40.convertSpSize()),
                modifier = Modifier
                    .padding(40.convertSize())
                    .fillMaxSize()
            )
            if (text.isEmpty()) {
                Text(
                    "请输入课堂表现内容",
                    fontSize = 40.convertSpSize(),
                    color = c_999999,
                    modifier = Modifier
                        .padding(40.convertSize())
                )
            }
        }

    }
}

// 课堂检测
@Composable
fun ClassTest() {
    LazyColumn(
        modifier = Modifier.padding(
            start = 35.convertSize(),
            end = 35.convertSize(),
            top = 35.convertSize()
        ),
    ) {
        items(20) {
            TestImageUploadItem(
                modifier = Modifier.padding(bottom = 35.convertSize()),
                name = "王十二",
                images = arrayListOf(
                    "https://gips3.baidu.com/it/u=3886271102,3123389489&fm=3028&app=3028&f=JPEG&fmt=auto?w=1280&h=960",
                    "https://gips0.baidu.com/it/u=1690853528,2506870245&fm=3028&app=3028&f=JPEG&fmt=auto?w=1024&h=1024"
                ),
            ) {

            }
        }
        item {
            ConfirmCancelView(
                modifier = Modifier
                    .padding(
                        start = 63.convertSize(),
                        end = 63.convertSize(),
                        top = 69.convertSize(),
                        bottom = 26.convertSize()
                    ),
                "标记完成",
                "保存",
                onConfirmClick = {

                },
                onCancelClick = {

                }
            )
        }
    }
}

// 课堂检测照片上传
@Composable
fun TestImageUploadItem(
    modifier: Modifier,
    name: String,
    images: ArrayList<String>,
    uploadClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(29.convertSize()))
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.padding(top = 35.convertSize(), start = 40.convertSize()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(R.drawable.paper), contentDescription = null,
                    modifier = Modifier
                        .width(40.convertSize())
                        .height(46.convertSize())
                )
                Text(
                    text = name,
                    fontSize = 40.convertSpSize(),
                    modifier = Modifier
                        .padding(start = 9.convertSize()),
                    color = c_047B83
                )
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 40.convertSize(),
                        top = 35.convertSize(),
                        bottom = 46.convertSize()
                    ),
                contentPadding = PaddingValues(end = 35.convertSize())
            ) {
                items(images.size) { index ->
                    AsyncImage(
                        model = images[index],
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 35.convertSize())
                            .size(288.convertSize())
                    )
                }
                item {
                    Image(
                        painterResource(R.drawable.add_photo),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(288.convertSize())
                            .clickable {
                                uploadClick()
                            }
                    )
                }
            }
        }
    }
}

// 布置作业
@Composable
fun AssignHomework(modifier: Modifier, text: String, onTextChange: (text: String) -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(29.convertSize()))
    ) {
        Box(
            modifier = Modifier
                .padding(
                    start = 35.convertSize(),
                    end = 35.convertSize(),
                    top = 35.convertSize(),
                    bottom = 52.convertSize()
                )
                .fillMaxWidth()
                .background(c_F2F8F9, shape = RoundedCornerShape(17.convertSize()))
                .border(3.convertSize(), c_F2F8F9, shape = RoundedCornerShape(17.convertSize()))
        ) {
            var text by remember {
                mutableStateOf(text)
            }
            BasicTextField(
                text, onValueChange = {
                    text = it
                    onTextChange(text)
                }, textStyle = TextStyle(fontSize = 40.convertSpSize()),
                modifier = Modifier
                    .padding(40.convertSize())
                    .fillMaxWidth()
            )
            if (text.isEmpty()) {
                Text(
                    "请输入布置作业",
                    fontSize = 40.convertSpSize(),
                    color = c_999999,
                    modifier = Modifier
                        .padding(40.convertSize())
                )
            }
        }
    }
}

// 作业检查
@Composable
fun HomeworkCheck() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 35.convertSize(),
                end = 35.convertSize(),
                top = 35.convertSize()
            )
    ) {
        items(3) {
            HomeworkCheckItem(
                modifier = Modifier.padding(bottom = 35.convertSize()),
                name = "王十二",
                text = "",
                images = arrayListOf(),
                files = arrayListOf(),
                uploadClick = {},
                onTextChange = {}
            )
        }
    }
}

// 作业检查item
@Composable
fun HomeworkCheckItem(
    modifier: Modifier,
    name: String,
    text: String,
    images: ArrayList<String>,
    files: ArrayList<String>,
    uploadClick: () -> Unit,
    onTextChange: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(29.convertSize()))
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.padding(top = 35.convertSize(), start = 40.convertSize()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(R.drawable.text_pad), contentDescription = null,
                    modifier = Modifier
                        .width(40.convertSize())
                        .height(46.convertSize())
                )
                Text(
                    text = name,
                    fontSize = 40.convertSpSize(),
                    modifier = Modifier
                        .padding(start = 9.convertSize()),
                    color = c_047B83
                )
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 40.convertSize(),
                        top = 35.convertSize(),
                        bottom = 46.convertSize()
                    ),
                contentPadding = PaddingValues(end = 35.convertSize())
            ) {
                items(images.size) { index ->
                    AsyncImage(
                        model = images[index],
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 35.convertSize())
                            .size(288.convertSize())
                    )
                }
                item {
                    Image(
                        painterResource(R.drawable.add_photo),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(288.convertSize())
                            .clickable {
                                uploadClick()
                            }
                    )
                }
            }
            Row(
                modifier = Modifier.padding(top = 40.convertSize(), start = 40.convertSize()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(R.drawable.debug), contentDescription = null,
                    modifier = Modifier
                        .width(40.convertSize())
                        .height(46.convertSize())
                )
                Text(
                    text = "作业检查",
                    fontSize = 40.convertSpSize(),
                    modifier = Modifier
                        .padding(start = 9.convertSize()),
                    color = c_047B83
                )
            }
            Box(
                modifier = Modifier
                    .padding(
                        start = 35.convertSize(),
                        end = 35.convertSize(),
                        top = 17.convertSize(),
                        bottom = 35.convertSize()
                    )
                    .fillMaxWidth()
                    .background(c_F2F8F9, shape = RoundedCornerShape(17.convertSize()))
                    .border(3.convertSize(), c_F2F8F9, shape = RoundedCornerShape(17.convertSize()))
            ) {
                var text by remember {
                    mutableStateOf(text)
                }
                BasicTextField(
                    text, onValueChange = {
                        text = it
                        onTextChange(text)
                    }, textStyle = TextStyle(fontSize = 40.convertSpSize()),
                    modifier = Modifier
                        .padding(40.convertSize())
                        .fillMaxWidth()
                )
                if (text.isEmpty()) {
                    Text(
                        "请输入检查内容",
                        fontSize = 40.convertSpSize(),
                        color = c_999999,
                        modifier = Modifier
                            .padding(40.convertSize())
                    )
                }
            }
        }
    }
}

// 课照上传
@Composable
fun ClassPhotoUpload() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 35.convertSize(),
                end = 35.convertSize(),
                top = 35.convertSize()
            )
    ) {
        items(3) {
            ClassPhotoUploadItem(
                modifier = Modifier.padding(bottom = 35.convertSize()),
                name = "王十二",
                images = arrayListOf(
                    "https://gips3.baidu.com/it/u=3886271102,3123389489&fm=3028&app=3028&f=JPEG&fmt=auto?w=1280&h=960",
                    "https://gips0.baidu.com/it/u=1690853528,2506870245&fm=3028&app=3028&f=JPEG&fmt=auto?w=1024&h=1024"
                ),
            ) {

            }
        }
    }
}

// 课照上传item
@Composable
fun ClassPhotoUploadItem(
    modifier: Modifier,
    name: String,
    images: ArrayList<String>,
    uploadClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(29.convertSize()))
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.padding(top = 35.convertSize(), start = 40.convertSize()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(R.drawable.picture), contentDescription = null,
                    modifier = Modifier
                        .width(40.convertSize())
                        .height(46.convertSize())
                )
                Text(
                    text = name,
                    fontSize = 40.convertSpSize(),
                    modifier = Modifier
                        .padding(start = 9.convertSize()),
                    color = c_047B83
                )
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 40.convertSize(),
                        top = 35.convertSize(),
                        bottom = 46.convertSize()
                    ),
                contentPadding = PaddingValues(end = 35.convertSize())
            ) {
                items(images.size) { index ->
                    AsyncImage(
                        model = images[index],
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 35.convertSize())
                            .size(288.convertSize())
                    )
                }
                item {
                    Image(
                        painterResource(R.drawable.add_photo),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(288.convertSize())
                            .clickable {
                                uploadClick()
                            }
                    )
                }
            }
        }
    }
}