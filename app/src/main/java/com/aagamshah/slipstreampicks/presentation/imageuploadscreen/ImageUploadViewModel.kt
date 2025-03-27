package com.aagamshah.slipstreampicks.presentation.imageuploadscreen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.slipstreampicks.domain.usecase.GetUserUseCase
import com.aagamshah.slipstreampicks.domain.usecase.UploadProfileImageUseCase
import com.aagamshah.slipstreampicks.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class ImageUploadViewModel @Inject constructor(
    private val uploadProfileImageUseCase: UploadProfileImageUseCase,
    private val userUseCase: GetUserUseCase
) : ViewModel() {

    private val _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: Boolean get() = _isLoading.value

    private val _imageUrl = mutableStateOf("")
    val imageUrl: String get() = _imageUrl.value

    private val _uploadSuccess = mutableStateOf(false)
    val uploadSuccess: Boolean get() = _uploadSuccess.value

    init {
        viewModelScope.launch {
            val user = userUseCase.invoke()
            _imageUrl.value = user?.profileImage ?: ""
        }
    }

    fun uploadImage(uri: Uri, context: Context) {
        viewModelScope.launch {
            try {
                val compressedFile = compressImage(uri, context)

                uploadProfileImageUseCase.invoke(compressedFile).onEach { result ->
                    when (result) {
                        is Resource.Error -> {
                            _isLoading.value = false
                            _uploadSuccess.value = false
                        }

                        is Resource.Loading -> {
                            _isLoading.value = true
                        }

                        is Resource.Success -> {
                            _isLoading.value = false
                            _uploadSuccess.value = true
                        }
                    }
                }.collect()
            } catch (e: Exception) {
                Log.e("TAGGED", "Image upload failed: ${e.message}", e)
            }
        }
    }

    private fun compressImage(uri: Uri, context: Context): File {
        val originalBitmap =
            BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri))
        var quality = 100
        val byteArrayOutputStream = ByteArrayOutputStream()

        originalBitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream)

        while (byteArrayOutputStream.size() > 1024 * 1024 && quality > 10) {
            byteArrayOutputStream.reset()
            quality -= 10
            originalBitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream)
        }

        val file = File(context.cacheDir, "compressed_upload_image.jpg")
        FileOutputStream(file).use { it.write(byteArrayOutputStream.toByteArray()) }

        originalBitmap.recycle()

        return file
    }

}