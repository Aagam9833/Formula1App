package com.aagamshah.slipstreampicks.data.repositoryimpl

import com.aagamshah.slipstreampicks.data.remote.ApiService
import com.aagamshah.slipstreampicks.domain.model.HomeModel
import com.aagamshah.slipstreampicks.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : HomeRepository {
    override suspend fun getHome(): HomeModel {
        return apiService.getHome()
    }
}