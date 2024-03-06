/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import dev.marlonlom.apps.cappajv.ui.layout.DevicePosture

object NavigationTypeSelector {

  @JvmStatic
  fun fromWindowSize(
    wsc: WindowSizeClass,
    devicePosture: DevicePosture,
    screenWidthDp: Int,
  ): NavigationType = when {
    screenWidthDp >= 720 -> NavigationType.EXPANDED_NAV
    screenWidthDp >= 600 -> NavigationType.NAVIGATION_RAIL
    isCompactHeight(devicePosture, wsc) -> NavigationType.NAVIGATION_RAIL
    isFullyMediumWidth(devicePosture, wsc) -> NavigationType.NAVIGATION_RAIL
    isLandscapeBook(devicePosture, wsc) -> NavigationType.NAVIGATION_RAIL
    else -> NavigationType.BOTTOM_NAV
  }

  private fun isCompactHeight(devicePosture: DevicePosture, wsc: WindowSizeClass) =
    devicePosture == DevicePosture.Normal && wsc.heightSizeClass == WindowHeightSizeClass.Compact

  private fun isFullyMediumWidth(devicePosture: DevicePosture, wsc: WindowSizeClass) =
    devicePosture == DevicePosture.Normal && wsc.widthSizeClass == WindowWidthSizeClass.Medium

  private fun isLandscapeBook(devicePosture: DevicePosture, wsc: WindowSizeClass) =
    devicePosture is DevicePosture.Separating.Book && wsc.widthSizeClass >= WindowWidthSizeClass.Medium
}

enum class NavigationType {
  BOTTOM_NAV, NAVIGATION_RAIL, EXPANDED_NAV
}
