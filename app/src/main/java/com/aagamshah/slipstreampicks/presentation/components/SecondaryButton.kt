package com.aagamshah.slipstreampicks.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aagamshah.slipstreampicks.ui.theme.AppTypography

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    isEnabled: Boolean
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1E1E1E),
            contentColor = Color.LightGray
        )
    ) {
        Text(text = text, style = AppTypography.titleLarge)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SecondaryButtonPreview() {
    SecondaryButton(
        "Secondary Button",
        onClick = {},
        modifier = Modifier,
        isEnabled = true
    )
}