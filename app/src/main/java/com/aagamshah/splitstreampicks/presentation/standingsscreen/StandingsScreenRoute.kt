package com.aagamshah.splitstreampicks.presentation.standingsscreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aagamshah.splitstreampicks.navigation.Route
import com.aagamshah.splitstreampicks.presentation.settingsscreen.SettingsScreen

fun NavGraphBuilder.standingsScreenRoute(navController: NavController) {
    composable(Route.StandingsScreen.route) {
        StandingsScreen(navController)
    }
}