/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.settings.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.mobile.settings.R

/**
 * A Composable switch for toggling between light and dark themes.
 *
 * @author marlonlom
 *
 * @param isDarkTheme A lambda that returns the current theme state. Returns `true` if the dark theme is enabled.
 * @param onCheckedChange A callback invoked when the user toggles the switch.
 * Receives `true` if dark mode is selected, `false` otherwise.
 */
@Composable
internal fun DarkThemeSwitch(isDarkTheme: () -> Boolean, onCheckedChange: (Boolean) -> Unit) = Row(
  modifier = Modifier.padding(10.dp),
  verticalAlignment = Alignment.CenterVertically,
  horizontalArrangement = Arrangement.spacedBy(10.dp),
) {
  Text(
    text = stringResource(R.string.text_dark_mode),
    style = MaterialTheme.typography.bodyMedium,
    color = MaterialTheme.colorScheme.onSurfaceVariant,
  )
  Spacer(Modifier.weight(1.0f))
  Switch(
    modifier = Modifier.testTag("dark_theme_switch"),
    checked = isDarkTheme(),
    onCheckedChange = {
      onCheckedChange(!isDarkTheme())
    },
  )
}
