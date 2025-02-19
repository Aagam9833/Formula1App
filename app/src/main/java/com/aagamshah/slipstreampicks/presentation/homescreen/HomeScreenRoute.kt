package com.aagamshah.slipstreampicks.presentation.homescreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aagamshah.slipstreampicks.navigation.Route

fun NavGraphBuilder.homeScreenRoute(navController: NavController) {
    composable(Route.HomeScreen.route) {
        HomeScreen(navController = navController)
    }
}