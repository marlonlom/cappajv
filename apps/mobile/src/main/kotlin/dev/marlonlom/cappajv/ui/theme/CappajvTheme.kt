/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Brand color schemes single object.
 *
 * @author marlonlom
 */
internal object BrandColorSchemes {

  val dark = darkColorScheme(
    primary = BrandColors.warmApricot2,
    onPrimary = BrandColors.stirlandMud,
    primaryContainer = BrandColors.notoriousNeanderthal,
    onPrimaryContainer = BrandColors.peachPuff,
    secondary = BrandColors.warmApricot,
    onSecondary = BrandColors.stirlandMud2,
    secondaryContainer = BrandColors.brown,
    onSecondaryContainer = BrandColors.peachPuff2,
    tertiary = BrandColors.gooseBill,
    onTertiary = BrandColors.ibexBrown,
    tertiaryContainer = BrandColors.bestialBrown,
    onTertiaryContainer = BrandColors.whisperingPeach,
    error = BrandColors.peachBud,
    errorContainer = BrandColors.chokecherry,
    onError = BrandColors.arcaviaRed,
    onErrorContainer = BrandColors.forgottenPink,
    background = BrandColors.kalaBlack,
    onBackground = BrandColors.springWood,
    surface = BrandColors.kalaBlack,
    onSurface = BrandColors.springWood,
    surfaceVariant = BrandColors.peppercornRent,
    onSurfaceVariant = BrandColors.hazyTaupe,
    outline = BrandColors.puttyYellow,
    inverseOnSurface = BrandColors.kalaBlack,
    inverseSurface = BrandColors.springWood,
    inversePrimary = BrandColors.spearShaft,
    surfaceTint = BrandColors.warmApricot2,
    outlineVariant = BrandColors.peppercornRent,
    scrim = BrandColors.black,
  )

  val light = lightColorScheme(
    primary = BrandColors.spearShaft,
    onPrimary = BrandColors.white,
    primaryContainer = BrandColors.peachPuff,
    onPrimaryContainer = BrandColors.zinnwalditeBrown,
    secondary = BrandColors.spearShaft2,
    onSecondary = BrandColors.white,
    secondaryContainer = BrandColors.peachPuff2,
    onSecondaryContainer = BrandColors.zinnwalditeBrown,
    tertiary = BrandColors.brownie,
    onTertiary = BrandColors.white,
    tertiaryContainer = BrandColors.whisperingPeach,
    onTertiaryContainer = BrandColors.chocolateMelange,
    error = BrandColors.heartwarming,
    errorContainer = BrandColors.forgottenPink,
    onError = BrandColors.white,
    onErrorContainer = BrandColors.biltong,
    background = BrandColors.lavenderBlush,
    onBackground = BrandColors.kalaBlack,
    surface = BrandColors.lavenderBlush,
    onSurface = BrandColors.kalaBlack,
    surfaceVariant = BrandColors.naturale,
    onSurfaceVariant = BrandColors.peppercornRent,
    outline = BrandColors.camelsHump,
    inverseOnSurface = BrandColors.moonlitBeach,
    inverseSurface = BrandColors.acadia,
    inversePrimary = BrandColors.warmApricot2,
    surfaceTint = BrandColors.spearShaft,
    outlineVariant = BrandColors.hazyTaupe,
    scrim = BrandColors.black,
  )

}

/**
 * Brand theme composable function for Cappajv app.
 *
 * @author marlonlom
 *
 * @param darkTheme True/False if dark theme is applied.
 * @param dynamicColor True/False if dynamic colors are applied.
 * @param colorContrast Selected color contrast, defaults to [BrandedColorContrasts.STANDARD]
 * @param content Composable ui contents.
 */
@ExperimentalMaterial3Api
@Composable
fun CappajvTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true,
  colorContrast: String = BrandedColorContrasts.STANDARD.name.lowercase(),
  content: @Composable () -> Unit
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    else -> BrandedColorContrasts.findColorScheme(colorContrast,darkTheme)
  }

  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = colorScheme.surface.toArgb()
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
    }
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = BrandFont.appTypography,
    shapes = CappaShapes,
    content = content
  )
}
