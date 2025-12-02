package com.hx.photoselect

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.util.jar.Manifest

@SuppressLint("RestrictedApi")
class PhotoSelectActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var photoUri: Uri? = null
        val pickerMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->

        }
        val requestTakePhotoPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {

                } else {
                    Toast.makeText(this, "权限被拒绝，无法使用相机功能", Toast.LENGTH_SHORT).show()
                }
        }
        val takePhoto = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
                if (success) {

                } else {
                    Toast.makeText(this, "拍照失败", Toast.LENGTH_SHORT).show()
                }

        }
        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(

                ) {
                    Text("打开相册", modifier = Modifier.clickable {
                        pickerMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
                    })
                    Text("拍照", modifier = Modifier.clickable {
                        val hasCamera = ContextCompat.checkSelfPermission(this@PhotoSelectActivity,
                            android.Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                        if (hasCamera) {
                            val fileName = "photo_${System.currentTimeMillis()}.jpg"
                            val storageDir = cacheDir
                            File.createTempFile(fileName,".jpg", storageDir).also { photoFile ->
                                photoUri = FileProvider.getUriForFile(
                                    this@PhotoSelectActivity,
                                    "com.hx.photoselect.fileprovider",
                                    photoFile
                                )
                            }
                            takePhoto.launch(photoUri)
                        } else {
                            requestTakePhotoPermission.launch(android.Manifest.permission.CAMERA)
                        }
                    })


                }
            }
        }
    }
}
