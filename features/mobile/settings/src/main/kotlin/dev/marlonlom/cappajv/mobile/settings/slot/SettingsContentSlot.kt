/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.settings.slot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun SettingsContentSlot(title: @Composable () -> Unit, content: @Composable () -> Unit) = Column(
  modifier = Modifier
    .fillMaxWidth()
    .padding(vertical = 10.dp)
    .background(
      color = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.5f),
      shape = MaterialTheme.shapes.large.copy(all = CornerSize(8.dp)),
    ),
) {
  title()
  content()

  Spacer(Modifier.height(10.dp))
}
