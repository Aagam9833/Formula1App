package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.domain.model.response.MessageResponseModel
import com.aagamshah.slipstreampicks.domain.repository.ForgotPasswordRepository
import com.aagamshah.slipstreampicks.utils.ApiExceptionHandler
import com.aagamshah.slipstreampicks.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val forgotPasswordRepository: ForgotPasswordRepository
) {

    operator fun invoke(email: String): Flow<Resource<MessageResponseModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = forgotPasswordRepository.forgotPassword(email)
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(ApiExceptionHandler.handleException(e)))
        }
    }

    operator fun invoke(
        email: String,
        otp: Int,
        password: String
    ): Flow<Resource<MessageResponseModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = forgotPasswordRepository.resetPassword(email, otp, password)
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(ApiExceptionHandler.handleException(e)))
        }
    }

}