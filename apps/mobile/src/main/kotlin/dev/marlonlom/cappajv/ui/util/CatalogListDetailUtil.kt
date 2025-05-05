/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.ui.util

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

/**
 * Utility object for managing shared logic between catalog list and detail views.
 *
 * Contains helper methods and constants used across catalog-related screens,
 * particularly for coordinating behavior or state between list and detail UIs
 * (e.g., in single-pane or two-pane layouts).
 *
 * @author marlonlom
 */
object CatalogListDetailUtil {

  /**
   * Determines the number of columns to be displayed in a grid layout based on the current UI state.
   *
   * This function evaluates the visibility of the two-pane layout and the navigation rail,
   * returning an appropriate number of grid columns depending on the combination of visible elements.
   *
   * @author marlonlom
   *
   * @param isTwoPaneVisible A lambda that returns `true` if the two-pane layout is currently visible, `false` otherwise.
   * @param isNavigationRailVisible A lambda that returns `true` if the navigation rail is currently visible, `false` otherwise.
   * @return An [Int] representing the number of columns to display in the grid.
   */
  @SuppressLint("ConfigurationScreenWidthHeight")
  @Composable
  fun gridColumnsCount(isTwoPaneVisible: () -> Boolean, isNavigationRailVisible: () -> Boolean): Int {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    val isCompactHeight = LocalConfiguration.current.screenHeightDp < 480
    if (isLandscape.and(isCompactHeight).and(isTwoPaneVisible())) return 3

    if (isTwoPaneVisible().not().and(isNavigationRailVisible())) return 4
    if (isTwoPaneVisible()) return 3
    return 2
  }

  /**
   * Computes the padding values for the detail screen content based on the current UI layout state.
   *
   * This composable calculates dynamic [PaddingValues] depending on whether a two-pane layout
   * or a navigation rail is visible. Useful for adapting UI spacing for responsive designs.
   *
   * @param isTwoPaneVisible A lambda that returns `true` if the UI is in two-pane mode.
   * @param isNavigationRailVisible A lambda that returns `true` if the navigation rail is currently visible.
   * @return [PaddingValues] object reflecting the appropriate padding for the current layout state.
   */
  @SuppressLint("ConfigurationScreenWidthHeight")
  @Composable
  fun detailContentPadding(isTwoPaneVisible: () -> Boolean, isNavigationRailVisible: () -> Boolean): PaddingValues {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    val isCompactHeight = LocalConfiguration.current.screenHeightDp < 480
    if (isLandscape.and(isCompactHeight).and(isTwoPaneVisible().not())) return PaddingValues(horizontal = 80.dp)

    if (isTwoPaneVisible().not().and(isNavigationRailVisible())) return PaddingValues(horizontal = 60.dp)
    return PaddingValues(0.dp)
  }
}
