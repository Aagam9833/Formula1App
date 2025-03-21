package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.request.SaveFantasyTeamRequestModel
import com.aagamshah.slipstreampicks.domain.model.response.MessageResponseModel

interface SaveFantasyTeamRepository {

    suspend fun saveTeam(saveFantasyTeamRequestModel: SaveFantasyTeamRequestModel): MessageResponseModel

}