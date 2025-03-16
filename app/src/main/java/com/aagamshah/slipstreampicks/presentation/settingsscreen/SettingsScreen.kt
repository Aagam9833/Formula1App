package com.aagamshah.slipstreampicks.presentation.settingsscreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.aagamshah.slipstreampicks.R
import com.aagamshah.slipstreampicks.navigation.Route
import com.aagamshah.slipstreampicks.presentation.components.PrimaryButton
import com.aagamshah.slipstreampicks.ui.theme.AppTypography
import com.aagamshah.slipstreampicks.ui.theme.Formula1Red
import com.aagamshah.slipstreampicks.ui.theme.Grey

@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
) {

    val username = settingsViewModel.user?.username ?: ""
    val userId = settingsViewModel.user?.id ?: ""
    val profileImage = settingsViewModel.user?.profileImage ?: ""

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 40.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Grey),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = username,
                        style = AppTypography.headlineLarge,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = userId,
                        style = AppTypography.bodyMedium,
                        color = Color.White,
                        modifier = Modifier.wrapContentWidth()
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                AsyncImage(
                    model = profileImage,
                    contentDescription = stringResource(R.string.profile_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .border(width = 1.dp, color = Formula1Red, shape = CircleShape)
                )
            }

        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            PrimaryButton(
                text = stringResource(R.string.logout),
                onClick = {
                    settingsViewModel.logoutUser()
                    navController.navigate(Route.SignUpLoginScreen.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                modifier = Modifier,
                isEnabled = true
            )
            Text(
                text = settingsViewModel.versionText,
                style = AppTypography.bodySmall,
                color = Color.White
            )
        }
    }
}