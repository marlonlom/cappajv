/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.welcome.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.features.welcome.parts.WelcomeButton
import dev.marlonlom.cappajv.features.welcome.parts.WelcomeDetail
import dev.marlonlom.cappajv.features.welcome.parts.WelcomePosterImage
import dev.marlonlom.cappajv.features.welcome.parts.WelcomeTitle
import dev.marlonlom.cappajv.ui.layout.DevicePosture
import dev.marlonlom.cappajv.ui.main.CappajvAppState

/**
 * Book mode folding welcome screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param onContinueHomeButtonClicked Action for continue button clicked.
 */
@Composable
internal fun BookWelcomeScreen(
  appState: CappajvAppState,
  onContinueHomeButtonClicked: () -> Unit,
) {
  val columnWidthRatio = when (appState.devicePosture) {
    is DevicePosture.Separating.Book -> appState.devicePosture.hingeRatio
    else -> 0.5f
  }
  Row(
    modifier = Modifier.fillMaxSize(),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Column(
      modifier = Modifier.fillMaxWidth(columnWidthRatio),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      WelcomePosterImage()
    }
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      WelcomeTitle(appState)
      WelcomeDetail(appState)
      WelcomeButton(
        modifier = Modifier.padding(20.dp),
        onContinueHomeButtonClicked = onContinueHomeButtonClicked
      )
    }
  }
}
