/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.settings.slot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.tv.settings.R

/**
 * Internal composable function that displays the about setting slot.
 *
 * @author marlonlom
 *
 * @param appVersionNumber A string representing the current version number of the application.
 */
@Composable
internal fun AboutSettingSlot(appVersionNumber: String) {
  Text(
    text = stringResource(R.string.text_about_title),
    style = MaterialTheme.typography.headlineSmall,
    color = MaterialTheme.colorScheme.onBackground,
  )

  Text(
    modifier = Modifier
      .graphicsLayer { alpha = 0.8f }
      .padding(top = 10.dp),
    color = MaterialTheme.colorScheme.onBackground,
    text = stringResource(R.string.text_about_description),
    style = MaterialTheme.typography.bodyMedium,
  )

  Box(
    modifier = Modifier
      .padding(top = 20.dp)
      .fillMaxWidth()
      .height(2.dp)
      .background(MaterialTheme.colorScheme.border.copy(alpha = 0.6f)),
  )

  Text(
    modifier = Modifier
      .graphicsLayer { alpha = 0.6f }
      .padding(top = 20.dp),
    text = stringResource(R.string.text_about_app_version),
    color = MaterialTheme.colorScheme.onBackground,
    style = MaterialTheme.typography.labelMedium,
  )

  Text(
    modifier = Modifier.padding(top = 10.dp),
    text = appVersionNumber,
    color = MaterialTheme.colorScheme.onBackground,
    style = MaterialTheme.typography.labelLarge,
  )
}
