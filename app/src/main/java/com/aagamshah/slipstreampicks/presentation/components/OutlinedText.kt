package com.aagamshah.slipstreampicks.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.aagamshah.slipstreampicks.R

@Composable
fun OutlinedText(number: String?, outlineColor: Color, textSize: TextUnit = 100.sp) {
    val formattedNumber = number?.let { if (it.length == 1) "0$it" else it } ?: "--"
    Text(
        text = formattedNumber,
        style = TextStyle.Default.copy(
            fontSize = textSize,
            fontFamily = FontFamily(Font(R.font.f1_bold)),
            drawStyle = Stroke(
                miter = 10f,
                width = 5f,
                join = StrokeJoin.Round
            ),
            color = outlineColor.copy(alpha = 0.6f),
        ),
    )
}