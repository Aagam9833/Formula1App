package com.aagamshah.slipstreampicks.data.repositoryimpl

import com.aagamshah.slipstreampicks.data.remote.ApiService
import com.aagamshah.slipstreampicks.domain.model.response.NavigationModel
import com.aagamshah.slipstreampicks.domain.repository.NavigationRepository
import javax.inject.Inject

class NavigationRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : NavigationRepository {
    override suspend fun getNavigation(): NavigationModel {
        return apiService.getNavigation()
    }
}