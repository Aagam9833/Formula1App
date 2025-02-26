package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.response.CurrentSeasonModel

interface CurrentSeasonRepository {

    suspend fun getCurrentSeason(): CurrentSeasonModel

}