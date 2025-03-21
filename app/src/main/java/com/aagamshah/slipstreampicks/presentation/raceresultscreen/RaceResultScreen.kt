package com.aagamshah.slipstreampicks.presentation.raceresultscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.aagamshah.slipstreampicks.R
import com.aagamshah.slipstreampicks.domain.model.response.Result
import com.aagamshah.slipstreampicks.presentation.components.ErrorPopUp
import com.aagamshah.slipstreampicks.presentation.components.OutlinedText
import com.aagamshah.slipstreampicks.ui.theme.AppTypography
import com.aagamshah.slipstreampicks.ui.theme.DarkGrey
import com.aagamshah.slipstreampicks.ui.theme.Formula1Red

@Composable
fun RaceResultScreen(
    navController: NavController,
    raceResultViewModel: RaceResultViewModel = hiltViewModel(),
    roundNumber: String,
) {

    val errorMessage = raceResultViewModel.errorMessage
    var showPopup by remember { mutableStateOf(false) }

    LaunchedEffect(errorMessage) {
        showPopup = errorMessage != null
    }

    if (!errorMessage.isNullOrEmpty()) {
        ErrorPopUp(errorMessage) { raceResultViewModel.dismissError() }
    }


    LaunchedEffect(roundNumber) {
        raceResultViewModel.getRoundResults(roundNumber)
    }

    val data = raceResultViewModel.raceResultModel
    val isLoading = raceResultViewModel.isLoading
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

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
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        WinnerComponent(screenWidth, data.results[1], Modifier, 0.4f)
                        WinnerComponent(screenWidth, data.results[0], Modifier, 0.5f)
                        WinnerComponent(screenWidth, data.results[2], Modifier, 0.4f)
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 32.dp, end = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(data.results.drop(3)) { index, item ->
                            RemainingRacerComponent(item, index)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WinnerComponent(screenWidth: Dp, data: Result, modifier: Modifier, height: Float) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = modifier
                .width(screenWidth * (height - 0.1f))
                .height(screenWidth * height),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedText(
                    number = data.position,
                    outlineColor = Color(android.graphics.Color.parseColor(data.constructorColor)),
                    textSize = 40.sp
                )
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = data.driverImage,
                    contentDescription = "",
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(top = 16.dp),
            text = data.code,
            style = AppTypography.headlineLarge,
            color = Color.White
        )
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .background(
                    color = Formula1Red,
                    shape = RoundedCornerShape(30.dp)
                )
        ) {
            Text(
                modifier = Modifier.padding(
                    vertical = 4.dp,
                    horizontal = 8.dp
                ),
                text = stringResource(R.string.pts, data.points),
                style = AppTypography.bodyMedium,
                color = Color.White
            )
        }
    }
}

@Composable
fun RemainingRacerComponent(data: Result, index: Int) {

    Card(
        shape = RoundedCornerShape(size = 12.dp),
        colors = CardDefaults.cardColors(containerColor = DarkGrey),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedText(
                    (index + 4).toString(),
                    Color(android.graphics.Color.parseColor(data.constructorColor)),
                    24.sp
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = data.code,
                    style = AppTypography.displayLarge,
                    fontSize = 24.sp
                )
            }
            Box(
                modifier = Modifier
                    .background(
                        color = Formula1Red,
                        shape = RoundedCornerShape(30.dp)
                    )
            ) {
                Text(
                    modifier = Modifier.padding(
                        vertical = 4.dp,
                        horizontal = 8.dp
                    ),
                    text = stringResource(R.string.pts, data.points),
                    style = AppTypography.bodyMedium,
                    color = Color.White
                )
            }
        }
    }

}