package com.aagamshah.splitstreampicks.data.repositoryimpl

import com.aagamshah.splitstreampicks.data.remote.ApiService
import com.aagamshah.splitstreampicks.domain.model.NavigationModel
import com.aagamshah.splitstreampicks.domain.repository.NavigationRepository
import javax.inject.Inject

class NavigationRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : NavigationRepository {
    override suspend fun getNavigation(): NavigationModel {
        return apiService.getNavigation()
    }
}