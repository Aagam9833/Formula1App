package com.aagamshah.slipstreampicks.presentation.fantasyscreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aagamshah.slipstreampicks.navigation.Route

fun NavGraphBuilder.fantasyScreenRoute(navController: NavController) {
    composable(Route.FantasyScreen.route) {
        FantasyScreen(navController = navController)
    }
}