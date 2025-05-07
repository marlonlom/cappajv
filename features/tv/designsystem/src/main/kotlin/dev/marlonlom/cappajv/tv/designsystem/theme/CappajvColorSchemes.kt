/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.designsystem.theme

import androidx.compose.ui.graphics.Color
import androidx.tv.material3.ColorScheme
import androidx.tv.material3.darkColorScheme
import androidx.tv.material3.lightColorScheme

/**
 * Branded color contrasts enum class.
 *
 * @author marlonlom
 *
 * @property light Light color scheme.
 * @property dark Dark color scheme.
 *
 */
internal enum class CappajvColorSchemes(val light: ColorScheme, val dark: ColorScheme) {

  /** Default branded color contrast: Standard. */
  STANDARD(
    light = lightColorScheme(
      primary = Color(0xFFFFB3AE),
      onPrimary = Color(0xFF571D1C),
      primaryContainer = Color(0xFF733331),
      onPrimaryContainer = Color(0xFFFFDAD7),
      secondary = Color(0xFFFFB3AF),
      onSecondary = Color(0xFF571D1D),
      secondaryContainer = Color(0xFF733331),
      onSecondaryContainer = Color(0xFFFFDAD7),
      tertiary = Color(0xFFFFB784),
      onTertiary = Color(0xFF4F2500),
      tertiaryContainer = Color(0xFF6D390C),
      onTertiaryContainer = Color(0xFFFFDCC6),
      error = Color(0xFFFFB4AB),
      onError = Color(0xFF690005),
      errorContainer = Color(0xFF93000A),
      onErrorContainer = Color(0xFFFFDAD6),
      background = Color(0xFF1A1111),
      onBackground = Color(0xFFF1DEDD),
      surface = Color(0xFF1A1111),
      onSurface = Color(0xFFF0DEDE),
      surfaceVariant = Color(0xFF534342),
      onSurfaceVariant = Color(0xFFD7C1C0),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFFF0DEDE),
      inverseOnSurface = Color(0xFF382E2E),
      inversePrimary = Color(0xFF904A46),
    ),
    dark = darkColorScheme(
      primary = Color(0xFFFFD2CE),
      onPrimary = Color(0xFF481312),
      primaryContainer = Color(0xFFCB7B76),
      onPrimaryContainer = Color(0xFF000000),
      secondary = Color(0xFFFFD2CF),
      onSecondary = Color(0xFF481313),
      secondaryContainer = Color(0xFFCB7B77),
      onSecondaryContainer = Color(0xFF000000),
      tertiary = Color(0xFFFFD4B8),
      onTertiary = Color(0xFF3F1C00),
      tertiaryContainer = Color(0xFFC5814E),
      onTertiaryContainer = Color(0xFF000000),
      error = Color(0xFFFFD2CC),
      onError = Color(0xFF540003),
      errorContainer = Color(0xFFFF5449),
      onErrorContainer = Color(0xFF000000),
      background = Color(0xFF1A1111),
      onBackground = Color(0xFFF1DEDD),
      surface = Color(0xFF1A1111),
      onSurface = Color(0xFFFFFFFF),
      surfaceVariant = Color(0xFF534342),
      onSurfaceVariant = Color(0xFFEED7D5),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFFF0DEDE),
      inverseOnSurface = Color(0xFF322827),
      inversePrimary = Color(0xFF743432),
    ),
  ),
}
