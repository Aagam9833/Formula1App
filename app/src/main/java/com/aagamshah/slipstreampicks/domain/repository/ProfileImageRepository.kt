package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.response.ProfileImageUploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ProfileImageRepository {
    suspend fun uploadProfileImage(file: MultipartBody.Part, userId: RequestBody): ProfileImageUploadResponse
}