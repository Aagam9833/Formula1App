package com.aagamshah.slipstreampicks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.aagamshah.slipstreampicks.presentation.fantasyscreen.fantasyScreenRoute
import com.aagamshah.slipstreampicks.presentation.forgotpasswordscreen.forgotPasswordScreenRoute
import com.aagamshah.slipstreampicks.presentation.homescreen.homeScreenRoute
import com.aagamshah.slipstreampicks.presentation.imageuploadscreen.imageUploadScreenRoute
import com.aagamshah.slipstreampicks.presentation.mainscreen.mainScreenRoute
import com.aagamshah.slipstreampicks.presentation.raceresultscreen.raceResultScreenRoute
import com.aagamshah.slipstreampicks.presentation.seasonscreen.seasonScreenRoute
import com.aagamshah.slipstreampicks.presentation.settingsscreen.settingsScreenRoute
import com.aagamshah.slipstreampicks.presentation.signuploginscreen.signUpLoginScreenRoute
import com.aagamshah.slipstreampicks.presentation.splashscreen.splashScreenRoute
import com.aagamshah.slipstreampicks.presentation.standingsscreen.standingsScreenRoute

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.SplashScreen.route) {
        mainScreenRoute(navController)
        homeScreenRoute(navController)
        seasonScreenRoute(navController)
        standingsScreenRoute(navController)
        settingsScreenRoute(navController)
        fantasyScreenRoute(navController)
        raceResultScreenRoute(navController)
        signUpLoginScreenRoute(navController)
        splashScreenRoute(navController)
        imageUploadScreenRoute(navController)
        forgotPasswordScreenRoute(navController)
    }
}