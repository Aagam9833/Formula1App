package com.aagamshah.slipstreampicks.presentation.imageuploadscreen

import android.content.Context
import android.net.Uri
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
import java.io.File
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
            uploadProfileImageUseCase.invoke(convertUriToFile(uri, context)).onEach { result ->
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
        }
    }

    private fun convertUriToFile(uri: Uri, context: Context): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.cacheDir, "upload_image.jpg")
        inputStream.use { input ->
            file.outputStream().use { output ->
                input?.copyTo(output)
            }
        }
        return file
    }

}