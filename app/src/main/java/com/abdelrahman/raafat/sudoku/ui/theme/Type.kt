package com.abdelrahman.raafat.sudoku.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun buildTypography(color: Color): Typography {
    // Set of Material typography styles to start with
    val typography =
        Typography(
            bodyLarge =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.5.sp,
                    color = color,
                ),
        )
    return typography
}
