package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.common.Resource
import com.aagamshah.slipstreampicks.domain.model.DriverStandingModel
import com.aagamshah.slipstreampicks.domain.repository.DriverStandingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DriverStandingUseCase @Inject constructor(
    private val driverStandingRepository: DriverStandingRepository,
) {

    operator fun invoke(): Flow<Resource<DriverStandingModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = driverStandingRepository.getDriverStanding()
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong!"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Check your internet connection"))
        }
    }

}