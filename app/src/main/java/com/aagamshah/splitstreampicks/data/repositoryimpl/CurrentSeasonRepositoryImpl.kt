package com.aagamshah.splitstreampicks.data.repositoryimpl

import com.aagamshah.splitstreampicks.data.remote.ApiService
import com.aagamshah.splitstreampicks.domain.model.CurrentSeasonModel
import com.aagamshah.splitstreampicks.domain.model.DriverStandingModel
import com.aagamshah.splitstreampicks.domain.repository.CurrentSeasonRepository
import com.aagamshah.splitstreampicks.domain.repository.DriverStandingRepository
import javax.inject.Inject

class CurrentSeasonRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : CurrentSeasonRepository {
    override suspend fun getCurrentSeason(): CurrentSeasonModel {
        return apiService.getCurrentSeason()
    }

}