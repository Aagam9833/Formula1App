package com.aagamshah.splitstreampicks.presentation.components


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
private fun MyTabIndicator(
    indicatorWidth: Dp,
    indicatorOffset: Dp,
    indicatorColor: Color,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(
                width = indicatorWidth,
            )
            .offset(
                x = indicatorOffset,
            )
            .clip(
                shape = CircleShape,
            )
            .background(
                color = indicatorColor,
            )
            .padding(4.dp)
    )
}

@Composable
private fun MyTabItem(
    onClick: () -> Unit,
    tabWidth: Dp,
    text: String,
) {
    val tabTextColor = animateColorAsState(
        targetValue = White,
        animationSpec = tween(easing = LinearEasing),
    ).value

    Text(
        modifier = Modifier
            .clip(CircleShape)
            .clickable { onClick() }
            .width(tabWidth)
            .padding(vertical = 8.dp, horizontal = 12.dp),
        text = text,
        color = tabTextColor,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun CustomTab(
    selectedItemIndex: Int,
    items: List<String>,
    modifier: Modifier = Modifier,
    onClick: (index: Int) -> Unit,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val tabWidth = screenWidth / items.size

    val indicatorOffset = animateDpAsState(
        targetValue = tabWidth * selectedItemIndex,
        animationSpec = tween(easing = LinearEasing),
        label = "IndicatorOffset"
    ).value

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(Black)
            .height(40.dp),
    ) {
        MyTabIndicator(
            indicatorWidth = tabWidth,
            indicatorOffset = indicatorOffset,
            indicatorColor = MaterialTheme.colorScheme.primary,
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .align(Alignment.Center),
        ) {
            items.forEachIndexed { index, text ->
                MyTabItem(
                    onClick = { onClick(index) },
                    tabWidth = tabWidth,
                    text = text,
                )
            }
        }
    }
}
