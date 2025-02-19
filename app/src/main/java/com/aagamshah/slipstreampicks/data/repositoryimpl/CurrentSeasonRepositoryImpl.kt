package com.aagamshah.slipstreampicks.data.repositoryimpl

import com.aagamshah.slipstreampicks.data.remote.ApiService
import com.aagamshah.slipstreampicks.domain.model.CurrentSeasonModel
import com.aagamshah.slipstreampicks.domain.repository.CurrentSeasonRepository
import javax.inject.Inject

class CurrentSeasonRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : CurrentSeasonRepository {
    override suspend fun getCurrentSeason(): CurrentSeasonModel {
        return apiService.getCurrentSeason()
    }

}