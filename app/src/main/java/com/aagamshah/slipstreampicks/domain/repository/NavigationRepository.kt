package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.NavigationModel

interface NavigationRepository {

    suspend fun getNavigation(): NavigationModel

}