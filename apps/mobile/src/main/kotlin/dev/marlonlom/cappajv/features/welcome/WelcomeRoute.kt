/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.features.welcome.onboarding.BookOnboardingScreen
import dev.marlonlom.cappajv.features.welcome.onboarding.DefaultLandscapeOnboardingScreen
import dev.marlonlom.cappajv.features.welcome.onboarding.DefaultOnboardingScreen
import dev.marlonlom.cappajv.features.welcome.onboarding.TableTopOnboardingScreen
import dev.marlonlom.cappajv.ui.layout.DevicePosture
import dev.marlonlom.cappajv.ui.main.CappajvAppState
import dev.marlonlom.cappajv.ui.main.scaffold.ScaffoldContentType

/**
 * Welcome route composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param onOnboardingFinished Action for continue button clicked.
 */
@Composable
fun WelcomeRoute(appState: CappajvAppState, onOnboardingFinished: () -> Unit) {
  val contentHorizontalPadding = when {
    appState.devicePosture is DevicePosture.Separating.Book -> 0.dp
    appState.isCompactWidth.not() -> 80.dp
    else -> 20.dp
  }
  Box(
    modifier = Modifier
      .background(MaterialTheme.colorScheme.background)
      .fillMaxSize()
      .padding(horizontal = contentHorizontalPadding),
    contentAlignment = Alignment.Center,
  ) {
    when (appState.scaffoldContentType) {
      ScaffoldContentType.SinglePane -> {
        when {
          appState.devicePosture is DevicePosture.Separating.Book -> {
            BookOnboardingScreen(appState.devicePosture, onOnboardingFinished)
          }

          appState.isCompactHeight -> {
            DefaultLandscapeOnboardingScreen(onOnboardingFinished)
          }

          else -> {
            DefaultOnboardingScreen(onOnboardingFinished)
          }
        }
      }

      is ScaffoldContentType.TwoPane -> {
        when (appState.devicePosture) {
          is DevicePosture.Separating.TableTop -> {
            TableTopOnboardingScreen(appState.devicePosture, onOnboardingFinished)
          }

          is DevicePosture.Separating.Book -> {
            BookOnboardingScreen(appState.devicePosture, onOnboardingFinished)
          }

          else -> {
            DefaultOnboardingScreen(onOnboardingFinished)
          }
        }
      }
    }
  }
}
