package com.aagamshah.slipstreampicks.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.aagamshah.slipstreampicks.domain.model.response.CurrentUser
import com.aagamshah.slipstreampicks.ui.theme.AppTypography
import com.aagamshah.slipstreampicks.ui.theme.SecondaryColor

@Composable
fun LeaderboardCell(modifier: Modifier, data: CurrentUser) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SecondaryColor),
    ) {
        Row(
            modifier = modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = data.rank.toString(),
                style = AppTypography.headlineLarge,
                color = Color.White
            )
            Spacer(modifier = modifier.width(16.dp))
            AsyncImage(
                model = data.profile_image,
                contentDescription = null,
                modifier = modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = modifier.width(16.dp))
            Column(
                modifier = modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = data.username,
                    style = AppTypography.headlineLarge,
                    color = Color.White
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = data.points.toString(),
                    style = AppTypography.headlineMedium,
                    color = Color.White
                )
            }
        }
    }
}