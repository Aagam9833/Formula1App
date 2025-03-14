package com.aagamshah.slipstreampicks.data.repositoryimpl

import android.util.Log
import com.aagamshah.slipstreampicks.data.local.dao.UserDao
import com.aagamshah.slipstreampicks.data.remote.ApiService
import com.aagamshah.slipstreampicks.domain.model.response.ProfileImageUploadResponse
import com.aagamshah.slipstreampicks.domain.repository.ProfileImageRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class ProfileImageRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) :
    ProfileImageRepository {
    override suspend fun uploadProfileImage(
        file: MultipartBody.Part,
        userId: RequestBody
    ): ProfileImageUploadResponse {

        val response = apiService.uploadProfileImage(file, userId)

        userDao.updateProfileImage(response.profileImage)

        Log.d("TAGGED", userDao.getUser().toString())

        return response
    }
}