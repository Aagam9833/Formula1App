package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.response.GetFantasyHomeResponseModel

interface GetFantasyHomeRepository {
    suspend fun getFantasyHome(userId: String): GetFantasyHomeResponseModel
}