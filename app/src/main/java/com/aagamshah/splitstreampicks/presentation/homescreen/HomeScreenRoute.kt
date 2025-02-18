package com.aagamshah.splitstreampicks.presentation.homescreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aagamshah.splitstreampicks.navigation.Route

fun NavGraphBuilder.homeScreenRoute(navController: NavController) {
    composable(Route.HomeScreen.route) {
        HomeScreen(navController = navController)
    }
}