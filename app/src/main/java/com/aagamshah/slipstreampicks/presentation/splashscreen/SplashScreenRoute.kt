package com.aagamshah.slipstreampicks.presentation.splashscreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.aagamshah.slipstreampicks.navigation.Route
import com.aagamshah.slipstreampicks.presentation.signuploginscreen.SignUpLoginScreen

fun NavGraphBuilder.splashScreenRoute(navController: NavHostController) {
    composable(Route.SplashScreen.route) {
        SplashScreen(navController)
    }
}