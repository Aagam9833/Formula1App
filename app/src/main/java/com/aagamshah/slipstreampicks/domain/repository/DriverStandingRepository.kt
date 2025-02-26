package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.response.DriverStandingModel

interface DriverStandingRepository {

    suspend fun getDriverStanding(): DriverStandingModel

}