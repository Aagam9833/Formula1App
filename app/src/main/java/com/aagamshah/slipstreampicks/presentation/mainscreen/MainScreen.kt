package com.aagamshah.slipstreampicks.presentation.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.aagamshah.slipstreampicks.presentation.components.ErrorPopUp
import com.aagamshah.slipstreampicks.presentation.components.LoadingAnimation
import com.aagamshah.slipstreampicks.presentation.fantasyscreen.fantasyScreenRoute
import com.aagamshah.slipstreampicks.presentation.homescreen.homeScreenRoute
import com.aagamshah.slipstreampicks.presentation.seasonscreen.seasonScreenRoute
import com.aagamshah.slipstreampicks.presentation.settingsscreen.settingsScreenRoute
import com.aagamshah.slipstreampicks.presentation.standingsscreen.standingsScreenRoute

@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel = hiltViewModel()) {

    val navigationData = mainViewModel.navigationModel
    val isLoading = mainViewModel.isLoading

    val errorMessage = mainViewModel.errorMessage
    var showPopup by remember { mutableStateOf(false) }

    LaunchedEffect(errorMessage) {
        showPopup = errorMessage != null
    }

    if (!errorMessage.isNullOrEmpty()) {
        ErrorPopUp(errorMessage) { mainViewModel.dismissError() }
    }


    Scaffold(
        containerColor = Color.Black
    ) { paddingValues ->

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LoadingAnimation(modifier = Modifier)
            }
        } else {
            navigationData?.let {
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
}