/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.theme

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
      secondary = Color(0xFFE7BDBA),
      onSecondary = Color(0xFF442928),
      secondaryContainer = Color(0xFF5D3F3D),
      onSecondaryContainer = Color(0xFFFFDAD7),
      tertiary = Color(0xFFFFB785),
      onTertiary = Color(0xFF502500),
      tertiaryContainer = Color(0xFF6E390C),
      onTertiaryContainer = Color(0xFFFFDCC6),
      error = Color(0xFFFFB4AB),
      onError = Color(0xFF690005),
      errorContainer = Color(0xFF93000A),
      onErrorContainer = Color(0xFFFFDAD6),
      background = Color(0xFF1A1111),
      onBackground = Color(0xFFF1DEDD),
      surface = Color(0xFF1A1111),
      onSurface = Color(0xFFF1DEDD),
      surfaceVariant = Color(0xFF534342),
      onSurfaceVariant = Color(0xFFD8C2C0),
      surfaceTint = Color(0xFF271D1D),
      border = Color(0xFFA08C8B),
      borderVariant = Color(0xFF534342),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFFF1DEDD),
      inverseOnSurface = Color(0xFF382E2D),
      inversePrimary = Color(0xFF904A47),
    ),
    dark = darkColorScheme(
      primary = Color(0xFFFFB9B5),
      onPrimary = Color(0xFF330406),
      primaryContainer = Color(0xFFCB7B76),
      onPrimaryContainer = Color(0xFF000000),
      secondary = Color(0xFFEBC1BE),
      onSecondary = Color(0xFF26100F),
      secondaryContainer = Color(0xFFAD8885),
      onSecondaryContainer = Color(0xFF000000),
      tertiary = Color(0xFFFFBC8F),
      onTertiary = Color(0xFF280F00),
      tertiaryContainer = Color(0xFFC5814F),
      onTertiaryContainer = Color(0xFF000000),
      error = Color(0xFFFFBAB1),
      onError = Color(0xFF370001),
      errorContainer = Color(0xFFFF5449),
      onErrorContainer = Color(0xFF000000),
      background = Color(0xFF1A1111),
      onBackground = Color(0xFFF1DEDD),
      surface = Color(0xFF1A1111),
      onSurface = Color(0xFFFFF9F9),
      surfaceVariant = Color(0xFF534342),
      onSurfaceVariant = Color(0xFFDCC6C4),
      surfaceTint = Color(0xFF271D1D),
      border = Color(0xFFB39E9D),
      borderVariant = Color(0xFF927F7D),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFFF1DEDD),
      inverseOnSurface = Color(0xFF322827),
      inversePrimary = Color(0xFF743432),
    ),
  ),
}
