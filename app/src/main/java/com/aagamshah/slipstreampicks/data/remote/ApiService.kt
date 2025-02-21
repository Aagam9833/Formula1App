package com.aagamshah.slipstreampicks.data.remote

import com.aagamshah.slipstreampicks.common.Constants
import com.aagamshah.slipstreampicks.domain.model.ConstructorStandingModel
import com.aagamshah.slipstreampicks.domain.model.CurrentSeasonModel
import com.aagamshah.slipstreampicks.domain.model.DriverStandingModel
import com.aagamshah.slipstreampicks.domain.model.HomeModel
import com.aagamshah.slipstreampicks.domain.model.NavigationModel
import com.aagamshah.slipstreampicks.domain.model.RaceResultModel
import retrofit2.http.GET
import retrofit2.http.Query

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

    @GET(Constants.GET_ROUND_RESULT)
    suspend fun getRoundResults(@Query("round") round: String): RaceResultModel

}