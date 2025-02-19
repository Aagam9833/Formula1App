package com.aagamshah.splitstreampicks.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aagamshah.splitstreampicks.R
import com.aagamshah.splitstreampicks.domain.model.NavigationModel
import com.aagamshah.splitstreampicks.navigation.Route
import com.aagamshah.splitstreampicks.ui.theme.Grey

@Composable
fun CustomBottomNavigationBar(navController: NavHostController, navigationModel: NavigationModel, modifier: Modifier) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Grey, shape = RoundedCornerShape(50.dp))
            .clip(RoundedCornerShape(20.dp))
            .shadow(8.dp, shape = RoundedCornerShape(20.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(color = Grey),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navigationModel.tabs?.forEach { item ->
                val isSelected = currentRoute == item.id
                IconButton(
                    onClick = {
                        if (!isSelected) {
                            navController.navigate(item.id) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }
                    },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        painter = painterResource(fetchIcon(item.id)),
                        contentDescription = "",
                        tint = if (isSelected) Color.White else Color.Gray
                    )
                }
            }
        }
    }
}

private fun fetchIcon(id: String): Int {
    return when (id) {
        "home_screen" -> {
            R.drawable.ic_home_nav
        }

        "season_screen" -> {
            R.drawable.ic_season_nav
        }

        "standings_screen" -> {
            R.drawable.ic_leaderboard_nav
        }

        "settings_screen" -> {
            R.drawable.ic_settings_nav
        }

        else -> {
            R.drawable.ic_settings_nav
        }
    }
}
