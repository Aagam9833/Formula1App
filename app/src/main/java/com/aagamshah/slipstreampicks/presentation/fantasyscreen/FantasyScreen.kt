package com.aagamshah.slipstreampicks.presentation.fantasyscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aagamshah.slipstreampicks.R
import com.aagamshah.slipstreampicks.domain.model.response.GetFantasyHomeResponseModel
import com.aagamshah.slipstreampicks.presentation.components.PrimaryButton
import com.aagamshah.slipstreampicks.ui.theme.AppTypography

@Composable
fun FantasyScreen(
    navController: NavController,
    fantasyViewModel: FantasyViewModel = hiltViewModel(),
) {

    val data = fantasyViewModel.homeData
    val isLoading = fantasyViewModel.isLoading

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(painter = painterResource(R.drawable.ic_app_icon), contentDescription = "")
            Text(
                modifier = Modifier.padding(horizontal = 32.dp),
                text = "Slipstream Picks Fantasy",
                style = AppTypography.displayLarge,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Coming Soon!",
                style = AppTypography.headlineLarge,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFF000000)
@Composable
fun FantasyScreenPreview() {
    val data = GetFantasyHomeResponseModel(listOf(), 0, listOf(), listOf())
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Slipstream Picks Fantasy",
                style = AppTypography.displayLarge,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            if (data.team.isEmpty()) {
                Text(
                    text = "You haven't picked your fantasy team yet",
                    style = AppTypography.headlineLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                PrimaryButton(
                    text = "Let's get started",
                    onClick = {},
                    modifier = Modifier.wrapContentSize(),
                    isEnabled = true
                )
            } else {

            }
        }
    }
}