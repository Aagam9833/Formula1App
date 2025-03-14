package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.domain.model.response.ProfileImageUploadResponse
import com.aagamshah.slipstreampicks.domain.repository.ProfileImageRepository
import com.aagamshah.slipstreampicks.domain.repository.UserRepository
import com.aagamshah.slipstreampicks.utils.ApiExceptionHandler
import com.aagamshah.slipstreampicks.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class UploadProfileImageUseCase @Inject constructor(
    private val profileImageRepository: ProfileImageRepository,
    private val userRepository: UserRepository
) {

    operator fun invoke(file: File): Flow<Resource<ProfileImageUploadResponse>> = flow {
        try {
            emit(Resource.Loading())
            val requestFile = file.asRequestBody("image/*".toMediaType())
            val imagePart = MultipartBody.Part.createFormData("image", file.name, requestFile)

            val userId = userRepository.getUser()?.id

            if (userId != null) {
                val userIdPart: RequestBody = userId.toRequestBody("text/plain".toMediaType())
                val data = profileImageRepository.uploadProfileImage(imagePart, userIdPart)
                emit(Resource.Success(data))
            } else {
                emit(Resource.Error("User not logged in"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(ApiExceptionHandler.handleException(e)))
        }
    }

}