package com.hx.wangchao.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hx.baselibrary.Constants
import com.hx.baselibrary.mmkv.MMKVUtils
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
    val todoTaskList = mutableStateListOf<TodoTaskItem>().apply {
        add(TodoTaskItem("课堂测验", false))
        add(TodoTaskItem("课堂表现", false))
        add(TodoTaskItem("布置作业", false))
        add(TodoTaskItem("作业检查", false))
        add(TodoTaskItem("课照上传", false))
    }
     // 当前页面选择的第几个
    var selectIndex = mutableStateOf(0)
    // 当前页面选择的名称
    var title = mutableStateOf("")
    // 当前页面用户名
    var userName = mutableStateOf(MMKVUtils.getString(Constants.KEY_USER_NAME)?:"")

}
// 戴航栏目的item
data class NavItem(
    val title: String,
    val icon: Int,
)

// 待办任务的item
data class TodoTaskItem(
    val taskTitle: String,
    val isFinish: Boolean,
)