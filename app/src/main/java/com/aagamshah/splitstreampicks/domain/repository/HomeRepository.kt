package com.aagamshah.splitstreampicks.domain.repository

import com.aagamshah.splitstreampicks.domain.model.HomeModel

interface HomeRepository {

    suspend fun getHome(): HomeModel

}