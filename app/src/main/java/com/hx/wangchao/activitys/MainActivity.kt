package com.hx.wangchao.activitys

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModelProvider
import com.hx.baselibrary.base.convertSize
import com.hx.baselibrary.base.convertSpSize
import com.hx.wangchao.R
import com.hx.wangchao.activitys.toDoList.ToDoList
import com.hx.wangchao.ui.theme.c_047B83
import com.hx.wangchao.ui.theme.c_666666
import com.hx.wangchao.utils.main
import com.hx.wangchao.viewModels.MainViewModel

/**
 * 主页面
 */
class MainActivity : BaseAppActivity() {
    val mainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var selectIndex by remember {
                mainViewModel.selectIndex
            }
            var title by remember {
                mainViewModel.title
            }
            LaunchedEffect(selectIndex) {
                title = mainViewModel.navList[selectIndex].title
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                StatusBar(modifier = Modifier, title)
                ToDoList(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = true)
                )

                Navigation(
                    modifier = Modifier
                        .height(158.convertSize()),
                    selectColor = c_047B83,
                    defaultColor = c_666666
                ) {
                    selectIndex = it
                }
            }
        }
    }

    /**
     * 状态栏
     */
    @Composable
    fun StatusBar(modifier: Modifier, title: String) {
        Box(
            modifier = modifier
                .background(color = c_047B83)
                .fillMaxWidth()
                .height(156.convertSize())
        ) {
            val userName by remember {
                mainViewModel.userName
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