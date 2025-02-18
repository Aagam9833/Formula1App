package com.aagamshah.splitstreampicks.presentation.mainscreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.aagamshah.splitstreampicks.navigation.Route

fun NavGraphBuilder.mainScreenRoute(navController: NavHostController) {
    composable(Route.MainScreen.route) {
        MainScreen(navController)
    }
}