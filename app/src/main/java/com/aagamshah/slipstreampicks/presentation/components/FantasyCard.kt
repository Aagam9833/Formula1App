package com.aagamshah.slipstreampicks.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.aagamshah.slipstreampicks.ui.theme.AppTypography
import com.aagamshah.slipstreampicks.ui.theme.TertiaryColor

@Composable
fun FantasyCard(
    data: FantasyCardModel,
    modifier: Modifier = Modifier,
    onclick: (FantasyCardModel) -> Unit = {}
) {

    Box(
        modifier = modifier
            .background(color = TertiaryColor)
            .fillMaxSize()
            .clickable {
                onclick(data)
            }
    ) {
        AsyncImage(
            model = data.image,
            contentDescription = null,
            modifier = modifier.align(Alignment.BottomCenter)
        )
        Text(
            data.title,
            modifier = modifier.padding(12.dp),
            style = AppTypography.labelLarge,
            color = Color.White
        )
    }

}

data class FantasyCardModel(
    val title: String,
    val image: String,
    var id: String,
    val type: CardType,
)

enum class CardType {
    DRIVER,
    CONSTRUCTOR
}