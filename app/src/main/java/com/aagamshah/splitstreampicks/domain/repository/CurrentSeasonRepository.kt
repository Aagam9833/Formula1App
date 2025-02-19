package com.aagamshah.splitstreampicks.domain.repository

import com.aagamshah.splitstreampicks.domain.model.CurrentSeasonModel

interface CurrentSeasonRepository {

    suspend fun getCurrentSeason(): CurrentSeasonModel

}