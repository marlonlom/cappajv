/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.ui.main

import android.content.res.Configuration
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.marlonlom.apps.cappajv.features.catalog_list.CatalogListState

/**
 * Remembers the application ui state value.
 *
 * @author marlonlom
 *
 * @param windowSizeClass Window size class.
 * @param navController Navigation controller.
 * @param localConfiguration Local configuration object.
 *
 * @return Application ui state value.
 */
@Composable
fun rememberCappajvAppState(
  windowSizeClass: WindowSizeClass,
  navController: NavHostController = rememberNavController(),
  localConfiguration: Configuration = LocalConfiguration.current,
  catalogListState: CatalogListState,
): CappajvAppState = remember(
  windowSizeClass,
  navController,
  localConfiguration,
  catalogListState
) {
  CappajvAppState(
    navController = navController,
    windowSizeClass = windowSizeClass,
    localConfiguration = localConfiguration,
    catalogListState = catalogListState
  )
}

/**
 * Main application ui state data class.
 *
 * @author marlonlom
 *
 * @param windowSizeClass Window size class.
 * @param navController Navigation controller.
 * @param localConfiguration Local configuration.
 */
@Stable
data class CappajvAppState(
  internal val navController: NavHostController,
  private val windowSizeClass: WindowSizeClass,
  private val localConfiguration: Configuration,
  val catalogListState: CatalogListState,
) {
  val isCompactWidth get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

  val isCompactHeight get() = windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

  val isLandscapeOrientation get() = localConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE

  val is7InTabletWidth get() = localConfiguration.smallestScreenWidthDp.dp >= 600.dp

  val is10InTabletWidth get() = localConfiguration.smallestScreenWidthDp.dp >= 720.dp

  val canShowBottomNavigation get() = isCompactHeight.not().and(isLandscapeOrientation.not())

  val canShowNavigationRail get() = isCompactHeight.or(is7InTabletWidth.and(isLandscapeOrientation))

  /**
   * Changes selected top destination.
   *
   * @param selectedRoute Selected destination route.
   */
  fun changeTopDestination(selectedRoute: String) {
    navController.navigate(selectedRoute) {
      popUpTo(navController.graph.findStartDestination().id) {
        saveState = true
        inclusive = true
      }
      launchSingleTop = true
      restoreState = true
    }
  }
}
