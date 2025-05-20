/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.main

import androidx.compose.runtime.Composable
import dev.marlonlom.cappajv.tv.R
import dev.marlonlom.cappajv.tv.onboarding.OnboardingScreen
import dev.marlonlom.cappajv.tv.ui.navigation.TvScaffold
import dev.marlonlom.cappajv.tv.ui.splash.SplashScreen

/**
 * Composable content for the TV Activity screen.
 *
 * Renders the UI based on the current [uiState], and triggers lifecycle-related callbacks.
 *
 * @author marlonlom
 *
 * @param uiState The current UI state of the screen, used to determine what content to display.
 * @param onStarted Callback invoked when the screen is first started or resumed.
 * @param onOnboarded Callback invoked when the user completes onboarding.
 */
@Composable
internal fun TvActivityContent(uiState: TvActivityUiState, onStarted: () -> Unit, onOnboarded: () -> Unit) {
  when (uiState) {
    TvActivityUiState.Starting -> {
      SplashScreen(onTimeout = onStarted)
    }

    is TvActivityUiState.Ready -> {
      val (userData) = uiState
      if (userData.isOnboarding) {
        OnboardingScreen(
          brandImage = R.drawable.img_logo,
          onOnboardingComplete = onOnboarded,
        )
      } else {
        TvScaffold()
      }
    }

    else -> Unit
  }
}
