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
fun TertiaryButton(
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
            containerColor = Color(0xFFD4AF37),
            contentColor = Color.Black
        )
    ) {
        Text(text = text, style = AppTypography.titleLarge)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TertiaryButtonPreview() {
    TertiaryButton(
        "Tertiary Button",
        onClick = {},
        modifier = Modifier,
        isEnabled = true
    )
}