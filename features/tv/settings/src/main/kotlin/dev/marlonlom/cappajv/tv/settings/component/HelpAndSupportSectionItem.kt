/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.settings.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ListItem
import androidx.tv.material3.ListItemDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import androidx.tv.material3.surfaceColorAtElevation

/**
 * Internal composable function that renders a single item within the help and support section.
 *
 * @param title Resource ID of the string to display as the item's title.
 */
@Composable
internal fun HelpAndSupportSectionItem(@StringRes title: Int) = ListItem(
  modifier = Modifier.padding(top = 20.dp),
  selected = false,
  onClick = {},
  headlineContent = {
    Text(
      text = stringResource(title),
      style = MaterialTheme.typography.bodyMedium
    )
  },
  colors = ListItemDefaults.colors(
    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp),
    contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
    focusedContentColor = MaterialTheme.colorScheme.surfaceVariant
  ),
)
