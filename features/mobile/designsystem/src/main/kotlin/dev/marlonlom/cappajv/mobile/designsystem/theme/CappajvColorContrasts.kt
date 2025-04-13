/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * Branded color contrasts enum class.
 *
 * @author marlonlom
 *
 * @property light Light color scheme.
 * @property dark Dark color scheme.
 *
 */
enum class CappajvColorContrasts(val light: ColorScheme, val dark: ColorScheme) {
  /** Default branded color contrast: Standard. */
  STANDARD(
    light = lightColorScheme(
      primary = Color(0xFF904A47),
      onPrimary = Color(0xFFFFFFFF),
      primaryContainer = Color(0xFFFFDAD7),
      onPrimaryContainer = Color(0xFF3B080A),
      secondary = Color(0xFF775654),
      onSecondary = Color(0xFFFFFFFF),
      secondaryContainer = Color(0xFFFFDAD7),
      onSecondaryContainer = Color(0xFF2C1514),
      tertiary = Color(0xFF8A5022),
      onTertiary = Color(0xFFFFFFFF),
      tertiaryContainer = Color(0xFFFFDCC6),
      onTertiaryContainer = Color(0xFF301400),
      error = Color(0xFFBA1A1A),
      onError = Color(0xFFFFFFFF),
      errorContainer = Color(0xFFFFDAD6),
      onErrorContainer = Color(0xFF410002),
      background = Color(0xFFFFF8F7),
      onBackground = Color(0xFF231919),
      surface = Color(0xFFFFF8F7),
      onSurface = Color(0xFF231919),
      surfaceVariant = Color(0xFFF4DDDB),
      onSurfaceVariant = Color(0xFF534342),
      outline = Color(0xFF857371),
      outlineVariant = Color(0xFFD8C2C0),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFF382E2D),
      inverseOnSurface = Color(0xFFFFEDEB),
      inversePrimary = Color(0xFFFFB3AE),
      surfaceDim = Color(0xFFE8D6D4),
      surfaceBright = Color(0xFFFFF8F7),
      surfaceContainerLowest = Color(0xFFFFFFFF),
      surfaceContainerLow = Color(0xFFFFF0EF),
      surfaceContainer = Color(0xFFFCEAE8),
      surfaceContainerHigh = Color(0xFFF6E4E2),
      surfaceContainerHighest = Color(0xFFF1DEDD),
    ),
    dark = darkColorScheme(
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
      outline = Color(0xFFA08C8B),
      outlineVariant = Color(0xFF534342),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFFF1DEDD),
      inverseOnSurface = Color(0xFF382E2D),
      inversePrimary = Color(0xFF904A47),
      surfaceDim = Color(0xFF1A1111),
      surfaceBright = Color(0xFF423736),
      surfaceContainerLowest = Color(0xFF140C0C),
      surfaceContainerLow = Color(0xFF231919),
      surfaceContainer = Color(0xFF271D1D),
      surfaceContainerHigh = Color(0xFF322827),
      surfaceContainerHighest = Color(0xFF3D3231),
    ),
  ),

  /** Branded color contrast: Medium. */
  MEDIUM(
    light = lightColorScheme(
      primary = Color(0xFF6E2F2D),
      onPrimary = Color(0xFFFFFFFF),
      primaryContainer = Color(0xFFAA5F5C),
      onPrimaryContainer = Color(0xFFFFFFFF),
      secondary = Color(0xFF593B39),
      onSecondary = Color(0xFFFFFFFF),
      secondaryContainer = Color(0xFF8F6C6A),
      onSecondaryContainer = Color(0xFFFFFFFF),
      tertiary = Color(0xFF693508),
      onTertiary = Color(0xFFFFFFFF),
      tertiaryContainer = Color(0xFFA56536),
      onTertiaryContainer = Color(0xFFFFFFFF),
      error = Color(0xFF8C0009),
      onError = Color(0xFFFFFFFF),
      errorContainer = Color(0xFFDA342E),
      onErrorContainer = Color(0xFFFFFFFF),
      background = Color(0xFFFFF8F7),
      onBackground = Color(0xFF231919),
      surface = Color(0xFFFFF8F7),
      onSurface = Color(0xFF231919),
      surfaceVariant = Color(0xFFF4DDDB),
      onSurfaceVariant = Color(0xFF4E3F3E),
      outline = Color(0xFF6C5B5A),
      outlineVariant = Color(0xFF897675),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFF382E2D),
      inverseOnSurface = Color(0xFFFFEDEB),
      inversePrimary = Color(0xFFFFB3AE),
      surfaceDim = Color(0xFFE8D6D4),
      surfaceBright = Color(0xFFFFF8F7),
      surfaceContainerLowest = Color(0xFFFFFFFF),
      surfaceContainerLow = Color(0xFFFFF0EF),
      surfaceContainer = Color(0xFFFCEAE8),
      surfaceContainerHigh = Color(0xFFF6E4E2),
      surfaceContainerHighest = Color(0xFFF1DEDD),
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
      outline = Color(0xFFB39E9D),
      outlineVariant = Color(0xFF927F7D),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFFF1DEDD),
      inverseOnSurface = Color(0xFF322827),
      inversePrimary = Color(0xFF743432),
      surfaceDim = Color(0xFF1A1111),
      surfaceBright = Color(0xFF423736),
      surfaceContainerLowest = Color(0xFF140C0C),
      surfaceContainerLow = Color(0xFF231919),
      surfaceContainer = Color(0xFF271D1D),
      surfaceContainerHigh = Color(0xFF322827),
      surfaceContainerHighest = Color(0xFF3D3231),
    ),
  ),

  /** Branded color contrast: High. */
  HIGH(
    light = lightColorScheme(
      primary = Color(0xFF440F0F),
      onPrimary = Color(0xFFFFFFFF),
      primaryContainer = Color(0xFF6E2F2D),
      onPrimaryContainer = Color(0xFFFFFFFF),
      secondary = Color(0xFF341B1A),
      onSecondary = Color(0xFFFFFFFF),
      secondaryContainer = Color(0xFF593B39),
      onSecondaryContainer = Color(0xFFFFFFFF),
      tertiary = Color(0xFF3A1900),
      onTertiary = Color(0xFFFFFFFF),
      tertiaryContainer = Color(0xFF693508),
      onTertiaryContainer = Color(0xFFFFFFFF),
      error = Color(0xFF4E0002),
      onError = Color(0xFFFFFFFF),
      errorContainer = Color(0xFF8C0009),
      onErrorContainer = Color(0xFFFFFFFF),
      background = Color(0xFFFFF8F7),
      onBackground = Color(0xFF231919),
      surface = Color(0xFFFFF8F7),
      onSurface = Color(0xFF000000),
      surfaceVariant = Color(0xFFF4DDDB),
      onSurfaceVariant = Color(0xFF2E2120),
      outline = Color(0xFF4E3F3E),
      outlineVariant = Color(0xFF4E3F3E),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFF382E2D),
      inverseOnSurface = Color(0xFFFFFFFF),
      inversePrimary = Color(0xFFFFE7E4),
      surfaceDim = Color(0xFFE8D6D4),
      surfaceBright = Color(0xFFFFF8F7),
      surfaceContainerLowest = Color(0xFFFFFFFF),
      surfaceContainerLow = Color(0xFFFFF0EF),
      surfaceContainer = Color(0xFFFCEAE8),
      surfaceContainerHigh = Color(0xFFF6E4E2),
      surfaceContainerHighest = Color(0xFFF1DEDD),
    ),
    dark = darkColorScheme(
      primary = Color(0xFFFFF9F9),
      onPrimary = Color(0xFF000000),
      primaryContainer = Color(0xFFFFB9B5),
      onPrimaryContainer = Color(0xFF000000),
      secondary = Color(0xFFFFF9F9),
      onSecondary = Color(0xFF000000),
      secondaryContainer = Color(0xFFEBC1BE),
      onSecondaryContainer = Color(0xFF000000),
      tertiary = Color(0xFFFFFAF8),
      onTertiary = Color(0xFF000000),
      tertiaryContainer = Color(0xFFFFBC8F),
      onTertiaryContainer = Color(0xFF000000),
      error = Color(0xFFFFF9F9),
      onError = Color(0xFF000000),
      errorContainer = Color(0xFFFFBAB1),
      onErrorContainer = Color(0xFF000000),
      background = Color(0xFF1A1111),
      onBackground = Color(0xFFF1DEDD),
      surface = Color(0xFF1A1111),
      onSurface = Color(0xFFFFFFFF),
      surfaceVariant = Color(0xFF534342),
      onSurfaceVariant = Color(0xFFFFF9F9),
      outline = Color(0xFFDCC6C4),
      outlineVariant = Color(0xFFDCC6C4),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFFF1DEDD),
      inverseOnSurface = Color(0xFF000000),
      inversePrimary = Color(0xFF4E1717),
      surfaceDim = Color(0xFF1A1111),
      surfaceBright = Color(0xFF423736),
      surfaceContainerLowest = Color(0xFF140C0C),
      surfaceContainerLow = Color(0xFF231919),
      surfaceContainer = Color(0xFF271D1D),
      surfaceContainerHigh = Color(0xFF322827),
      surfaceContainerHighest = Color(0xFF3D3231),
    ),
  ),
  ;

  /**
   * Utility functions for enum class.
   *
   * @author marlonlom
   */
  companion object {

    /**
     * Returns the selected color contrast color scheme by name and if dark theme is applied.
     *
     * @param colorContrast Selected color contrast name.
     * @param darkTheme True/False is dark theme is applied.
     * @return Selected color contrast scheme.
     */
    fun findColorScheme(colorContrast: String, darkTheme: Boolean): ColorScheme = valueOf(colorContrast).let {
      if (darkTheme) it.dark else it.light
    }
  }
}
