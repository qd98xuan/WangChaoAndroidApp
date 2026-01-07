package com.hx.wangchao.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hx.baselibrary.network.BaseResponse
import com.hx.baselibrary.network.Result
import com.hx.wangchao.Entity.DropdownEntity
import com.hx.wangchao.Entity.SpaceEntity
import com.hx.wangchao.Entity.TeacherEntity
import com.hx.wangchao.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * 基础数据ViewModel
 */
class BaseDataViewModel : ViewModel() {
    private val _baseDataStatus = MutableStateFlow<BaseResponse<String>?>(null)
    val baseDatatStatus: StateFlow<BaseResponse<String>?> = _baseDataStatus

    // 老师列表
    val teachers = mutableStateListOf<DropdownEntity>()
    fun getTeacherList() {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.getTeacherList().collect {
                when (it) {
                    is Result.Success<*> -> {
                        teachers.clear()
                        teachers.addAll((it.data as TeacherEntity).map { teacher ->
                            DropdownEntity(
                                teacher.id,
                                teacher.realname
                            )
                        })
                    }

                    is Result.Error -> {
                        _baseDataStatus.value = BaseResponse(it.code, "", it.msg)
                    }

                    is Result.Loading -> {
                        _baseDataStatus.value = BaseResponse(-1, "", "加载中...")
                    }
                }
            }
        }
    }

    // 场地列表
    val spaces = mutableStateListOf<DropdownEntity>()
    fun getSpaceList() {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.getSpaceList().collect {
                when (it) {
                    is Result.Success<*> -> {
                        spaces.clear()
                        spaces.addAll((it.data as SpaceEntity).map { space ->
                            DropdownEntity(
                                space.id,
                                space.title
                            )
                        })
                    }

                    is Result.Error -> {
                        _baseDataStatus.value = BaseResponse(it.code, "", it.msg)
                    }

                    is Result.Loading -> {
                        _baseDataStatus.value = BaseResponse(-1, "", "加载中...")
                    }
                }
            }
        }
    }
}