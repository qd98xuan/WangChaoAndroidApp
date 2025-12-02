package com.hx.wangchao.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.hx.baselibrary.base.convertSpSize

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.convertSpSize(),
        lineHeight = 24.convertSpSize(),
        letterSpacing = 0.5.convertSpSize()
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.convertSpSize(),
        lineHeight = 28.convertSpSize(),
        letterSpacing = 0.convertSpSize()
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.convertSpSize(),
        lineHeight = 16.convertSpSize(),
        letterSpacing = 0.5.convertSpSize()
    )
    */
)