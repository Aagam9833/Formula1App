package com.aagamshah.slipstreampicks.data.repositoryimpl

import com.aagamshah.slipstreampicks.data.remote.ApiService
import com.aagamshah.slipstreampicks.domain.model.request.SaveFantasyTeamRequestModel
import com.aagamshah.slipstreampicks.domain.model.response.MessageResponseModel
import com.aagamshah.slipstreampicks.domain.repository.SaveFantasyTeamRepository
import javax.inject.Inject

class SaveFantasyTeamRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : SaveFantasyTeamRepository {
    override suspend fun saveTeam(saveFantasyTeamRequestModel: SaveFantasyTeamRequestModel): MessageResponseModel {
        return apiService.setFantasyTeam(saveFantasyTeamRequestModel)
    }
}