/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.ui.main

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.marlonlom.cappajv.ui.layout.DevicePosture
import dev.marlonlom.cappajv.ui.main.scaffold.ScaffoldContentClassifier
import dev.marlonlom.cappajv.ui.navigation.CatalogDestination.Detail
import dev.marlonlom.cappajv.ui.navigation.NavigationTypeSelector

/**
 * Remembers the application ui state value.
 *
 * @author marlonlom
 *
 * @param windowSizeClass Window size class.
 * @param navController Navigation controller.
 *
 * @return Application ui state value.
 */
@Composable
fun rememberCappajvAppState(
  windowSizeClass: WindowSizeClass,
  devicePosture: DevicePosture,
  screenWidthDp: Int,
  isLandscape: Boolean,
  navController: NavHostController = rememberNavController(),
): CappajvAppState = remember(
  windowSizeClass,
  devicePosture,
  screenWidthDp,
  isLandscape,
  navController,
) {
  CappajvAppState(
    navController = navController,
    windowSizeClass = windowSizeClass,
    devicePosture = devicePosture,
    screenWidthDp = screenWidthDp,
    isLandscape = isLandscape,
  )
}

/**
 * Main application ui state data class.
 *
 * @author marlonlom
 *
 * @param navController Navigation controller.
 * @param windowSizeClass Window size class.
 * @property devicePosture Device posture, used for detecting foldable features.
 */
@Stable
data class CappajvAppState(
  internal val navController: NavHostController,
  val windowSizeClass: WindowSizeClass,
  val devicePosture: DevicePosture,
  val screenWidthDp: Int,
  val isLandscape: Boolean,
) {
  val isCompactWidth get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

  val isCompactHeight get() = windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

  val isMediumWidth get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium

  val isExpandedWidth get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded

  val scaffoldContentType
    get() = ScaffoldContentClassifier.classify(
      devicePosture,
      isExpandedWidth,
      isMediumWidth,
      isCompactHeight,
    )

  val navigationType get() = NavigationTypeSelector.fromWindowSize(windowSizeClass, devicePosture, screenWidthDp)

  /**
   * Changes selected top destination.
   *
   * @param selectedRoute Selected destination route.
   */
  fun changeTopDestination(selectedRoute: String) {
    navController.navigate(selectedRoute) {
      navController.graph.startDestinationRoute?.let { route ->
        popUpTo(route) {
          saveState = true
        }
        launchSingleTop = true
        restoreState = true
      }
    }
  }

  fun goToDetail(catalogId: Long) {
    navController.navigate(Detail.createRoute(catalogId))
  }
}
