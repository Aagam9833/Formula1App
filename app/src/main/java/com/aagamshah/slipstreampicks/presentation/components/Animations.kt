package com.aagamshah.slipstreampicks.presentation.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import com.airbnb.lottie.compose.*

@Composable
fun CustomAnimation(
    modifier: Modifier,
    lottieRes: Int,
    loop: Boolean,
    size: Float = 1f,
    isPlaying: Boolean = true,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieRes))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = if (loop) LottieConstants.IterateForever else 1,
        isPlaying = isPlaying
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
            .fillMaxWidth()
            .scale(size)
    )
}