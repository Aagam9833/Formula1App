package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.common.Resource
import com.aagamshah.slipstreampicks.domain.model.CurrentSeasonModel
import com.aagamshah.slipstreampicks.domain.repository.CurrentSeasonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CurrentSeasonUseCase @Inject constructor(
    private val currentSeasonRepository: CurrentSeasonRepository,
) {

    operator fun invoke(): Flow<Resource<CurrentSeasonModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = currentSeasonRepository.getCurrentSeason()
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong!"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Check your internet connection"))
        }
    }

}