package com.aagamshah.slipstreampicks.data.repositoryimpl

import com.aagamshah.slipstreampicks.data.remote.ApiService
import com.aagamshah.slipstreampicks.domain.model.response.DriverStandingModel
import com.aagamshah.slipstreampicks.domain.repository.DriverStandingRepository
import javax.inject.Inject

class DriverStandingRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : DriverStandingRepository {
    override suspend fun getDriverStanding(): DriverStandingModel {
        return apiService.getDriverStanding()
    }
}