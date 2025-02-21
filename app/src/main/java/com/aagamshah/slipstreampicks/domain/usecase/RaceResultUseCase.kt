package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.common.Resource
import com.aagamshah.slipstreampicks.domain.model.RaceResultModel
import com.aagamshah.slipstreampicks.domain.repository.RaceResultRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RaceResultUseCase @Inject constructor(
    private val raceResultRepository: RaceResultRepository,
) {

    operator fun invoke(round: String): Flow<Resource<RaceResultModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = raceResultRepository.getRaceResult(round)
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong!"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Check your internet connection"))
        }
    }

}