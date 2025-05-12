/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.settings.slot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.tv.settings.domain.SettingsMenu

@Composable
fun SettingsContentSlot(selectedMenu: Int) = Column(
  modifier = Modifier.background(
      MaterialTheme.colorScheme.surface,
      MaterialTheme.shapes.extraSmall,
    ),
) {
  Text(
    modifier = Modifier
      .fillMaxSize()
      .paddingFromBaseline(40.dp, 20.dp),
    text = "The [${SettingsMenu.entries[selectedMenu].name}] section is not available yet.",
    textAlign = TextAlign.Center,
    fontWeight = FontWeight.Bold,
    color = MaterialTheme.colorScheme.onSurface,
    style = MaterialTheme.typography.titleMedium,
  )
}
