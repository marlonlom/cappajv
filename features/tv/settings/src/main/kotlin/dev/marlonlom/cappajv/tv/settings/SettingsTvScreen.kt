/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.tv.settings.slot.SettingsContentSlot
import dev.marlonlom.cappajv.tv.settings.slot.SettingsMenuColumnSlot

/**
 * Displays the settings screen of the coffee catalog tv app.
 *
 * @author marlonlom
 *
 * @param appVersionNumber A string representing the current version number of the application.
 *
 */
@Composable
fun SettingsTvScreen(appVersionNumber: String) {
  var isLeftColumnFocused by remember { mutableStateOf(false) }
  var selectedListIndex by remember { mutableIntStateOf(0) }

  Box(modifier = Modifier.fillMaxSize()) {
    Row(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp)
        .padding(bottom = 48.dp),
      horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
      SettingsMenuColumnSlot(
        onLeftColumnFocused = { focused -> isLeftColumnFocused = focused },
        isListItemSelected = { index -> selectedListIndex == index },
        onListItemSelected = { index -> selectedListIndex = index },
      )

      SettingsContentSlot(
        selectedMenu = selectedListIndex,
        appVersionNumber = appVersionNumber,
      )
    }
  }
}
