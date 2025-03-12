package com.aagamshah.slipstreampicks.presentation.splashscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aagamshah.slipstreampicks.R
import com.aagamshah.slipstreampicks.navigation.Route
import com.aagamshah.slipstreampicks.ui.theme.AppTypography
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {

    var isUserLoggedIn = splashViewModel.isUserLoggedIn

    LaunchedEffect(isUserLoggedIn) {
        delay(1500)
        if (isUserLoggedIn) {
            navController.navigate(Route.MainScreen.route) {
                popUpTo(Route.SplashScreen.route) { inclusive = true }
            }
        } else {
            navController.navigate(Route.SignUpLoginScreen.route) {
                popUpTo(Route.SplashScreen.route) { inclusive = true }
            }
        }
    }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(R.string.app_name), style = AppTypography.displayLarge)
        }
    }

}
