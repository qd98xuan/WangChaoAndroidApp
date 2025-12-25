package com.hx.wangchao.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.hx.baselibrary.Constants
import com.hx.baselibrary.network.Result
import com.hx.wangchao.repository.ApiRepository
import com.hx.wangchao.utils.AESUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 登录ViewModel
 */
class LoginViewmodel : ViewModel() {
    var loginError = mutableStateOf("")
    var loginSuccess = mutableStateOf(false)

    // 商户编码
    var tenant = mutableStateOf("")
    var username = mutableStateOf("aier")
    var password = mutableStateOf("123456")
    val aesUtils = AESUtils()
    fun login() {
        val encodePassword = aesUtils.encrypt(password.value, Constants.KEY_AES)
        LogUtils.d("加密后的密码",encodePassword)
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.login(tenant.value, username.value, encodePassword).collect {
                when (it) {
                    is Result.Success -> {
                        loginSuccess.value = true
                    }

                    is Result.Error -> {
                        loginError.value = it.msg
                        loginSuccess.value = false
                    }

                    is Result.Loading -> {

                    }
                }
            }
        }
    }
}