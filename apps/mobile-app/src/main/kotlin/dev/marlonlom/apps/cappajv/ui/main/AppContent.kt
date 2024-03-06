/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import dev.marlonlom.apps.cappajv.features.welcome.WelcomeRoute
import dev.marlonlom.apps.cappajv.ui.main.scaffold.MainScaffold
import dev.marlonlom.apps.cappajv.ui.theme.CappajvTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Returns true/false if dynamic colors are applied to the ui.
 *
 * @param mainActivityUiState Main activity ui state.
 * @return true/false
 */
@Composable
private fun shouldUseDynamicColor(
  mainActivityUiState: MainActivityUiState
): Boolean = when (mainActivityUiState) {
  MainActivityUiState.Loading -> false
  is MainActivityUiState.Success -> mainActivityUiState.userData.useDynamicColor
}

/**
 * Returns true/false if dark theme is applied to the ui.
 *
 * @param mainActivityUiState Main activity ui state.
 * @return true/false
 */
@Composable
private fun shouldUseDarkTheme(
  mainActivityUiState: MainActivityUiState
): Boolean = when (mainActivityUiState) {
  MainActivityUiState.Loading -> isSystemInDarkTheme()
  is MainActivityUiState.Success -> {
    val useDarkTheme = mainActivityUiState.userData.useDarkTheme
    if (useDarkTheme.not()) isSystemInDarkTheme() else useDarkTheme
  }
}

/**
 * Application main content composable ui.
 *
 * @author marlonlom
 *
 * @param mainActivityUiState Main activity ui state.
 * @param appUiState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 * @param onOnboardingComplete Action for onboarding complete.
 */
@ExperimentalFoundationApi
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalCoroutinesApi
@Composable
fun AppContent(
  mainActivityUiState: MainActivityUiState,
  appUiState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  onOnboardingComplete: () -> Unit,
) = CappajvTheme(
  darkTheme = shouldUseDarkTheme(mainActivityUiState),
  dynamicColor = shouldUseDynamicColor(mainActivityUiState)
) {
  when (mainActivityUiState) {
    MainActivityUiState.Loading -> Unit

    is MainActivityUiState.Success -> {
      if (mainActivityUiState.userData.isOnboarding) {
        WelcomeRoute(
          appState = appUiState,
          onContinueHomeButtonClicked = onOnboardingComplete
        )
      } else {
        MainScaffold(
          mainActivityUiState = mainActivityUiState,
          appState = appUiState,
          appContentCallbacks = appContentCallbacks,
        )
      }
    }
  }
}
