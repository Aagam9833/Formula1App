package com.aagamshah.slipstreampicks.presentation.standingsscreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun StandingsScreen(
    navController: NavController,
    standingsViewModel: StandingsViewModel = hiltViewModel(),
) {

    Text("STANDINGS")

}