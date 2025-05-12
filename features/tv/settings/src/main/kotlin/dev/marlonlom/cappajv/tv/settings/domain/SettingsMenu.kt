/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.settings.domain

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.Help
import androidx.compose.material.icons.twotone.Info
import androidx.compose.ui.graphics.vector.ImageVector
import dev.marlonlom.cappajv.tv.settings.R

/**
 * Enum class representing different setting screens with associated icons and title resources.
 *
 * @author marlonlom
 *
 * @param icon The icon to display for the setting screen.
 * @param title The string resource ID for the screen's title.
 */
enum class SettingsMenu(val icon: ImageVector, @StringRes val title: Int) {
  /** Screen showing information about the app. */
  ABOUT(
    Icons.TwoTone.Info,
    R.string.text_about,
  ),

  /** Screen providing help and support options. */
  HELP_SUPPORT(
    Icons.AutoMirrored.TwoTone.Help,
    R.string.text_help_support,
  ),
}
