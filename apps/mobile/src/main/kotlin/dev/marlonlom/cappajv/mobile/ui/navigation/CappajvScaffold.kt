/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource

/**
 * Sets up the base scaffold layout for the Cappajv application.
 *
 * This function defines the overall structure of the app UI, including elements
 * such as the top app bar, bottom navigation, drawer, or other scaffold-related
 * components. Typically used as the main container for composable content.
 *
 * @author marlonlom
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
internal fun CappajvScaffold() {
  var currentDestination by rememberSaveable { mutableStateOf(CappajvDestinations.HOME) }
  val navigator = rememberListDetailPaneScaffoldNavigator<Long>()
  val layoutType = calculateNavigationSuiteType()
  val hideNavigationMenu = remember {
    derivedStateOf {
      navigator.scaffoldValue.primary.equals(PaneAdaptedValue.Expanded).and(
        navigator.scaffoldValue.secondary.equals(PaneAdaptedValue.Hidden),
      )
    }
  }

  NavigationSuiteScaffold(
    layoutType = when {
      hideNavigationMenu.value -> NavigationSuiteType.None
      else -> layoutType
    },
    navigationSuiteItems = {
      CappajvDestinations.entries.forEach {
        item(
          modifier = if (layoutType != NavigationSuiteType.NavigationBar) {
            Modifier.safeDrawingPadding()
          } else {
            Modifier
          },
          selected = currentDestination == it,
          onClick = {
            currentDestination = it
          },
          icon = {
            Icon(
              imageVector = it.icon,
              contentDescription = stringResource(it.hint),
            )
          },
          label = {
            Text(stringResource(it.title))
          },
        )
      }
    },
    content = {
      CappajvScaffoldContent(
        currentDestination = currentDestination,
        navigator = navigator,
        layoutType = layoutType,
      )
    },
  )
}

/**
 * Calculates the appropriate [NavigationSuiteType] based on the provided {@link WindowAdaptiveInfo}.
 * <br><br>
 * This function prioritizes displaying a [NavigationSuiteType.NavigationRail] when the
 * window height is compact. Otherwise, it uses the default calculation from
 * [NavigationSuiteScaffoldDefaults].
 *
 * @author marlonlom
 *
 * @param adaptiveInfo The [WindowAdaptiveInfo] to determine the navigation suite type.
 *
 * @return The calculated [NavigationSuiteType] for the current window configuration.
 */
@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
private fun calculateNavigationSuiteType(
  adaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
): NavigationSuiteType {
  val isMediumDpWidth = LocalConfiguration.current.screenWidthDp.let { it.coerceIn(600, 840) == it }
  if (isMediumDpWidth) return NavigationSuiteType.NavigationRail
  if (LocalConfiguration.current.screenHeightDp <= 480) return NavigationSuiteType.NavigationRail
  return calculateFromAdaptiveInfo(adaptiveInfo)
}
