/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.welcome.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.marlonlom.cappajv.ui.layout.DevicePosture
import dev.marlonlom.cappajv.ui.main.CappajvAppState

/**
 * Book mode folding onboarding screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param onContinueHomeButtonClicked Action for continue to home screen button clicked.
 */
@Composable
internal fun BookOnboardingScreen(
  appState: CappajvAppState,
  onContinueHomeButtonClicked: () -> Unit
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
      modifier = Modifier
        .fillMaxWidth(columnWidthRatio),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Box(modifier = Modifier.fillMaxSize()) { }
    }
    Column(
      modifier = Modifier.fillMaxWidth().safeContentPadding(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      DefaultOnboardingScreen(
        onOnboardingFinished = onContinueHomeButtonClicked
      )
    }
  }

}
