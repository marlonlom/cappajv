/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.welcome.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.features.welcome.parts.WelcomeButton
import dev.marlonlom.apps.cappajv.features.welcome.parts.WelcomeDetail
import dev.marlonlom.apps.cappajv.features.welcome.parts.WelcomePosterImage
import dev.marlonlom.apps.cappajv.features.welcome.parts.WelcomeTitle
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * Portrait welcome screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param onContinueHomeButtonClicked Action for continue button clicked.
 */
@Composable
internal fun PortraitWelcomeScreen(
  appState: CappajvAppState,
  onContinueHomeButtonClicked: () -> Unit,
) {
  val contentModifier = when {
    appState.isExpandedWidth -> Modifier.fillMaxWidth(0.5f)
    appState.isMediumWidth -> Modifier.fillMaxWidth(0.75f)
    else -> Modifier.fillMaxWidth()
  }

  Column(
    modifier = contentModifier,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    WelcomePosterImage()
    WelcomeTitle(appState)
    WelcomeDetail(appState)
    WelcomeButton(
      modifier = Modifier
        .padding(horizontal = 20.dp)
        .padding(bottom = 20.dp, top = 40.dp),
      onContinueHomeButtonClicked = onContinueHomeButtonClicked
    )
  }
}
