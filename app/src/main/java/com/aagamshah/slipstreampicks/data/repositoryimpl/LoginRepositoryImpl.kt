package com.aagamshah.slipstreampicks.data.repositoryimpl

import com.aagamshah.slipstreampicks.data.local.dao.UserDao
import com.aagamshah.slipstreampicks.data.local.entity.UserEntity
import com.aagamshah.slipstreampicks.data.local.sharedpreferences.PreferenceManager
import com.aagamshah.slipstreampicks.data.remote.ApiService
import com.aagamshah.slipstreampicks.domain.model.request.LoginRequestModel
import com.aagamshah.slipstreampicks.domain.model.response.AuthResponseModel
import com.aagamshah.slipstreampicks.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val preferenceManager: PreferenceManager,
) : LoginRepository {
    override suspend fun login(loginRequestModel: LoginRequestModel): AuthResponseModel {
        val response = apiService.postLogin(loginRequestModel)

        val userEntity = UserEntity(
            id = response.user.id,
            email = response.user.email,
            username = response.user.username,
        )
        userDao.insertUser(userEntity)

        preferenceManager.saveAccessToken(response.token)

        return response
    }

}