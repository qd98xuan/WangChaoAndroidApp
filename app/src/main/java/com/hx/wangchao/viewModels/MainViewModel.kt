package com.hx.wangchao.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hx.wangchao.R

/**
 * 主页面ViewModel
 */
class MainViewModel: ViewModel() {
    val navList = mutableStateListOf<NavItem>().apply {
        add(NavItem("待办", R.drawable.todo))
        add(NavItem("课表", R.drawable.course))
        add(NavItem("我的", R.drawable.me))
    }
     // 当前页面选择的第几个
    var selectIndex = mutableStateOf(0)
    // 当前页面选择的名称
    var title = mutableStateOf("")
    // 当前页面用户名
    var userName = mutableStateOf("XXX")
}
data class NavItem(
    val title: String,
    val icon: Int,
)