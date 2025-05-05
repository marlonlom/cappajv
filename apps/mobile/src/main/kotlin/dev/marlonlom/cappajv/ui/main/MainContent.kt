/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.ui.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import dev.marlonlom.cappajv.mobile.designsystem.theme.CappajvColorContrasts
import dev.marlonlom.cappajv.mobile.designsystem.theme.CappajvTheme
import dev.marlonlom.cappajv.mobile.onboarding.OnboardingScreen
import dev.marlonlom.cappajv.ui.navigation.CappajvScaffold
import org.koin.compose.KoinContext

/**
 * Returns true/false if dynamic colors are applied to the ui.
 *
 * @param mainActivityUiState Main activity ui state.
 * @return true/false
 */
@Composable
private fun shouldUseDynamicColor(mainActivityUiState: MainActivityUiState): Boolean = when (mainActivityUiState) {
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
private fun shouldUseDarkTheme(mainActivityUiState: MainActivityUiState): Boolean = when (mainActivityUiState) {
  MainActivityUiState.Loading -> isSystemInDarkTheme()
  is MainActivityUiState.Success -> {
    val useDarkTheme = mainActivityUiState.userData.useDarkTheme
    if (useDarkTheme.not()) isSystemInDarkTheme() else useDarkTheme
  }
}

/**
 * Returns the color contrast applied to the ui.
 *
 * @param mainActivityUiState Main activity ui state.
 * @return Color contrast name.
 */
@Composable
private fun shouldUseColorContrast(mainActivityUiState: MainActivityUiState): String = when (mainActivityUiState) {
  MainActivityUiState.Loading -> CappajvColorContrasts.STANDARD
  is MainActivityUiState.Success -> mainActivityUiState.userData.colorContrast
}.name

/**
 * Displays the main content of the application based on the provided UI state.
 *
 * @author marlonlom
 *
 * @param mainActivityUiState The current UI state of the MainActivity. This is used to
 * determine what content to show (e.g., onboarding, main screen, loading).
 * @param onOnboardingComplete Callback function that is invoked when the onboarding
 * process is completed. Used to update state or navigate accordingly.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainContent(mainActivityUiState: MainActivityUiState, onOnboardingComplete: () -> Unit) = CappajvTheme(
  darkTheme = shouldUseDarkTheme(mainActivityUiState),
  dynamicColor = shouldUseDynamicColor(mainActivityUiState),
  colorContrast = shouldUseColorContrast(mainActivityUiState),
) {
  KoinContext {
    when (mainActivityUiState) {
      MainActivityUiState.Loading -> Unit

      is MainActivityUiState.Success -> {
        if (mainActivityUiState.userData.isOnboarding) {
          OnboardingScreen(onOnboardingFinished = onOnboardingComplete)
        } else {
          CappajvScaffold()
        }
      }
    }
  }
}
