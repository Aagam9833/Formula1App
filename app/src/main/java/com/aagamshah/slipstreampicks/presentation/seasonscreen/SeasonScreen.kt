package com.aagamshah.slipstreampicks.presentation.seasonscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.aagamshah.slipstreampicks.R
import com.aagamshah.slipstreampicks.domain.model.response.Race
import com.aagamshah.slipstreampicks.navigation.Route
import com.aagamshah.slipstreampicks.presentation.components.CustomTab
import com.aagamshah.slipstreampicks.presentation.components.ErrorPopUp
import com.aagamshah.slipstreampicks.presentation.components.LoadingAnimation
import com.aagamshah.slipstreampicks.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeasonScreen(navController: NavController, seasonViewModel: SeasonViewModel = hiltViewModel()) {

    val data = seasonViewModel.currentSeasonModel

    val pagerState = rememberPagerState { 2 }
    val coroutineScope = rememberCoroutineScope()
    val isLoading = seasonViewModel.isLoading

    val errorMessage = seasonViewModel.errorMessage
    var showPopup by remember { mutableStateOf(false) }

    var isRefreshing by remember { mutableStateOf(false) }
    val refreshState = rememberPullToRefreshState()

    LaunchedEffect(errorMessage) {
        showPopup = errorMessage != null
    }

    if (!errorMessage.isNullOrEmpty()) {
        ErrorPopUp(errorMessage) { seasonViewModel.dismissError() }
    }


    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LoadingAnimation(modifier = Modifier)
        }
    } else {
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            state = refreshState,
            onRefresh = {
                isRefreshing = true
                seasonViewModel.refreshPage()
                isRefreshing = false
            }
        ) {
            Column {
                CustomTab(
                    items = listOf(
                        stringResource(R.string.upcoming),
                        stringResource(R.string.past)
                    ),
                    selectedItemIndex = pagerState.currentPage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 32.dp)
                ) { index ->
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }

                Text(
                    text = if (pagerState.currentPage == 0) stringResource(R.string.schedule).uppercase() else stringResource(
                        R.string.races
                    ).uppercase(),
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
                    SeasonListComponent(
                        races = if (page == 0) data?.upcomingRaces else data?.pastRaces,
                        seasonViewModel = seasonViewModel,
                        isPast = (page == 1),
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun SeasonListComponent(
    races: List<Race>?,
    seasonViewModel: SeasonViewModel,
    isPast: Boolean,
    navController: NavController,
) {
    if (races.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(R.string.waiting_for_lights_out),
                style = AppTypography.displayLarge,
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyColumn(contentPadding = PaddingValues(bottom = 100.dp)) {
            items(races) { race ->
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            if (isPast) {
                                navController.navigate("${Route.RaceResultScreen.route}/${race.round}")
                            }
                        },
                    colors = CardDefaults.cardColors(containerColor = DarkGrey),
                ) {
                    Row {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = seasonViewModel.formatDate(
                                    race.sessions.firstPractice,
                                    race.sessions.race
                                ),
                                style = AppTypography.headlineSmall,
                                color = Color.White
                            )
                            Box(
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .background(color = Grey, shape = RoundedCornerShape(30.dp))
                            ) {
                                Text(
                                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
                                    text = seasonViewModel.formatMonth(
                                        race.sessions.firstPractice,
                                        race.sessions.race
                                    ),
                                    style = AppTypography.bodyMedium,
                                    color = Color.Black
                                )
                            }
                        }
                        VerticalDivider(thickness = 1.dp, color = Grey)
                        Column(
                            modifier = Modifier
                                .weight(2f)
                                .align(Alignment.CenterVertically),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = stringResource(R.string.round, race.round),
                                style = AppTypography.titleSmall,
                                color = Formula1Red
                            )
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = race.location.country,
                                style = AppTypography.titleLarge,
                                color = Color.White
                            )
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = race.circuit.name,
                                style = AppTypography.bodyMedium,
                                color = LightGrey
                            )
                        }
                        AsyncImage(
                            modifier = Modifier.weight(2f),
                            model = race.circuit.url,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}
