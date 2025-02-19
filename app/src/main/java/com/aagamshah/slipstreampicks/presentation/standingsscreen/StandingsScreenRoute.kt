package com.aagamshah.slipstreampicks.presentation.standingsscreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aagamshah.slipstreampicks.navigation.Route

fun NavGraphBuilder.standingsScreenRoute(navController: NavController) {
    composable(Route.StandingsScreen.route) {
        StandingsScreen(navController)
    }
}