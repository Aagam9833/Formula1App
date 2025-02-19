package com.aagamshah.slipstreampicks.ui.theme

import android.os.Build
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Formula 1 Dark Theme Colors
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF0000),  // Ferrari Red (F1 branding)
    secondary = Color(0xFF1E1E1E), // Dark Gray for background sections
    tertiary = Color(0xFFD4AF37),  // Gold accent for premium UI feel
    background = Color(0xFF121212), // True black background
    surface = Color(0xFF181818),    // Slightly lighter black for cards
    onPrimary = Color.White,       // White text on red
    onSecondary = Color.LightGray, // Light gray text on dark backgrounds
    onTertiary = Color.Black,      // Black text on gold
    onBackground = Color.White,    // White text on black
    onSurface = Color.White        // White text on dark surfaces
)

@Composable
fun SlipstreamPicksTheme(
    dynamicColor: Boolean = false, // Disable dynamic colors for F1 branding
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            dynamicDarkColorScheme(context)
        }
        else -> DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
