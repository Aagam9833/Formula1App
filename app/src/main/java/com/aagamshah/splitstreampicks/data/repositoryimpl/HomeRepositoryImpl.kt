package com.aagamshah.splitstreampicks.data.repositoryimpl

import com.aagamshah.splitstreampicks.data.remote.ApiService
import com.aagamshah.splitstreampicks.domain.model.HomeModel
import com.aagamshah.splitstreampicks.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : HomeRepository {
    override suspend fun getHome(): HomeModel {
        return apiService.getHome()
    }
}