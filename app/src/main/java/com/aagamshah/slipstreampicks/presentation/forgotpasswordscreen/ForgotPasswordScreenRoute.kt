package com.aagamshah.slipstreampicks.presentation.forgotpasswordscreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.aagamshah.slipstreampicks.navigation.Route

fun NavGraphBuilder.forgotPasswordScreenRoute(navController: NavHostController) {
    composable(Route.ForgotPasswordScreen.route) {
        ForgotPasswordScreen(navController)
    }
}