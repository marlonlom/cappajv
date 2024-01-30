/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

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
  val contentHorizontalPadding = if (appState.isCompactWidth.not()) 80.dp else 20.dp
  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = contentHorizontalPadding),
    contentAlignment = Alignment.Center
  ) {
    if (appState.isCompactHeight) {
      LandscapeCompactWelcomeScreen(appState, onContinueHomeButtonClicked)
    } else {
      PortraitWelcomeScreen(appState, onContinueHomeButtonClicked)
    }
  }
}

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
  Column(
    modifier = Modifier
      .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(0.75f),
      horizontalArrangement = Arrangement.SpaceAround,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(modifier = Modifier.fillMaxWidth(0.35f)) {
        WelcomePosterImage()
      }
      Column(modifier = Modifier.fillMaxWidth()) {
        WelcomeTitle(appState)
        WelcomeDetail(appState)
      }
    }
    Row(
      modifier = Modifier.fillMaxWidth(0.6f),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      WelcomeButton(onContinueHomeButtonClicked)
    }
  }
}

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
    appState.is7InTabletWidth -> Modifier.fillMaxWidth(0.75f)
    appState.isCompactWidth -> Modifier.fillMaxWidth()
    else -> Modifier
  }
  Column(
    modifier = contentModifier,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    WelcomePosterImage()
    WelcomeTitle(appState)
    WelcomeDetail(appState)
    WelcomeButton(onContinueHomeButtonClicked)
  }
}
