package com.aagamshah.splitstreampicks.domain.repository

import com.aagamshah.splitstreampicks.domain.model.DriverStandingModel

interface DriverStandingRepository {

    suspend fun getDriverStanding(): DriverStandingModel

}