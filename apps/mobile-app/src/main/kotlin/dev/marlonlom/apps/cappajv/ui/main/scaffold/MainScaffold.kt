/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.ui.main.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.marlonlom.apps.cappajv.core.preferences.UserPreferencesRepository
import dev.marlonlom.apps.cappajv.features.catalog_list.CatalogListState
import dev.marlonlom.apps.cappajv.features.welcome.WelcomeRoute
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import dev.marlonlom.apps.cappajv.ui.main.MainActivityUiState
import dev.marlonlom.apps.cappajv.ui.main.rememberCappajvAppState
import dev.marlonlom.apps.cappajv.ui.navigation.CatalogDestination
import dev.marlonlom.apps.cappajv.ui.navigation.MainNavHost
import dev.marlonlom.apps.cappajv.ui.util.DevicePosture

/**
 * Main scaffold composable ui.
 *
 * @author marlonlom
 *
 * @param mainActivityUiState Main activity ui state.
 * @param windowSizeClass Window size class.
 * @param appContentCallbacks Application content callbacks.
 * @param userPreferencesRepository User preferences repository.
 * @param onOnboardingComplete Action for onboarding complete event.
 * @param appState Main application ui state
 */
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
fun MainScaffold(
  mainActivityUiState: MainActivityUiState,
  windowSizeClass: WindowSizeClass,
  devicePosture: DevicePosture,
  appContentCallbacks: AppContentCallbacks,
  userPreferencesRepository: UserPreferencesRepository,
  onOnboardingComplete: () -> Unit,
  catalogListState: CatalogListState,
  appState: CappajvAppState = rememberCappajvAppState(
    windowSizeClass = windowSizeClass,
    devicePosture = devicePosture,
    catalogListState = catalogListState
  ),
) {

  val currentAppRoute = appState.navController
    .currentBackStackEntryAsState().value?.destination?.route ?: CatalogDestination.CatalogList.route
  var bottomNavSelectedIndex by rememberSaveable {
    mutableIntStateOf(
      CatalogDestination.topCatalogDestinations.map { it.route }.indexOf(currentAppRoute)
    )
  }
  val isTopDestination = currentAppRoute in CatalogDestination.topCatalogDestinations.map { it.route }

  var showSettingsDialog by rememberSaveable { mutableStateOf(false) }

  Scaffold(
    contentWindowInsets = WindowInsets(0, 0, 0, 0),
    bottomBar = {
      val isCurrentlyOnboarding: (MainActivityUiState) -> Boolean = { uiState ->
        when (uiState) {
          is MainActivityUiState.Success ->
            uiState.userData.isOnboarding

          else -> true
        }
      }

      val isBottomBarVisible = appState.canShowBottomNavigation.and(isTopDestination)
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
      mainActivityUiState = mainActivityUiState,
      appState = appState,
      appContentCallbacks = appContentCallbacks,
      onOnboardingComplete = onOnboardingComplete,
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
}

/**
 * Main scaffold content composable ui.
 *
 * @author marlonlom
 *
 * @param paddingValues Padding values.
 * @param mainActivityUiState Main activity ui state.
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 * @param onOnboardingComplete Action for onboarding complete event.
 */
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
private fun MainScaffoldContent(
  paddingValues: PaddingValues,
  mainActivityUiState: MainActivityUiState,
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  onOnboardingComplete: () -> Unit,
  selectedPosition: Int,
  onSelectedPositionChanged: (Int, String) -> Unit
) {
  Box(
    modifier = Modifier
      .safeDrawingPadding()
      .padding(paddingValues),
    contentAlignment = Alignment.Center
  ) {
    when (mainActivityUiState) {
      is MainActivityUiState.Success -> {
        if (mainActivityUiState.userData.isOnboarding) {
          WelcomeRoute(
            appState = appState,
            onContinueHomeButtonClicked = onOnboardingComplete
          )
        } else {
          if (appState.canShowExpandedNavigationDrawer) {
            ExpandedNavigationDrawer(
              selectedPosition = selectedPosition,
              onSelectedPositionChanged = onSelectedPositionChanged,
            ) {
              MainNavHost(
                appState = appState,
                appContentCallbacks
              )
            }
          } else {
            Row {
              if (appState.canShowNavigationRail) {
                MainNavigationRail(
                  selectedPosition = selectedPosition,
                  onSelectedPositionChanged = onSelectedPositionChanged,
                )
              }

              MainNavHost(
                appState = appState,
                appContentCallbacks
              )
            }
          }
        }
      }

      else -> Unit
    }

  }
}
