package com.aagamshah.slipstreampicks.data.repositoryimpl

import com.aagamshah.slipstreampicks.data.remote.ApiService
import com.aagamshah.slipstreampicks.domain.model.request.ForgotPasswordRequestModel
import com.aagamshah.slipstreampicks.domain.model.request.ResetPasswordModel
import com.aagamshah.slipstreampicks.domain.model.response.MessageResponseModel
import com.aagamshah.slipstreampicks.domain.repository.ForgotPasswordRepository
import javax.inject.Inject

class ForgotPasswordRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ForgotPasswordRepository {
    override suspend fun forgotPassword(email: String): MessageResponseModel {
        val response = apiService.forgotPassword(ForgotPasswordRequestModel(email))

        return response

    }

    override suspend fun resetPassword(
        email: String,
        otp: Int,
        password: String
    ): MessageResponseModel {
        val model = ResetPasswordModel(email,otp,password)
        val response = apiService.resetPassword(model)
        return response
    }

}