package com.aagamshah.slipstreampicks.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.aagamshah.slipstreampicks.utils.CardFace
import com.aagamshah.slipstreampicks.ui.theme.DarkGrey

@Composable
fun FlipCard(
    cardFace: CardFace,
    modifier: Modifier = Modifier,
    front: @Composable () -> Unit,
    back: @Composable () -> Unit,
) {

    val rotation = animateFloatAsState(
        targetValue = cardFace.angle,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        )
    )

    Card(
        modifier = modifier
            .graphicsLayer {
                rotationY = rotation.value
                cameraDistance = 12f * density
            },
        colors = CardDefaults.cardColors(containerColor = DarkGrey)
    ) {

        if (rotation.value <= 90f) {
            Box(
                Modifier.fillMaxWidth()
            ) {
                front()
            }
        } else {
            Box(
                Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        rotationY = 180f
                    },
            ) {
                back()
            }
        }

    }
}