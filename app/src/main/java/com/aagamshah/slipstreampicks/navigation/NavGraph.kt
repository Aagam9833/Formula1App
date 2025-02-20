package com.aagamshah.slipstreampicks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.aagamshah.slipstreampicks.presentation.fantasyscreen.fantasyScreenRoute
import com.aagamshah.slipstreampicks.presentation.homescreen.homeScreenRoute
import com.aagamshah.slipstreampicks.presentation.mainscreen.mainScreenRoute
import com.aagamshah.slipstreampicks.presentation.raceresultscreen.raceResultScreenRoute
import com.aagamshah.slipstreampicks.presentation.seasonscreen.seasonScreenRoute
import com.aagamshah.slipstreampicks.presentation.settingsscreen.settingsScreenRoute
import com.aagamshah.slipstreampicks.presentation.standingsscreen.standingsScreenRoute

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.MainScreen.route) {
        mainScreenRoute(navController)
        homeScreenRoute(navController)
        seasonScreenRoute(navController)
        standingsScreenRoute(navController)
        settingsScreenRoute(navController)
        fantasyScreenRoute(navController)
        raceResultScreenRoute(navController)
    }
}