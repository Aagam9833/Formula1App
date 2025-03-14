package com.aagamshah.slipstreampicks.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aagamshah.slipstreampicks.ui.theme.AppTypography
import java.util.Locale

@Composable
fun TimeUnitBox(value: Long, label: String, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.width(60.dp)) {
        Text(
            text = String.format(Locale.US, "%02d", value),
            style = AppTypography.displayLarge,
            color = Color.White
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = label,
            style = AppTypography.bodyMedium,
            color = Color.Gray
        )
    }
}