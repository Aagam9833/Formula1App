package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.response.NavigationModel

interface NavigationRepository {

    suspend fun getNavigation(): NavigationModel

}