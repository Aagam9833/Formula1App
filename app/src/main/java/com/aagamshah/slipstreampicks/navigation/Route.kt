package com.aagamshah.slipstreampicks.navigation

sealed class Route(val route: String) {
    data object MainScreen : Route("main_screen")
    data object HomeScreen : Route("home_screen")
    data object SeasonScreen : Route("season_screen")
    data object StandingsScreen : Route("standings_screen")
    data object SettingsScreen : Route("settings_screen")
    data object FantasyScreen : Route("fantasy_screen")
    data object RaceResultScreen : Route("race_result_screen")
}