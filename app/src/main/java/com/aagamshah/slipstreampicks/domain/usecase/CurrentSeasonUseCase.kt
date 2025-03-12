package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.utils.ApiExceptionHandler
import com.aagamshah.slipstreampicks.utils.Resource
import com.aagamshah.slipstreampicks.domain.model.response.CurrentSeasonModel
import com.aagamshah.slipstreampicks.domain.repository.CurrentSeasonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrentSeasonUseCase @Inject constructor(
    private val currentSeasonRepository: CurrentSeasonRepository,
) {

    operator fun invoke(): Flow<Resource<CurrentSeasonModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = currentSeasonRepository.getCurrentSeason()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(ApiExceptionHandler.handleException(e)))
        }
    }

}