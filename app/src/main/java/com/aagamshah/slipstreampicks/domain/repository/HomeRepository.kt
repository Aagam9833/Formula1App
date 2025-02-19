package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.HomeModel

interface HomeRepository {

    suspend fun getHome(): HomeModel

}