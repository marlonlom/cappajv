/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.welcome.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
 * Landscape compact welcome screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param onContinueHomeButtonClicked Action for continue button clicked.
 */
@Composable
internal fun LandscapeCompactWelcomeScreen(
  appState: CappajvAppState,
  onContinueHomeButtonClicked: () -> Unit,
) {
  val contentMaxWidthRatio = if (appState.isMediumWidth) 1f else 0.75f
  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(contentMaxWidthRatio),
      horizontalArrangement = Arrangement.spacedBy(20.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(
        horizontalAlignment = Alignment.End,
      ) {
        WelcomePosterImage()
      }
      Column(modifier = Modifier.fillMaxWidth()) {
        WelcomeTitle(appState)
        WelcomeDetail(appState)
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.End,
          verticalAlignment = Alignment.CenterVertically
        ) {
          WelcomeButton(
            modifier = Modifier.padding(20.dp),
            onContinueHomeButtonClicked = onContinueHomeButtonClicked
          )
        }
      }
    }
  }
}
