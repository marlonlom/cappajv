/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.settings.slot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import dev.marlonlom.cappajv.tv.settings.domain.SettingsMenu

/**
 * Composable function that displays the content for a specific settings menu item.
 *
 * @author marlonlom
 *
 * @param selectedMenu The identifier of the currently selected menu item.
 */
@Composable
internal fun SettingsContentSlot(selectedMenu: Int) = Column(
  modifier = Modifier
    .padding(horizontal = 20.dp)
    .background(
      MaterialTheme.colorScheme.surface,
      MaterialTheme.shapes.small,
    ),
) {
  when (selectedMenu) {
    SettingsMenu.ABOUT.ordinal -> AboutSettingSlot()
    SettingsMenu.HELP_SUPPORT.ordinal -> HelpSupportSettingSlot()
  }
}
