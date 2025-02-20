package com.aagamshah.slipstreampicks.presentation.raceresultscreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.aagamshah.slipstreampicks.navigation.Route

fun NavGraphBuilder.raceResultScreenRoute(navController: NavHostController) {
    composable("${Route.RaceResultScreen.route}/{roundNumber}") { backStackEntry ->
        val roundNumber = backStackEntry.arguments?.getString("roundNumber") ?: "Unknown"
        RaceResultScreen(navController = navController, roundNumber = roundNumber)
    }
}