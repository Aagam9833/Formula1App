package com.aagamshah.slipstreampicks.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.aagamshah.slipstreampicks.R
import com.aagamshah.slipstreampicks.ui.theme.AppTypography
import com.aagamshah.slipstreampicks.ui.theme.DarkGrey

@Composable
fun ErrorPopUp(message: String, modifier: Modifier = Modifier, onDismiss: () -> Unit) {

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = modifier.defaultMinSize(minHeight = 200.dp, minWidth = 200.dp),
            colors = CardDefaults.cardColors(containerColor = DarkGrey)
        ) {
            Column(
                modifier = modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Box box!",
                    style = AppTypography.titleLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                CustomAnimation(
                    modifier = modifier,
                    lottieRes = R.raw.boxbox,
                    loop = true,
                    isPlaying = true,
                    size = 1f
                )
                Text(
                    text = message,
                    style = AppTypography.bodyLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFF000000)
@Composable
fun ErrorPopUpPreview() {
    ErrorPopUp("Something went wrong!") {}
}
