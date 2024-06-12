/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.welcome.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
 * TableTop mode folding welcome screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param onContinueHomeButtonClicked Action for continue button clicked.
 */
@Composable
internal fun TableTopWelcomeScreen(
  appState: CappajvAppState,
  onContinueHomeButtonClicked: () -> Unit,
) {
  val columnHeightRatio = when (appState.devicePosture) {
    is DevicePosture.Separating.TableTop -> appState.devicePosture.hingeRatio
    else -> 0.5f
  }

  Column(modifier = Modifier.fillMaxSize()) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(columnHeightRatio),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      WelcomePosterImage()
    }
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 40.dp),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
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
}
