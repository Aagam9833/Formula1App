package com.aagamshah.slipstreampicks.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import com.aagamshah.slipstreampicks.R

@Composable
fun LoadingAnimation(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .pointerInput(Unit) {}, contentAlignment = Alignment.Center
    ) {
        CustomAnimation(
            modifier, R.raw.loading, true, 1f, true
        )
    }
}