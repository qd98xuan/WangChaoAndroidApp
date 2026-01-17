package com.hx.wangchao.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hx.baselibrary.network.Result
import com.hx.wangchao.Entity.OSSPresignEntity
import com.hx.wangchao.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * OSS相关的ViewModel
 */
class OSSViewModel: ViewModel() {

    // oss上传结果消息通道
    val ossMessageChannel = Channel<String>(Channel.BUFFERED)

    // 生成的oss连接，在文件上传成功后返回
    val ossUrl = mutableStateOf("")

    fun uploadFileToOSS(objectName: String,file: File) {
        viewModelScope.launch(Dispatchers.IO) {
            ApiRepository.getPresignedPutUrl(objectName).collect {
                when(it) {
                    is Result.Success<*> -> {
                        val presignedObject = it.data as OSSPresignEntity
                        val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(),file)
                        val body = MultipartBody.Part.createFormData("file",objectName,requestFile)
                        ApiRepository.uploadFileToOSS(presignedObject.presignedPutUrl,body).collect {
                            when(it) {
                                is Result.Success<*> -> {
                                    ossUrl.value = presignedObject.getUrl
                                }
                                is Result.Loading -> {

                                }
                                is Result.Error -> {
                                    ossMessageChannel.send(it.msg)
                                }
                            }
                        }

                    }
                    is Result.Loading -> {

                    }
                    is Result.Error ->  {
                        ossMessageChannel.send(it.msg)
                    }
                }
            }
        }
    }
}