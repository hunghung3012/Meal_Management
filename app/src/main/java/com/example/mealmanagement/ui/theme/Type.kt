package com.example.mealmanagement.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mealmanagement.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
val inter_bold = FontFamily(
    Font(R.font.inter_bold,FontWeight.Bold)
)
val inter_medium = FontFamily(
    Font(R.font.inter_medium,FontWeight.Medium)
)
val inter_light = FontFamily(
    Font(R.font.inter_light,FontWeight.Light)
)
val inter_extrabold = FontFamily(
    Font(R.font.inter_extrabold,FontWeight.Bold)
)
val kanit_ragular = FontFamily(
    Font(R.font.kanit_regular,FontWeight.Normal)
)
val kanit_bold = FontFamily(
    Font(R.font.kanit_bold,FontWeight.Bold)
)