package com.aagamshah.splitstreampicks.presentation.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.aagamshah.splitstreampicks.navigation.Route
import com.aagamshah.splitstreampicks.presentation.components.CustomBottomNavigationBar
import com.aagamshah.splitstreampicks.presentation.homescreen.homeScreenRoute
import com.aagamshah.splitstreampicks.presentation.seasonscreen.seasonScreenRoute
import com.aagamshah.splitstreampicks.presentation.settingsscreen.settingsScreenRoute
import com.aagamshah.splitstreampicks.presentation.standingsscreen.standingsScreenRoute

@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel = hiltViewModel()) {

    val navigationData = mainViewModel.navigationModel

    navigationData?.let {
        Scaffold(
            containerColor = Color.Black
        ) { paddingValues ->
            Box(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()) {
                NavHost(
                    navController = navController,
                    startDestination = Route.HomeScreen.route
                ) {
                    homeScreenRoute(navController)
                    seasonScreenRoute(navController)
                    standingsScreenRoute(navController)
                    settingsScreenRoute(navController)
                }
                CustomBottomNavigationBar(
                    navController,
                    navigationData,
                    modifier = Modifier
                        .padding(32.dp)
                        .align(Alignment.BottomCenter),
                )
            }
        }
    }

}