/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.ui.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import dev.marlonlom.cappajv.mobile.designsystem.theme.CappajvTheme
import dev.marlonlom.cappajv.mobile.onboarding.OnboardingScreen
import dev.marlonlom.cappajv.mobile.ui.navigation.CappajvScaffold
import org.koin.compose.KoinContext

/**
 * Displays the main content of the application based on the provided UI state.
 *
 * @author marlonlom
 *
 * @param uiState The current UI state of the MainActivity. This is used to
 * determine what content to show (e.g., onboarding, main screen, loading).
 * @param onOnboardingComplete Callback function that is invoked when the onboarding
 * process is completed. Used to update state or navigate accordingly.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainContent(uiState: MainActivityUiState, onOnboardingComplete: () -> Unit) = CappajvTheme(
  darkTheme = uiState.shouldUseDarkTheme(),
  dynamicColor = uiState.shouldUseDynamicColor(),
  colorContrast = uiState.shouldUseColorContrast(),
) {
  KoinContext {
    when (uiState) {
      MainActivityUiState.Loading -> Unit

      is MainActivityUiState.Success -> {
        if (uiState.userData.isOnboarding) {
          OnboardingScreen(onOnboardingFinished = onOnboardingComplete)
        } else {
          CappajvScaffold()
        }
      }
    }
  }
}
