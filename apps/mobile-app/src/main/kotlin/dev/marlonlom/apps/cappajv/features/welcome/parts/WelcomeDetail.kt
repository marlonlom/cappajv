/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.welcome.parts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.R
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * Details text for welcome screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState
 */
@Composable
internal fun WelcomeDetail(appState: CappajvAppState) {
  Text(
    modifier = Modifier
      .padding(horizontal = 20.dp),
    text = stringResource(R.string.text_welcome_detail),
    style = MaterialTheme.typography.bodyLarge,
    textAlign = getWelcomeTextAlign(appState),
    fontWeight = FontWeight.Normal
  )
}
