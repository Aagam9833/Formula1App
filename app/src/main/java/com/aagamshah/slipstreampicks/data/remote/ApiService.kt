package com.aagamshah.slipstreampicks.data.remote

import com.aagamshah.slipstreampicks.common.Constants
import com.aagamshah.slipstreampicks.domain.model.ConstructorStandingModel
import com.aagamshah.slipstreampicks.domain.model.CurrentSeasonModel
import com.aagamshah.slipstreampicks.domain.model.DriverStandingModel
import com.aagamshah.slipstreampicks.domain.model.HomeModel
import com.aagamshah.slipstreampicks.domain.model.NavigationModel
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.GET_HOME)
    suspend fun getHome(): HomeModel

    @GET(Constants.GET_DRIVER_STANDING)
    suspend fun getDriverStanding(): DriverStandingModel

    @GET(Constants.NAVIGATION)
    suspend fun getNavigation(): NavigationModel

    @GET(Constants.CURRENT_SEASON)
    suspend fun getCurrentSeason(): CurrentSeasonModel

    @GET(Constants.GET_CONSTRUCTOR_STANDINGS)
    suspend fun getConstructorStandings(): ConstructorStandingModel

}