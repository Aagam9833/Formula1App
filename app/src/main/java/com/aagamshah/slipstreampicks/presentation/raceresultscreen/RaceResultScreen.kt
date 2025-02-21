package com.aagamshah.slipstreampicks.presentation.raceresultscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.aagamshah.slipstreampicks.domain.model.Result
import com.aagamshah.slipstreampicks.ui.theme.AppTypography

@Composable
fun RaceResultScreen(
    navController: NavController,
    raceResultViewModel: RaceResultViewModel = hiltViewModel(),
    roundNumber: String,
) {

    LaunchedEffect(roundNumber) {
        raceResultViewModel.getRoundResults(roundNumber)
    }

    val data = raceResultViewModel.raceResultModel
    val isLoading = raceResultViewModel.isLoading

    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Black) { innerPadding ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            if (data != null) {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        text = "Round ${data.round}: ${data.raceName}",
                        style = AppTypography.headlineLarge,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        DriverImageWithDetails(
                            modifier = Modifier.weight(1f),
                            result = data.results[1],
                            imageHeight = 120.dp
                        )
                        DriverImageWithDetails(
                            modifier = Modifier.weight(1f),
                            result = data.results[0],
                            imageHeight = 160.dp
                        )
                        DriverImageWithDetails(
                            modifier = Modifier.weight(1f),
                            result = data.results[2],
                            imageHeight = 140.dp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DriverImageWithDetails(modifier: Modifier, result: Result, imageHeight: Dp) {
    Column(verticalArrangement = Arrangement.Center) {
        AsyncImage(
            modifier = modifier.height(imageHeight),
            model = result.driverImage,
            contentDescription = "",
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = result.name,
            style = AppTypography.titleMedium,
            textAlign = TextAlign.Center
        )
    }
}