package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.utils.ApiExceptionHandler
import com.aagamshah.slipstreampicks.utils.Resource
import com.aagamshah.slipstreampicks.domain.model.request.SignUpRequestModel
import com.aagamshah.slipstreampicks.domain.model.response.AuthResponseModel
import com.aagamshah.slipstreampicks.domain.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository,
) {
    operator fun invoke(signUpRequestModel: SignUpRequestModel): Flow<Resource<AuthResponseModel>> =
        flow {
            try {
                emit(Resource.Loading())
                val data = signUpRepository.signUp(signUpRequestModel)
                emit(Resource.Success(data))
            } catch (e: Exception) {
                emit(Resource.Error(ApiExceptionHandler.handleException(e)))
            }
        }

}