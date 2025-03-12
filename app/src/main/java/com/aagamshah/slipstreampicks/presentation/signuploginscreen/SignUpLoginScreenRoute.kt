package com.aagamshah.slipstreampicks.presentation.signuploginscreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.aagamshah.slipstreampicks.navigation.Route

fun NavGraphBuilder.signUpLoginScreenRoute(navController: NavHostController) {
    composable(Route.SignUpLoginScreen.route) {
        SignUpLoginScreen(navController)
    }
}