package com.aagamshah.splitstreampicks.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.aagamshah.splitstreampicks.R

val F1Bold = FontFamily(Font(R.font.f1_bold, FontWeight.Bold))
val F1Wide = FontFamily(Font(R.font.f1_wide, FontWeight.Normal))
val F1Regular = FontFamily(Font(R.font.f1_reg, FontWeight.Normal))

val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = F1Bold,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
    ),
    displayMedium = TextStyle(
        fontFamily = F1Bold,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    ),
    displaySmall = TextStyle(
        fontFamily = F1Wide,
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineLarge = TextStyle(
        fontFamily = F1Bold,
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineMedium = TextStyle(
        fontFamily = F1Regular,
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineSmall = TextStyle(
        fontFamily = F1Regular,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal
    ),
    titleLarge = TextStyle(
        fontFamily = F1Bold,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    ),
    titleMedium = TextStyle(
        fontFamily = F1Wide,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    titleSmall = TextStyle(
        fontFamily = F1Regular,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyLarge = TextStyle(
        fontFamily = F1Regular,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = F1Regular,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = F1Regular,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = F1Bold,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = F1Wide,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = F1Regular,
        fontSize = 10.sp
    )
)