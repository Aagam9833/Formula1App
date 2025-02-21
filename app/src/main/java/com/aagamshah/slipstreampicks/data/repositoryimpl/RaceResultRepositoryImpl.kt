package com.aagamshah.slipstreampicks.data.repositoryimpl

import com.aagamshah.slipstreampicks.data.remote.ApiService
import com.aagamshah.slipstreampicks.domain.model.RaceResultModel
import com.aagamshah.slipstreampicks.domain.repository.RaceResultRepository
import javax.inject.Inject

class RaceResultRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : RaceResultRepository {
    override suspend fun getRaceResult(round: String): RaceResultModel {
        return apiService.getRoundResults(round)
    }

}