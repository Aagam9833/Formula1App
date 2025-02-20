package com.aagamshah.slipstreampicks.presentation.raceresultscreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun RaceResultScreen(
    navController: NavController,
    raceResultViewModel: RaceResultViewModel = hiltViewModel(),
    roundNumber: String,
) {

    Text("ROUND $roundNumber")

}