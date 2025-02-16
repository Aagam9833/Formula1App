package com.aagamshah.splitstreampicks.navigation

sealed class Route(val route: String) {

    data object HomeScreen : Route("home_screen")

}