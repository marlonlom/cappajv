/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.welcome.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.ui.layout.DevicePosture

/**
 * TableTop folding mode onboarding screen composable ui.
 *
 * @author marlonlom
 *
 * @param devicePosture Application device posture.
 * @param onOnboardingFinished Action for onboarding finished event.
 */
@Composable
internal fun TableTopOnboardingScreen(devicePosture: DevicePosture, onOnboardingFinished: () -> Unit) {
  val columnHeightRatio = when (devicePosture) {
    is DevicePosture.Separating.TableTop -> devicePosture.hingeRatio
    else -> 0.5f
  }

  Column(modifier = Modifier.fillMaxSize()) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(columnHeightRatio)
        .safeContentPadding(),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      DefaultLandscapeOnboardingScreen(
        onOnboardingFinished = onOnboardingFinished,
      )
    }
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 40.dp),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically,
    ) {
    }
  }
}
