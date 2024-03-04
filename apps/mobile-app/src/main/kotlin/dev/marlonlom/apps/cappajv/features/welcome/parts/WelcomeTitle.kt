/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.welcome.parts

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.R
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState


/**
 * Title text for welcome screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 */
@Composable
internal fun WelcomeTitle(appState: CappajvAppState) {
  Text(
    modifier = Modifier
      .paddingFromBaseline(top = 40.dp, bottom = 20.dp)
      .padding(horizontal = 20.dp),
    text = stringResource(R.string.text_welcome_title),
    style = MaterialTheme.typography.titleLarge,
    textAlign = getWelcomeTextAlign(appState),
    fontWeight = FontWeight.Bold
  )
}

@Composable
internal fun getWelcomeTextAlign(appState: CappajvAppState): TextAlign = when {
  appState.isCompactHeight -> TextAlign.Start
  else -> TextAlign.Center
}
