/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.tv.settings.domain.SettingsMenu
import dev.marlonlom.cappajv.tv.settings.slot.SettingsMenuColumnSlot

@Composable
fun SettingsScreen() {
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

      Column(
        modifier = Modifier
          .background(
            MaterialTheme.colorScheme.surfaceTint,
            MaterialTheme.shapes.extraSmall,
          ),
      ) {
        Text(
          modifier = Modifier
            .fillMaxSize()
            .paddingFromBaseline(40.dp, 20.dp),
          text = "The [${SettingsMenu.entries[selectedListIndex].name}] section is not available yet.",
          textAlign = TextAlign.Center,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface,
          style = MaterialTheme.typography.titleMedium,
        )
      }
    }
  }
}
