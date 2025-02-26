package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.common.Resource
import com.aagamshah.slipstreampicks.domain.model.response.ConstructorStandingModel
import com.aagamshah.slipstreampicks.domain.repository.ConstructorStandingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ConstructorStandingUseCase @Inject constructor(
    private val constructorStandingRepository: ConstructorStandingRepository,
) {

    operator fun invoke(): Flow<Resource<ConstructorStandingModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = constructorStandingRepository.getConstructorStanding()
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong!"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Check your internet connection"))
        }
    }

}