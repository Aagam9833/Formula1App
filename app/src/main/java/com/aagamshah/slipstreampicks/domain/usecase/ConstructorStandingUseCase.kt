package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.utils.ApiExceptionHandler
import com.aagamshah.slipstreampicks.utils.Resource
import com.aagamshah.slipstreampicks.domain.model.response.ConstructorStandingModel
import com.aagamshah.slipstreampicks.domain.repository.ConstructorStandingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConstructorStandingUseCase @Inject constructor(
    private val constructorStandingRepository: ConstructorStandingRepository,
) {

    operator fun invoke(): Flow<Resource<ConstructorStandingModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = constructorStandingRepository.getConstructorStanding()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(ApiExceptionHandler.handleException(e)))
        }
    }

}