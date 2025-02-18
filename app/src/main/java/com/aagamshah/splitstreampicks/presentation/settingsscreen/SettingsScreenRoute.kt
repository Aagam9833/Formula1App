package com.aagamshah.splitstreampicks.presentation.settingsscreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aagamshah.splitstreampicks.navigation.Route
import com.aagamshah.splitstreampicks.presentation.seasonscreen.SeasonScreen

fun NavGraphBuilder.settingsScreenRoute(navController: NavController) {
    composable(Route.SettingsScreen.route) {
        SettingsScreen(navController)
    }
}