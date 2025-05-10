/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Coffee
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import dev.marlonlom.cappajv.tv.R

/**
 * Enumeration representing top destinations in the application.
 * Each destination has a title, an icon, and a hint, all represented by resource IDs.
 *
 * @author marlonlom
 *
 * @property title The string resource ID for the title of the destination.
 * @property icon The ImageVector representing the icon of the destination.
 * @property hint The string resource ID for a hint or description of the destination.
 */
enum class TvDestinations(@StringRes val title: Int, val icon: ImageVector, @StringRes val hint: Int) {

  /**
   * Home destination.
   */
  HOME(
    title = R.string.text_menu_home,
    icon = Icons.TwoTone.Coffee,
    hint = R.string.text_menu_home,
  ),

  /**
   * Favorites destination.
   */
  FAVORITES(
    title = R.string.text_menu_favorite,
    icon = Icons.TwoTone.Favorite,
    hint = R.string.text_menu_favorite,
  ),

  /**
   * Settings destination.
   */
  SETTINGS(
    title = R.string.text_menu_settings,
    icon = Icons.TwoTone.Settings,
    hint = R.string.text_menu_settings,
  ),
}
