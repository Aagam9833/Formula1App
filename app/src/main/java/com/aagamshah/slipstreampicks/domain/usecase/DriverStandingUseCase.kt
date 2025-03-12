package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.utils.ApiExceptionHandler
import com.aagamshah.slipstreampicks.utils.Resource
import com.aagamshah.slipstreampicks.domain.model.response.DriverStandingModel
import com.aagamshah.slipstreampicks.domain.repository.DriverStandingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DriverStandingUseCase @Inject constructor(
    private val driverStandingRepository: DriverStandingRepository,
) {

    operator fun invoke(): Flow<Resource<DriverStandingModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = driverStandingRepository.getDriverStanding()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(ApiExceptionHandler.handleException(e)))
        }
    }

}