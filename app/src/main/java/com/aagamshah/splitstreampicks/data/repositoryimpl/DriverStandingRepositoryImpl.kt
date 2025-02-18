package com.aagamshah.splitstreampicks.data.repositoryimpl

import com.aagamshah.splitstreampicks.data.remote.ApiService
import com.aagamshah.splitstreampicks.domain.model.DriverStandingModel
import com.aagamshah.splitstreampicks.domain.repository.DriverStandingRepository
import javax.inject.Inject

class DriverStandingRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : DriverStandingRepository {
    override suspend fun getDriverStanding(): DriverStandingModel {
        return apiService.getDriverStanding()
    }
}