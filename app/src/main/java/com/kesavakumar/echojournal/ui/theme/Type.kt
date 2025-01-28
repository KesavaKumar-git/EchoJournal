package com.kesavakumar.echojournal.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.GoogleFont

import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.kesavakumar.echojournal.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val bodyFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Inter"),
        fontProvider = provider,
    )
)

val displayFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Inter"),
        fontProvider = provider,
    )
)

val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily, fontSize = 26.sp, lineHeight = 32.sp),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily, fontSize = 22.sp, lineHeight = 26.sp),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily, fontSize = 16.sp, lineHeight = 24.sp),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily, fontSize = 13.sp, lineHeight = 18.sp),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily, fontSize = 14.sp, lineHeight = 20.sp),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily, fontSize = 12.sp, lineHeight = 16.sp),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily, fontSize = 14.sp, lineHeight = 20.sp),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily, fontSize = 12.sp),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
)
