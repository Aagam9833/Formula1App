package com.aagamshah.slipstreampicks.presentation.standingsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.aagamshah.slipstreampicks.R
import com.aagamshah.slipstreampicks.domain.model.ConstructorStandingModel
import com.aagamshah.slipstreampicks.domain.model.DriverStandingModel
import com.aagamshah.slipstreampicks.presentation.components.CustomTab
import com.aagamshah.slipstreampicks.presentation.components.OutlinedText
import com.aagamshah.slipstreampicks.ui.theme.AppTypography
import com.aagamshah.slipstreampicks.ui.theme.BlackWhite
import com.aagamshah.slipstreampicks.ui.theme.Formula1Red
import kotlinx.coroutines.launch

@Composable
fun StandingsScreen(
    navController: NavController,
    standingsViewModel: StandingsViewModel = hiltViewModel(),
) {

    val driverData = standingsViewModel.driverStandingModel
    val constructorData = standingsViewModel.constructorStandingModel
    val isLoading = standingsViewModel.isLoading

    val pagerState = rememberPagerState { 2 }
    val coroutineScope = rememberCoroutineScope()
    val (selected, setSelected) = remember {
        mutableIntStateOf(0)
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {

        Column {
            CustomTab(
                items = listOf("Driver", "Constructor"),
                selectedItemIndex = selected,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 32.dp)
            ) { index ->
                setSelected(index)
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }

            Text(
                text = stringResource(R.string.standings).uppercase(),
                style = AppTypography.headlineLarge,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                when (page) {
                    0 -> {
                        setSelected(0)
                        DriverStandingComposable(driverData)
                    }

                    1 -> {
                        setSelected(1)
                        ConstructorStandingComposable(constructorData)
                    }
                }
            }
        }
    }
}

@Composable
fun ConstructorStandingComposable(constructorData: ConstructorStandingModel?) {
    if (constructorData == null || constructorData.constructors.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "Waiting for lights out!",
                style = AppTypography.displayLarge,
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyColumn(contentPadding = PaddingValues(bottom = 100.dp)) {
            items(constructorData.constructors) { data ->
                val gradient = Brush.linearGradient(
                    colors = listOf(
                        BlackWhite,
                        Color.Black,
                        Color.Black,
                        Color.Black,
                        Color.Black,
                        Color.Black,
                        Color(android.graphics.Color.parseColor(data.constructorColor)),
                    ),
                    start = Offset(50f, 50f),
                    end = Offset(700f, 700f)
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Box(
                        modifier = Modifier
                            .background(brush = gradient)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = data.constructorName,
                                    style = AppTypography.titleLarge,
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
                                            vertical = 2.dp,
                                            horizontal = 4.dp
                                        ),
                                        text = stringResource(R.string.pts, data.points),
                                        style = AppTypography.bodyMedium,
                                        color = Color.White
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier.weight(2f),
                                contentAlignment = Alignment.Center
                            ) {
                                OutlinedText(
                                    data.position,
                                    Color(android.graphics.Color.parseColor(data.constructorColor))
                                )
                                AsyncImage(
                                    alignment = Alignment.BottomCenter,
                                    model = data.imageUrl,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .aspectRatio(1f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun DriverStandingComposable(driverData: DriverStandingModel?) {
    if (driverData == null || driverData.drivers.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "Waiting for lights out!",
                style = AppTypography.displayLarge,
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyColumn(contentPadding = PaddingValues(bottom = 100.dp)) {
            items(driverData.drivers) { data ->
                val gradient = Brush.linearGradient(
                    colors = listOf(
                        Color(android.graphics.Color.parseColor(data.constructorColor)),
                        Color.Black,
                        Color.Black,
                        Color.Black,
                        Color.Black,
                        Color.Black,
                        BlackWhite
                    ),
                    start = Offset(80f, 80f),
                    end = Offset(700f, 700f)
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Box(
                        modifier = Modifier
                            .background(brush = gradient)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier.weight(2f),
                                contentAlignment = Alignment.Center
                            ) {
                                OutlinedText(
                                    data.position,
                                    Color(android.graphics.Color.parseColor(data.constructorColor))
                                )
                                AsyncImage(
                                    alignment = Alignment.BottomCenter,
                                    model = data.imageUrl,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .aspectRatio(1f)
                                )
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = if (data.permanentNumber.length == 1) "0${data.permanentNumber}" else data.permanentNumber,
                                    style = AppTypography.displayLarge,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = data.name,
                                    style = AppTypography.titleLarge,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = data.constructorName,
                                    style = AppTypography.bodyMedium,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(8.dp))
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
                                            vertical = 2.dp,
                                            horizontal = 4.dp
                                        ),
                                        text = stringResource(R.string.pts, data.points),
                                        style = AppTypography.bodyMedium,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
