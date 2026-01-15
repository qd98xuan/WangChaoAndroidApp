package com.hx.wangchao.activitys.classTable

import android.R
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.hx.baselibrary.base.convertSize
import com.hx.baselibrary.base.convertSpSize
import com.hx.wangchao.Entity.CalendarItem
import com.hx.wangchao.ui.theme.c_047B83
import com.hx.wangchao.ui.theme.c_333333
import com.hx.wangchao.ui.theme.c_666666
import com.hx.wangchao.ui.theme.c_9BCACD
import com.hx.wangchao.ui.theme.c_D5D5D5
import com.hx.wangchao.ui.theme.c_E5E5E5
import com.hx.wangchao.viewModels.ClassTableViewModel
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * 课表日历
 */
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ClassCalendar(modifier: Modifier,classTableViewModel: ClassTableViewModel) {
    val calendarList = remember {
        mutableStateListOf<String>()
    }
    LaunchedEffect(Unit) {
        val weekNames = listOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")
        val calendar = Calendar.getInstance()
        for (i in 0 until 7) {
            calendarList.add(weekNames.get(calendar.get(Calendar.DAY_OF_WEEK) - 1))
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
    }
    Column(
        modifier = modifier.background(
            color = Color.White,
            shape = RoundedCornerShape(29.convertSize())
        )
    ) {
        Text(
            text = "展示从明天开始的后七天课程安排",
            color = c_666666,
            fontSize = 40.convertSpSize(),
            modifier = Modifier.padding(start = 46.convertSize(), top = 35.convertSize())
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 35.convertSize()),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "时间",
                color = c_333333,
                fontSize = 40.convertSpSize(),
            )
            calendarList.forEach {
                Text(
                    text = it,
                    color = c_333333,
                    fontSize = 40.convertSpSize(),
                )
            }
        }
        val timeList = remember {
            classTableViewModel.timeList
        }
        timeList.forEach {
            CalendarItem(Modifier, time = it[0].time, calendarList = it)
            Box(
                modifier = Modifier
                    .padding(start = 35.convertSize(), end = 35.convertSize())
                    .fillMaxWidth()
                    .height(2.convertSize())
                    .background(
                        c_E5E5E5
                    )
            )
        }

    }
}

@Composable
fun CalendarItem(modifier: Modifier, time: String, calendarList: ArrayList<CalendarItem>) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Text(
            text = time,
            color = Color.Black,
            fontSize = 35.convertSpSize(),
            modifier = Modifier
                .padding(start = 35.convertSize())
                .weight(1f)
                .height(147.convertSize())
        )
        for (i in 0 until 7) {
            val day = calendarList.find { it.index == i }
            if (day != null) {
                Box(
                    modifier = Modifier
                        .padding(
                            start = if (i == 0) {
                                14.convertSize()
                            } else {
                                7.convertSize()
                            },
                            end = 7.convertSize(),
                            top = 7.convertSize(),
                            bottom = 7.convertSize()
                        )
                        .background(
                            brush = Brush.verticalGradient(
                                colors = if (!day.isDelay) listOf(
                                    c_9BCACD, Color.White
                                ) else listOf(
                                    c_D5D5D5, Color.White
                                )
                            ), shape = RoundedCornerShape(17.convertSize())
                        )
                        .weight(1f)
                        .height(147.convertSize())
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 12.convertSize()),
                        text = day.name,
                        color = if (!day.isDelay) c_047B83 else c_666666,
                        fontSize = 40.convertSpSize(),
                    )
                }
            } else {
                // 空位置
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(147.convertSize())
                )
            }
        }
    }
}