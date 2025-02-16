package com.aagamshah.splitstreampicks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.aagamshah.splitstreampicks.presentation.homescreen.homeScreenRoute

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.HomeScreen.route) {
        homeScreenRoute(navController)
    }
}