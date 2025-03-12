package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.utils.ApiExceptionHandler
import com.aagamshah.slipstreampicks.utils.Resource
import com.aagamshah.slipstreampicks.domain.model.request.LoginRequestModel
import com.aagamshah.slipstreampicks.domain.model.response.AuthResponseModel
import com.aagamshah.slipstreampicks.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {

    operator fun invoke(loginRequestModel: LoginRequestModel): Flow<Resource<AuthResponseModel>> =
        flow {
            try {
                emit(Resource.Loading())
                val data = loginRepository.login(loginRequestModel)
                emit(Resource.Success(data))
            } catch (e: Exception) {
                emit(Resource.Error(ApiExceptionHandler.handleException(e)))
            }
        }

}