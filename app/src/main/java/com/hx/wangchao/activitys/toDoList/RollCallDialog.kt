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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.blankj.utilcode.util.ToastUtils
import com.hx.baselibrary.base.convertSize
import com.hx.baselibrary.base.convertSpSize
import com.hx.wangchao.Entity.AttendanceSubmitEntity
import com.hx.wangchao.R
import com.hx.wangchao.ui.theme.c_047B83
import com.hx.wangchao.ui.theme.c_333333
import com.hx.wangchao.viewModels.MainViewModel
import com.hx.wangchao.viewModels.TodoPageDialogType
import com.hx.wangchao.viewModels.TodoViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow


/**
 * 点名对话框
 */
@Composable
fun RollCallDialog(modifier: Modifier, mainViewModel: MainViewModel, todoViewModel: TodoViewModel) {
    Column(
        modifier = modifier
            .height(1477.convertSize())
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 58.convertSize(), topEnd = 58.convertSize())
            )
    ) {
        Image(
            painterResource(R.drawable.rollcall_title),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 63.convertSize(), start = 63.convertSize())
                .width(173.convertSize())
                .height(98.convertSize())
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            // 监听点名列表结果
            val testDataList = remember {
                todoViewModel.rollCallList
            }
            LaunchedEffect(Unit) {
                todoViewModel.attendanceListState.receiveAsFlow().collect{
                    if (it.code != 200) {
                        ToastUtils.showShort(it.message)
                    }
                }
                // 监听提交点名结果
                todoViewModel.attendanceState.receiveAsFlow().collect {
                    if (it.code != 200) {
                        ToastUtils.showShort(it.message)
                    }
                }
            }

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(testDataList.size) {
                    RollcallItem(
                        modifier = Modifier.padding(
                            start = 69.convertSize(),
                            end = 69.convertSize()
                        ),
                        testDataList.get(it)
                    ) {
                        todoViewModel.attendance(
                            AttendanceSubmitEntity(
                                todoViewModel.activeLessonId, it.accountId,
                                when (it.isSelectedRollCall) {
                                    RollCall.CHUQIN -> "NORMAL"
                                    RollCall.CHIDAO -> "LEAVE"
                                    RollCall.BUKE -> "MAKEUP"
                                    RollCall.QUEQIN -> "ABSENCE"
                                    RollCall.QINGJIA -> ""
                                    else -> {
                                        ""
                                    }
                                }
                            )
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun RollcallItem(
    modifier: Modifier,
    rollCallEntity: RollCallEntity,
    onValueChange: (data: RollCallEntity) -> Unit
) {
    var isSelectedRollCall by remember {
        mutableStateOf(rollCallEntity.isSelectedRollCall)
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(95.convertSize()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = rollCallEntity.name,
            fontSize = 46.convertSpSize(),
            color = c_333333,
        )
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(start = 69.convertSize())
        ) {
            for (item in rollCallEntity.rollCallList) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(95.convertSize())
                        .paint(painterResource(if (isSelectedRollCall.value == item.rollCall.value) item.selectBg else item.normalBg))
                        .clickable {
                            isSelectedRollCall = item.rollCall
                            rollCallEntity.isSelectedRollCall = isSelectedRollCall
                            onValueChange(rollCallEntity)
                        }
                )
            }
        }

    }
}

// 定义一个点名实体类
data class RollCallEntity(
    var name: String,
    val accountId: String,
    var isSelectedRollCall: RollCall = RollCall.CHUQIN,
    val rollCallList: ArrayList<RollCallItemEntity> = arrayListOf(
        RollCallItemEntity(
            RollCall.CHUQIN,
            selectBg = R.drawable.chuqin_selected,
            normalBg = R.drawable.chuqin_normal
        ),
        RollCallItemEntity(
            RollCall.QINGJIA,
            selectBg = R.drawable.qingdjia_selected,
            normalBg = R.drawable.qingjia_normal
        ),
        RollCallItemEntity(
            RollCall.CHIDAO,
            selectBg = R.drawable.chidao_selected,
            normalBg = R.drawable.chidao_normal
        ),
        RollCallItemEntity(
            RollCall.BUKE,
            selectBg = R.drawable.buke_selected,
            normalBg = R.drawable.buke_normal
        ),
        RollCallItemEntity(
            RollCall.QUEQIN,
            selectBg = R.drawable.quexin_selected,
            normalBg = R.drawable.queqin_nornal
        ),
    )
)

data class RollCallItemEntity(
    val rollCall: RollCall,
    val selectBg: Int,
    val normalBg: Int,
)

enum class RollCall(val value: String) {
    CHUQIN("出勤"),
    QINGJIA("请假"),
    CHIDAO("迟到"),
    BUKE("补课"),
    QUEQIN("缺勤")
}