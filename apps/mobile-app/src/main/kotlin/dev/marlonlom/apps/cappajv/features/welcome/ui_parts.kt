/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.R
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

@Composable
internal fun WelcomePosterImage() {
  Image(
    painter = painterResource(id = R.drawable.img_welcome_poster),
    contentDescription = null,
    modifier = Modifier
      .height(160.dp),
    contentScale = ContentScale.Crop
  )
}

@Composable
internal fun WelcomeButton(onContinueHomeButtonClicked: () -> Unit) {
  Button(
    modifier = Modifier.padding(top = 40.dp, bottom = 20.dp),
    onClick = {
      onContinueHomeButtonClicked()
    },
    shape = MaterialTheme.shapes.small,
    colors = ButtonDefaults.buttonColors(
      containerColor = MaterialTheme.colorScheme.primary,
      contentColor = MaterialTheme.colorScheme.onPrimary,
    )
  ) {
    Text(text = stringResource(R.string.text_welcome_button))
  }
}

@Composable
internal fun WelcomeDetail(appState: CappajvAppState) {
  Text(
    modifier = Modifier
      .padding(horizontal = 20.dp),
    text = stringResource(R.string.text_welcome_detail),
    style = MaterialTheme.typography.bodyMedium,
    textAlign = getWelcomeTextAlign(appState),
    fontWeight = FontWeight.Normal
  )
}

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
private fun getWelcomeTextAlign(appState: CappajvAppState): TextAlign = when {
  appState.isLandscapeOrientation.and(appState.isCompactHeight) -> TextAlign.Start
  else -> TextAlign.Center
}
