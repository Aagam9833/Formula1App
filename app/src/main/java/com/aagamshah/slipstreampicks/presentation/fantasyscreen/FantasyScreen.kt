package com.aagamshah.slipstreampicks.presentation.fantasyscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aagamshah.slipstreampicks.R
import com.aagamshah.slipstreampicks.ui.theme.AppTypography

@Composable
fun FantasyScreen(
    navController: NavController,
    fantasyViewModel: FantasyViewModel = hiltViewModel(),
) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(painter = painterResource(R.drawable.ic_app_icon), contentDescription = "")
            Text(text = "Slipstream Picks Fantasy", style = AppTypography.displayLarge)
            Text(text = "Coming Soon!", style = AppTypography.headlineLarge)
        }
    }

}