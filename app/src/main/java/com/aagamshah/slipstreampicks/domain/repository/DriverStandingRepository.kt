package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.DriverStandingModel

interface DriverStandingRepository {

    suspend fun getDriverStanding(): DriverStandingModel

}