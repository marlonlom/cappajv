/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.settings.slot

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.mobile.settings.R

/**
 * Provides a slot for displaying a headline on the catalog favorites screen.
 *
 * This internal composable function serves as a designated area where
 * a headline composable can be placed within the catalog favorites screen layout.
 *
 * @author marlonlom
 */
@Composable
internal fun CatalogSettingsHeadlineSlot() = Row(
  modifier = Modifier
    .fillMaxWidth()
    .background(
      color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
      shape = MaterialTheme.shapes.large,
    )
    .padding(vertical = 5.dp)
    .heightIn(min = 48.dp),
  verticalAlignment = Alignment.CenterVertically,
  horizontalArrangement = Arrangement.spacedBy(10.dp),
) {
  Image(
    painter = painterResource(R.drawable.img_coffee_silo),
    contentDescription = null,
    modifier = Modifier
      .size(36.dp, 36.dp)
      .padding(start = 5.dp),
  )
  Text(
    text = stringResource(R.string.text_settings),
    color = MaterialTheme.colorScheme.onSecondaryContainer,
  )
}
