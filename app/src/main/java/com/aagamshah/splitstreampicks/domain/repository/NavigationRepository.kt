package com.aagamshah.splitstreampicks.domain.repository

import com.aagamshah.splitstreampicks.domain.model.NavigationModel

interface NavigationRepository {

    suspend fun getNavigation(): NavigationModel

}