package com.aagamshah.slipstreampicks.presentation.fantasyscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aagamshah.slipstreampicks.R
import com.aagamshah.slipstreampicks.domain.model.response.toConstructorFantasyCardList
import com.aagamshah.slipstreampicks.domain.model.response.toDriverFantasyCardList
import com.aagamshah.slipstreampicks.presentation.components.ErrorPopUp
import com.aagamshah.slipstreampicks.presentation.components.FantasyBottomSheet
import com.aagamshah.slipstreampicks.presentation.components.FantasyCard
import com.aagamshah.slipstreampicks.presentation.components.FantasyCardModel
import com.aagamshah.slipstreampicks.presentation.components.LeaderboardCell
import com.aagamshah.slipstreampicks.presentation.components.TertiaryButton
import com.aagamshah.slipstreampicks.ui.theme.AppTypography
import com.aagamshah.slipstreampicks.ui.theme.SecondaryColor
import com.aagamshah.slipstreampicks.ui.theme.TertiaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FantasyScreen(
    navController: NavController,
    fantasyViewModel: FantasyViewModel = hiltViewModel(),
) {
    val data = fantasyViewModel.homeData
    val fantasyTeam = fantasyViewModel.currentUserTeam

    val isLoading = fantasyViewModel.isLoading
    val showBottomSheet = remember { mutableStateOf(false) }
    val errorMessage = fantasyViewModel.errorMessage
    var showPopup by remember { mutableStateOf(false) }

    LaunchedEffect(errorMessage) {
        showPopup = errorMessage != null
    }

    Box(modifier = Modifier.fillMaxSize()) {


        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        if (data != null) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(bottom = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = stringResource(R.string.slipstream_picks_fantasy),
                        style = AppTypography.displayLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    if (fantasyTeam.isEmpty()) {
                        NoFantasyTeamCard(onStartClick = { showBottomSheet.value = true })
                    } else {
                        FantasyTeamCard(
                            fantasyTeam = fantasyTeam,
                            totalPoints = data.currentUser?.points ?: 0,
                            onEditClick = { showBottomSheet.value = true },
                            editTeam = data.isQualifyingOver
                        )
                    }
                }

                item {
                    Text(
                        text = stringResource(R.string.leaderboard),
                        style = AppTypography.headlineLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (data.topRanks.isNotEmpty()) {
                    items(data.topRanks) { rankData ->
                        LeaderboardCell(modifier = Modifier.padding(), data = rankData)
                    }
                } else {
                    item {
                        Text(
                            text = "No participants yet!",
                            style = AppTypography.displayLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        if (showBottomSheet.value) {
            FantasyBottomSheet(
                onDismiss = { showBottomSheet.value = false },
                driverList = data?.currentDrivers.toDriverFantasyCardList(),
                constructorList = data?.currentConstructors.toConstructorFantasyCardList(),
                userTeam = fantasyTeam,
                onTeamSelected = { selectedTeam ->
                    if (selectedTeam.isNotEmpty() && selectedTeam.size == 6) {
                        fantasyViewModel.saveFantasyTeam(selectedTeam)
                    }
                    showBottomSheet.value = false
                }
            )
        }
    }
    if (!errorMessage.isNullOrEmpty()) {
        ErrorPopUp(errorMessage) { fantasyViewModel.dismissError() }
    }
}


@Composable
fun NoFantasyTeamCard(onStartClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = SecondaryColor)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 16.dp, start = 32.dp, end = 32.dp),
                text = stringResource(R.string.you_haven_t_set_your_fantasy_team),
                style = AppTypography.headlineLarge,
                color = TertiaryColor,
                textAlign = TextAlign.Center
            )
            TertiaryButton(
                text = stringResource(R.string.start),
                onClick = onStartClick,
                modifier = Modifier.padding(16.dp),
                isEnabled = true
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FantasyTeamCard(
    fantasyTeam: List<FantasyCardModel?>,
    totalPoints: Long,
    onEditClick: () -> Unit,
    editTeam: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = SecondaryColor)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp),
                text = stringResource(R.string.your_team),
                style = AppTypography.headlineLarge,
                color = TertiaryColor,
                textAlign = TextAlign.Center
            )
            HorizontalUncontainedCarousel(
                state = rememberCarouselState {
                    fantasyTeam.size
                },
                itemWidth = 140.dp,
                itemSpacing = 12.dp,
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 16.dp)
            ) { index ->
                FantasyCard(
                    data = fantasyTeam[index]!!,
                    modifier = Modifier.maskClip(shape = RoundedCornerShape(8.dp)),
                )
            }
            Text(
                text = stringResource(R.string.total_points, totalPoints),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            if (!editTeam) {
                TertiaryButton(
                    text = stringResource(R.string.edit),
                    onClick = onEditClick,
                    modifier = Modifier.padding(16.dp),
                    isEnabled = true
                )
            }
        }
    }
}
