package com.aagamshah.slipstreampicks.data.repositoryimpl

import com.aagamshah.slipstreampicks.data.remote.ApiService
import com.aagamshah.slipstreampicks.domain.model.response.GetFantasyHomeResponseModel
import com.aagamshah.slipstreampicks.domain.repository.GetFantasyHomeRepository
import javax.inject.Inject

class GetFantasyHomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : GetFantasyHomeRepository {
    override suspend fun getFantasyHome(userId: String): GetFantasyHomeResponseModel {
        return apiService.getFantasyHome(userId)
    }

}