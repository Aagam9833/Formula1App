package com.aagamshah.splitstreampicks.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DotsIndicator(pagerState: PagerState, pageCount: Int, activeRing: Color, inactiveRing: Color) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(pageCount) { index ->
            val color =
                if (pagerState.currentPage == index) activeRing else inactiveRing

            Box(
                modifier = Modifier
                    .size(20.dp)
                    .padding(4.dp)
                    .background(
                        color = color,
                        shape = CircleShape
                    )
            )
        }
    }
}