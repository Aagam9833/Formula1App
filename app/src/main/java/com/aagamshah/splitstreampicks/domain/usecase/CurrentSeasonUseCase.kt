package com.aagamshah.splitstreampicks.domain.usecase

import com.aagamshah.splitstreampicks.common.Resource
import com.aagamshah.splitstreampicks.domain.model.CurrentSeasonModel
import com.aagamshah.splitstreampicks.domain.repository.CurrentSeasonRepository
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