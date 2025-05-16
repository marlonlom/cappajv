/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.settings.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.ListItem
import androidx.tv.material3.ListItemDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.tv.settings.R
import dev.marlonlom.cappajv.tv.settings.domain.SettingsMenu

/**
 * Internal composable function that renders a single item in the settings menu.
 *
 * @author marlonlom
 *
 * @param item The [SettingsMenu] item to display.
 * @param selected Whether this item is currently selected.
 * @param onClick A callback function invoked when this item is clicked.
 */
@Composable
internal fun SettingsMenuListItem(item: SettingsMenu, selected: Boolean, onClick: () -> Unit) = ListItem(
  modifier = Modifier.testTag("settings_menu_list_btn_${item.ordinal}"),
  selected = selected,
  onClick = onClick,
  leadingContent = {
    Icon(
      item.icon,
      modifier = Modifier
        .padding(vertical = 2.dp)
        .padding(start = 4.dp)
        .size(20.dp),
      contentDescription = stringResource(
        id = R.string.text_list_item_icon,
        item.title,
      ),
    )
  },
  headlineContent = {
    Text(
      text = stringResource(item.title),
      style = MaterialTheme.typography.bodyMedium.copy(
        fontWeight = FontWeight.Medium,
      ),
      modifier = Modifier.fillMaxWidth(),
    )
  },
  scale = ListItemDefaults.scale(focusedScale = 1f),
  shape = ListItemDefaults.shape(shape = MaterialTheme.shapes.small),
)
