/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.ui.main.scaffold

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.marlonlom.apps.cappajv.features.settings.SettingsDialog
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import dev.marlonlom.apps.cappajv.ui.main.MainActivityUiState
import dev.marlonlom.apps.cappajv.ui.navigation.CatalogDestination
import dev.marlonlom.apps.cappajv.ui.navigation.NavigationType

/**
 * Main scaffold composable ui.
 *
 * @author marlonlom
 *
 * @param mainActivityUiState Main activity ui state.
 * @param appState Main application ui state
 * @param appContentCallbacks Application content callbacks.
 */
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
fun MainScaffold(
  mainActivityUiState: MainActivityUiState,
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
) {

  val currentAppRoute = appState.navController.currentBackStackEntryAsState().value?.destination?.route
    ?: CatalogDestination.CatalogList.route
  var bottomNavSelectedIndex by rememberSaveable {
    mutableIntStateOf(
      CatalogDestination.topCatalogDestinations.map { it.route }.indexOf(currentAppRoute)
    )
  }
  val isTopDestination = currentAppRoute in CatalogDestination.topCatalogDestinations.map { it.route }

  var showSettingsDialog by rememberSaveable { mutableStateOf(false) }

  if (showSettingsDialog) {
    SettingsDialog(
      appState = appState,
      onDialogDismissed = { showSettingsDialog = false },
      openOssLicencesInfo = appContentCallbacks.openOssLicencesInfo,
      openExternalUrl = appContentCallbacks.openExternalUrl
    )
  }

  Scaffold(
    contentWindowInsets = WindowInsets(0, 0, 0, 0),
    bottomBar = {
      val isCurrentlyOnboarding: (MainActivityUiState) -> Boolean = { uiState ->
        when (uiState) {
          is MainActivityUiState.Success -> uiState.userData.isOnboarding

          else -> true
        }
      }

      val isBottomBarVisible = (appState.navigationType == NavigationType.BOTTOM_NAV).and(isTopDestination)
        .and(isCurrentlyOnboarding(mainActivityUiState).not())

      if (isBottomBarVisible) {
        BottomNavigationBar(
          selectedPosition = bottomNavSelectedIndex,
          onSelectedPositionChanged = { position, route ->
            if (route == CatalogDestination.Settings.route) {
              showSettingsDialog = true
            } else {
              bottomNavSelectedIndex = position
              appState.changeTopDestination(route)
            }
          },
        )
      }
    },
  ) { paddingValues ->
    MainScaffoldContent(
      paddingValues = paddingValues,
      appState = appState,
      appContentCallbacks = appContentCallbacks,
      selectedPosition = bottomNavSelectedIndex,
    ) { position, route ->
      if (route == CatalogDestination.Settings.route) {
        showSettingsDialog = true
      } else {
        bottomNavSelectedIndex = position
        appState.changeTopDestination(route)
      }
    }
  }
}
