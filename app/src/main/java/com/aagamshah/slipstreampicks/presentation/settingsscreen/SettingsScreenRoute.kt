package com.aagamshah.slipstreampicks.presentation.settingsscreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aagamshah.slipstreampicks.navigation.Route

fun NavGraphBuilder.settingsScreenRoute(navController: NavController) {
    composable(Route.SettingsScreen.route) {
        SettingsScreen(navController)
    }
}