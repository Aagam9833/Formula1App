package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.response.ConstructorStandingModel

interface ConstructorStandingRepository {

    suspend fun getConstructorStanding(): ConstructorStandingModel

}