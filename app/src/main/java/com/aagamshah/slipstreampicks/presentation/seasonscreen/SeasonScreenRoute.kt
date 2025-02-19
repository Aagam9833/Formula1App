package com.aagamshah.slipstreampicks.presentation.seasonscreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aagamshah.slipstreampicks.navigation.Route

fun NavGraphBuilder.seasonScreenRoute(navController: NavController) {
    composable(Route.SeasonScreen.route) {
        SeasonScreen(navController)
    }
}