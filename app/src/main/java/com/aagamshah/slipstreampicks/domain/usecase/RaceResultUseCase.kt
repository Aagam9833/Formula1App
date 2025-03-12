package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.utils.ApiExceptionHandler
import com.aagamshah.slipstreampicks.utils.Resource
import com.aagamshah.slipstreampicks.domain.model.response.RaceResultModel
import com.aagamshah.slipstreampicks.domain.repository.RaceResultRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RaceResultUseCase @Inject constructor(
    private val raceResultRepository: RaceResultRepository,
) {

    operator fun invoke(round: String): Flow<Resource<RaceResultModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = raceResultRepository.getRaceResult(round)
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(ApiExceptionHandler.handleException(e)))
        }
    }

}