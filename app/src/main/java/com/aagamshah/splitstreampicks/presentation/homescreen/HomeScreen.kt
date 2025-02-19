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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.aagamshah.splitstreampicks.domain.model.DriverStandingModel
import com.aagamshah.splitstreampicks.presentation.components.DotsIndicator
import com.aagamshah.splitstreampicks.presentation.components.TimeUnitBox
import com.aagamshah.splitstreampicks.ui.theme.AppTypography
import com.aagamshah.splitstreampicks.ui.theme.Formula1Red
import com.aagamshah.splitstreampicks.ui.theme.Grey

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {

    val homeData = homeViewModel.homeModel
    val remainingTime = homeViewModel.remainingTime.collectAsState().value
    val driverStandingData = homeViewModel.driverStandingModel

    homeData?.let { data ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp)
        ) {
            Box {
                TopCard(data.circuit.url, data.raceName)
                TimerCard(
                    sessionTitle = data.nextSession.name,
                    remainingTime = remainingTime,
                    outlineUrl = data.circuit.outlineUrl,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 80.dp)
                )
            }
            Spacer(modifier = Modifier.height(72.dp))
            DriverStandingCard(driverStandingData)

        }
    }
}

//region TOP CARD IMAGE WITH TITLE
@Composable
fun TopCard(image: String, title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        AsyncImage(
            model = image,
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
            text = title,
            style = AppTypography.displayLarge,
            color = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center)
        )
    }
}
//endregion

//region TIMER CARD
@Composable
fun TimerCard(
    sessionTitle: String,
    remainingTime: CountdownState,
    outlineUrl: String,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Grey
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
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
                    text = sessionTitle,
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
                model = outlineUrl,
                contentDescription = "",
            )
        }
    }
}
//endregion

//region DRIVER STANDING CARD
@Composable
fun DriverStandingCard(driverStandingData: DriverStandingModel?) {
    driverStandingData?.let {
        val pageCount = (driverStandingData.drivers.size + 4) / 5
        val pagerState = rememberPagerState(pageCount = { pageCount })

        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Text(
                    driverStandingData.title,
                    style = AppTypography.headlineLarge
                )
                DotsIndicator(pagerState, pageCount, Formula1Red, Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(
                    text = "POS",
                    style = AppTypography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Spacer(modifier = Modifier.weight(0.4f))
                Text(
                    text = "DRIVER",
                    style = AppTypography.bodyMedium,
                    modifier = Modifier.weight(4f)
                )
                Text(
                    text = "TEAM",
                    style = AppTypography.bodyMedium,
                    modifier = Modifier.weight(4f)
                )
                Text(
                    text = "POINTS",
                    style = AppTypography.bodyMedium,
                    modifier = Modifier.weight(2f)
                )
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    val startIndex = page * 5
                    val endIndex = minOf(startIndex + 5, driverStandingData.drivers.size)
                    val driversOnPage = driverStandingData.drivers.subList(startIndex, endIndex)
                    driversOnPage.forEachIndexed { index, driver ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(color = if (index % 2 == 0) Grey else Color.Black)
                                .height(30.dp)
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Text(
                                text = (startIndex + index + 1).toString(),
                                style = AppTypography.bodyMedium,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Box(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(24.dp)
                                    .weight(0.1f)
                                    .background(
                                        shape = RoundedCornerShape(20.dp),
                                        color = Color(
                                            android.graphics.Color.parseColor(driver.constructorColor)
                                        )
                                    )
                            )
                            Spacer(modifier = Modifier.weight(0.4f))
                            Text(
                                text = driver.name,
                                style = AppTypography.labelLarge,
                                modifier = Modifier.weight(4f)
                            )
                            Text(
                                text = driver.constructorName,
                                style = AppTypography.bodyMedium,
                                modifier = Modifier.weight(4f)
                            )
                            Text(
                                text = driver.points,
                                style = AppTypography.bodyMedium,
                                modifier = Modifier.weight(2f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}
//endregion