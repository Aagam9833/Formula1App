package com.aagamshah.splitstreampicks.presentation.seasonscreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun SeasonScreen(navController: NavController, seasonViewModel: SeasonViewModel = hiltViewModel()) {


    Text("SEASON")

}