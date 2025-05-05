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
 * A Composable switch for enabling or disabling dynamic color theming.
 *
 * This switch allows the user to toggle the use of dynamic colors, which may adapt based on
 * wallpaper or system settings, depending on the Android version and theme.
 *
 * @author marlonlom
 *
 * @param isDarkTheme A lambda that returns the current theme state. Returns `true` if the dark theme is enabled.
 * This may affect how dynamic colors are applied.
 * @param onCheckedChange A callback triggered when the user toggles the switch.
 * Receives `true` to enable dynamic colors, or `false` to disable them.
 */
@Composable
internal fun DynamicColorsSwitch(isDarkTheme: () -> Boolean, onCheckedChange: (Boolean) -> Unit) = Row(
  modifier = Modifier.padding(10.dp),
  verticalAlignment = Alignment.CenterVertically,
  horizontalArrangement = Arrangement.spacedBy(10.dp),
) {
  Text(
    text = stringResource(R.string.text_dynamic_colors),
    style = MaterialTheme.typography.bodyMedium,
    color = MaterialTheme.colorScheme.onSurfaceVariant,
  )
  Spacer(Modifier.weight(1.0f))
  Switch(
    modifier = Modifier.testTag("dynamic_colors_switch"),
    checked = isDarkTheme(),
    onCheckedChange = {
      onCheckedChange(!isDarkTheme())
    },
  )
}
