/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.settings.slot

import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.tv.settings.component.SettingsMenuListItem
import dev.marlonlom.cappajv.tv.settings.domain.SettingsMenu

/**
 * Composable function that displays a settings menu column layout.
 *
 * @author marlonlom
 *
 * @param onLeftColumnFocused Callback triggered with focus state of the left column.
 * @param isListItemSelected Function that returns whether a given item (by index) is selected.
 * @param onListItemSelected Callback triggered when an item (by index) is selected.
 */
@Composable
internal fun SettingsMenuColumnSlot(
  onLeftColumnFocused: (Boolean) -> Unit,
  isListItemSelected: (Int) -> Boolean,
  onListItemSelected: (Int) -> Unit,
) = Column(
  modifier = Modifier
    .fillMaxWidth(0.32f)
    .verticalScroll(rememberScrollState())
    .fillMaxHeight()
    .onFocusChanged {
      onLeftColumnFocused(it.hasFocus)
    }
    .focusRestorer()
    .focusGroup(),
  verticalArrangement = Arrangement.spacedBy(12.dp),
) {
  SettingsMenu.entries.forEachIndexed { index, menu ->
    key(index) {
      SettingsMenuListItem(
        item = menu,
        selected = isListItemSelected(index),
        onClick = { onListItemSelected(index) },
      )
    }
  }
}
