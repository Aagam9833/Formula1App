package com.aagamshah.slipstreampicks.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aagamshah.slipstreampicks.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FantasyBottomSheet(
    onDismiss: () -> Unit,
    driverList: List<FantasyCardModel>,
    constructorList: List<FantasyCardModel>,
    userTeam: List<FantasyCardModel?>,
    onTeamSelected: (List<FantasyCardModel>) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val selectedTeam = remember { userTeam.filterNotNull().toMutableStateList() }

    val isDriverSelectionComplete = selectedTeam.count { it.type == CardType.DRIVER } == 5
    val cardContent = if (isDriverSelectionComplete) constructorList else driverList

    val carouselState = rememberCarouselState { cardContent.size } // Reset state dynamically

    // Dynamically adjust padding based on item count
    val dynamicContentPadding = if (cardContent.size <= 3) 48.dp else 16.dp

    // Dynamically adjust item width based on item count to prevent extra space
    val dynamicItemWidth = if (cardContent.size <= 3) 160.dp else 140.dp

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your Team",
                style = AppTypography.headlineLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Display the selected team in reverse order (last added first)
            HorizontalUncontainedCarousel(
                state = rememberCarouselState { selectedTeam.size },
                itemWidth = 140.dp,
                itemSpacing = 12.dp,
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 16.dp)
            ) { index ->
                selectedTeam.reversed().getOrNull(index)?.let { card ->
                    FantasyCard(
                        data = card,
                        modifier = Modifier.maskClip(shape = RoundedCornerShape(8.dp))
                    ) {
                        selectedTeam.remove(card)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (isDriverSelectionComplete) "Select your constructor" else "Select your 5 drivers",
                style = AppTypography.headlineLarge,
                color = Color.White
            )

            HorizontalUncontainedCarousel(
                state = carouselState,
                itemWidth = dynamicItemWidth,
                itemSpacing = 12.dp,
                contentPadding = PaddingValues(start = dynamicContentPadding, end = dynamicContentPadding),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 16.dp)
            ) { index ->
                cardContent.getOrNull(index)?.let { card ->
                    FantasyCard(
                        data = card,
                        modifier = Modifier.maskClip(shape = RoundedCornerShape(8.dp))
                    ) {
                        if (card !in selectedTeam && selectedTeam.size < 6) {
                            when (card.type) {
                                CardType.DRIVER -> if (selectedTeam.count { it.type == CardType.DRIVER } < 5) selectedTeam.add(card)
                                CardType.CONSTRUCTOR -> if (selectedTeam.count { it.type == CardType.CONSTRUCTOR } < 1) selectedTeam.add(card)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TertiaryButton(
                text = "Proceed",
                onClick = { onTeamSelected(selectedTeam) },
                modifier = Modifier,
                isEnabled = selectedTeam.count { it.type == CardType.DRIVER } == 5 &&
                        selectedTeam.count { it.type == CardType.CONSTRUCTOR } == 1
            )
        }
    }
}
