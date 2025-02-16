package com.aagamshah.splitstreampicks.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.aagamshah.splitstreampicks.ui.theme.AppTypography
import java.util.Locale

@Composable
fun TimeUnitBox(value: Long, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = String.format(Locale.US, "%02d", value),
            style = AppTypography.displayMedium,
            color = Color.White
        )
        Text(
            text = label,
            style = AppTypography.bodyMedium,
            color = Color.Gray
        )
    }
}