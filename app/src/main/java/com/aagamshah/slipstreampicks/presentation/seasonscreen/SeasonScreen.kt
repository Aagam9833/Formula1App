package com.aagamshah.slipstreampicks.presentation.seasonscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.aagamshah.slipstreampicks.domain.model.Race
import com.aagamshah.slipstreampicks.navigation.Route
import com.aagamshah.slipstreampicks.presentation.components.CustomTab
import com.aagamshah.slipstreampicks.ui.theme.AppTypography
import com.aagamshah.slipstreampicks.ui.theme.DarkGrey
import com.aagamshah.slipstreampicks.ui.theme.Formula1Red
import com.aagamshah.slipstreampicks.ui.theme.Grey
import com.aagamshah.slipstreampicks.ui.theme.LightGrey
import kotlinx.coroutines.launch

@Composable
fun SeasonScreen(navController: NavController, seasonViewModel: SeasonViewModel = hiltViewModel()) {

    val data = seasonViewModel.currentSeasonModel

    val pagerState = rememberPagerState { 2 }
    val coroutineScope = rememberCoroutineScope()
    val (selected, setSelected) = remember {
        mutableIntStateOf(0)
    }

    Column {
        CustomTab(
            items = listOf("Upcoming Grand Prix", "Past Grand Prix"),
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
            text = stringResource(R.string.schedule).uppercase(),
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
                    SeasonListComponent(data?.upcomingRaces, seasonViewModel, false, navController)
                }

                1 -> {
                    setSelected(1)
                    SeasonListComponent(data?.pastRaces, seasonViewModel, true, navController)
                }
            }
        }
    }
}

//region SEASON LIST COMPONENT
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
                text = "Waiting for lights out!",
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
//                            if (isPast) {
                            navController.navigate("${Route.RaceResultScreen.route}/${race.round}")
//                            }
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
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}
//endregion