/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.features.welcome.screens.BookWelcomeScreen
import dev.marlonlom.cappajv.features.welcome.screens.LandscapeCompactWelcomeScreen
import dev.marlonlom.cappajv.features.welcome.screens.PortraitWelcomeScreen
import dev.marlonlom.cappajv.features.welcome.screens.TableTopWelcomeScreen
import dev.marlonlom.cappajv.ui.layout.DevicePosture
import dev.marlonlom.cappajv.ui.main.CappajvAppState
import dev.marlonlom.cappajv.ui.main.scaffold.ScaffoldContentType

/**
 * Welcome route composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param onContinueHomeButtonClicked Action for continue button clicked.
 */
@Composable
fun WelcomeRoute(
  appState: CappajvAppState,
  onContinueHomeButtonClicked: () -> Unit
) {
  val contentHorizontalPadding = when {
    appState.devicePosture is DevicePosture.Separating.Book -> 0.dp
    appState.isCompactWidth.not() -> 80.dp
    else -> 20.dp
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = contentHorizontalPadding),
    contentAlignment = Alignment.Center
  ) {
    when (appState.scaffoldContentType) {
      ScaffoldContentType.SinglePane -> {
        when {
          appState.isCompactHeight -> {
            LandscapeCompactWelcomeScreen(appState, onContinueHomeButtonClicked)
          }

          else -> {
            PortraitWelcomeScreen(appState, onContinueHomeButtonClicked)
          }
        }
      }

      is ScaffoldContentType.TwoPane -> {
        when (appState.devicePosture) {
          is DevicePosture.Separating.TableTop -> {
            TableTopWelcomeScreen(appState, onContinueHomeButtonClicked)
          }

          is DevicePosture.Separating.Book -> {
            BookWelcomeScreen(appState, onContinueHomeButtonClicked)
          }

          else -> {
            PortraitWelcomeScreen(appState, onContinueHomeButtonClicked)
          }
        }

      }
    }
  }
}
