/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import dev.marlonlom.cappajv.R

/**
 * Brand font object specification for application.
 *
 * @author marlonlom
 */
@ExperimentalMaterial3Api
internal object CappajvFont {

  /** Default font family. */
  private val defaultFontFamily = FontFamily(
    Font(resId = R.font.albertsans_regular, weight = FontWeight.Normal),
    Font(resId = R.font.albertsans_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(resId = R.font.albertsans_bold, weight = FontWeight.Bold),
    Font(resId = R.font.albertsans_bold_italic, style = FontStyle.Italic),
  )

  /** Baseline typography. */
  private val baseline = Typography()

  /** Set of Material typography styles to start with. */
  val appTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = defaultFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = defaultFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = defaultFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = defaultFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = defaultFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = defaultFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = defaultFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = defaultFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = defaultFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = defaultFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = defaultFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = defaultFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = defaultFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = defaultFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = defaultFontFamily)
  )
}