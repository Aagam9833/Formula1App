package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.response.RaceResultModel

interface RaceResultRepository {

    suspend fun getRaceResult(round: String): RaceResultModel

}