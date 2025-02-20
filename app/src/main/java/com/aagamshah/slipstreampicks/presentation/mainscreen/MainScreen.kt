package com.aagamshah.slipstreampicks.presentation.mainscreen

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
import androidx.navigation.compose.rememberNavController
import com.aagamshah.slipstreampicks.navigation.Route
import com.aagamshah.slipstreampicks.presentation.components.CustomBottomNavigationBar
import com.aagamshah.slipstreampicks.presentation.fantasyscreen.fantasyScreenRoute
import com.aagamshah.slipstreampicks.presentation.homescreen.homeScreenRoute
import com.aagamshah.slipstreampicks.presentation.seasonscreen.seasonScreenRoute
import com.aagamshah.slipstreampicks.presentation.settingsscreen.settingsScreenRoute
import com.aagamshah.slipstreampicks.presentation.standingsscreen.standingsScreenRoute

@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel = hiltViewModel()) {

    val navigationData = mainViewModel.navigationModel

    navigationData?.let {
        Scaffold(
            containerColor = Color.Black
        ) { paddingValues ->

            val childNavController = rememberNavController()

            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                NavHost(
                    navController = childNavController,
                    startDestination = Route.HomeScreen.route
                ) {
                    homeScreenRoute(navController)
                    seasonScreenRoute(navController)
                    standingsScreenRoute(navController)
                    settingsScreenRoute(navController)
                    fantasyScreenRoute(navController)
                }
                CustomBottomNavigationBar(
                    childNavController,
                    navigationData,
                    modifier = Modifier
                        .padding(32.dp)
                        .align(Alignment.BottomCenter),
                )
            }
        }
    }

}