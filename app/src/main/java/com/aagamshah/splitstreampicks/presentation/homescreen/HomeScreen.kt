package com.aagamshah.splitstreampicks.presentation.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.aagamshah.splitstreampicks.presentation.components.TimeUnitBox
import com.aagamshah.splitstreampicks.ui.theme.AppTypography

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {

    val data = homeViewModel.homeModel
    val remainingTime = homeViewModel.remainingTime.collectAsState().value

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Black
    ) { innerPadding ->
        data?.let { data ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                ) {
                    AsyncImage(
                        model = data.circuit.url,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,

                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFF0047AB).copy(alpha = 0.6f),
                                        Color(0xFF000000).copy(alpha = 1f)
                                    ),
                                    startY = 0f,
                                    endY = Float.POSITIVE_INFINITY
                                )
                            )
                    )
                    Text(
                        text = data.raceName,
                        style = AppTypography.displayLarge,
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.Center)
                    )
                }
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(27, 27, 27)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .offset(y = (-72).dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                        modifier = Modifier
                            .padding(12.dp)
                            .wrapContentHeight()
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(3f)
                                .align(Alignment.CenterVertically),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = data.nextSession.name,
                                style = AppTypography.headlineLarge,
                                color = Color.White,
                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                            )
                            HorizontalDivider(
                                thickness = 1.dp,
                                color = Color.White,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .fillMaxWidth()
                                    .height(intrinsicSize = IntrinsicSize.Max),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (remainingTime.showDays) {
                                    TimeUnitBox(value = remainingTime.first, label = "DAYS")
                                    VerticalDivider(
                                        color = Color.White,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .width(1.dp)
                                    )
                                    TimeUnitBox(value = remainingTime.second, label = "HRS")
                                    VerticalDivider(
                                        color = Color.White,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .width(1.dp)
                                    )
                                    TimeUnitBox(value = remainingTime.third, label = "MINS")
                                } else {
                                    TimeUnitBox(value = remainingTime.first, label = "HRS")
                                    VerticalDivider(
                                        color = Color.White,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .width(1.dp)
                                    )
                                    TimeUnitBox(value = remainingTime.second, label = "MINS")
                                    VerticalDivider(
                                        color = Color.White,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .width(1.dp)
                                    )
                                    TimeUnitBox(value = remainingTime.third, label = "SECS")
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        AsyncImage(
                            modifier = Modifier.weight(2f),
                            model = data.circuit.outlineUrl,
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}