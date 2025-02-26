package com.aagamshah.slipstreampicks.data.repositoryimpl

import com.aagamshah.slipstreampicks.data.remote.ApiService
import com.aagamshah.slipstreampicks.domain.model.response.ConstructorStandingModel
import com.aagamshah.slipstreampicks.domain.repository.ConstructorStandingRepository
import javax.inject.Inject

class ConstructorStandingRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ConstructorStandingRepository {
    override suspend fun getConstructorStanding(): ConstructorStandingModel {
        return apiService.getConstructorStandings()
    }

}