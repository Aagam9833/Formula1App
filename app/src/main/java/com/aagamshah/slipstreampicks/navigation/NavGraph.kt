package com.aagamshah.slipstreampicks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.aagamshah.slipstreampicks.presentation.mainscreen.mainScreenRoute

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.MainScreen.route) {
        mainScreenRoute(navController)
    }
}