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
import androidx.window.layout.FoldingFeature
import dev.marlonlom.apps.cappajv.features.catalog_list.CatalogListState
import dev.marlonlom.apps.cappajv.ui.util.DevicePosture

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
  devicePosture: DevicePosture,
  navController: NavHostController = rememberNavController(),
  localConfiguration: Configuration = LocalConfiguration.current,
  catalogListState: CatalogListState,
): CappajvAppState = remember(
  windowSizeClass,
  devicePosture,
  navController,
  localConfiguration,
  catalogListState
) {
  CappajvAppState(
    navController = navController,
    windowSizeClass = windowSizeClass,
    localConfiguration = localConfiguration,
    devicePosture = devicePosture,
    catalogListState = catalogListState
  )
}

/**
 * Main application ui state data class.
 *
 * @author marlonlom
 *
 * @param navController Navigation controller.
 * @param windowSizeClass Window size class.
 * @param localConfiguration Local configuration.
 * @property devicePosture Device posture, used for detecting foldable features.
 * @property catalogListState Catalog list ui state value.
 */
@Stable
data class CappajvAppState(
  internal val navController: NavHostController,
  val windowSizeClass: WindowSizeClass,
  private val localConfiguration: Configuration,
  val devicePosture: DevicePosture,
  val catalogListState: CatalogListState,
) {
  val isCompactWidth get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

  val isCompactHeight get() = windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

  val isLandscapeOrientation get() = localConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE

  val is7InTabletWidth get() = localConfiguration.smallestScreenWidthDp.dp >= 600.dp

  val is10InTabletWidth get() = localConfiguration.smallestScreenWidthDp.dp >= 720.dp

  val canShowBottomNavigation get() = isCompactWidth

  val canShowNavigationRail get() = isCompactWidth.not().and(is10InTabletWidth.not())

  val canShowExpandedNavigationDrawer get() = isCompactWidth.not().and(is10InTabletWidth)

  val isDeviceBookPosture get() = devicePosture is DevicePosture.BookPosture

  val isDeviceBookPostureVertical
    get() = when (devicePosture) {
      is DevicePosture.BookPosture -> {
        devicePosture.orientation == FoldingFeature.Orientation.VERTICAL
      }

      else -> false
    }

  val isDeviceBookPostureHorizontal
    get() = when (devicePosture) {
      is DevicePosture.BookPosture -> {
        devicePosture.orientation == FoldingFeature.Orientation.HORIZONTAL
      }

      else -> false
    }

  val isDeviceSeparating get() = devicePosture is DevicePosture.Separating

  val isDeviceSeparatingVertical
    get() = when (devicePosture) {
      is DevicePosture.Separating -> {
        devicePosture.orientation == FoldingFeature.Orientation.VERTICAL
      }

      else -> false
    }

  val isDeviceSeparatingHorizontal
    get() = when (devicePosture) {
      is DevicePosture.Separating -> {
        devicePosture.orientation == FoldingFeature.Orientation.HORIZONTAL
      }

      else -> false
    }

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
