package com.aagamshah.slipstreampicks.data.repositoryimpl

import android.util.Log
import com.aagamshah.slipstreampicks.data.local.dao.UserDao
import com.aagamshah.slipstreampicks.data.local.entity.UserEntity
import com.aagamshah.slipstreampicks.data.local.sharedpreferences.PreferenceManager
import com.aagamshah.slipstreampicks.data.remote.ApiService
import com.aagamshah.slipstreampicks.domain.model.request.SignUpRequestModel
import com.aagamshah.slipstreampicks.domain.model.response.AuthResponseModel
import com.aagamshah.slipstreampicks.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val preferenceManager: PreferenceManager,
) : SignUpRepository {
    override suspend fun signUp(signUpRequestModel: SignUpRequestModel): AuthResponseModel {
        val response = apiService.postSignUp(signUpRequestModel)

        val userEntity = UserEntity(
            id = response.user.id,
            email = response.user.email,
            username = response.user.username,
            profileImage = response.user.profileImage
        )
        userDao.insertUser(userEntity)

        Log.d("TAGGED", userEntity.toString())

        preferenceManager.saveAccessToken(response.token)

        return response
    }

}