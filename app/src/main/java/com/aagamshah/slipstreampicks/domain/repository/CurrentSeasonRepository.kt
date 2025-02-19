package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.CurrentSeasonModel

interface CurrentSeasonRepository {

    suspend fun getCurrentSeason(): CurrentSeasonModel

}