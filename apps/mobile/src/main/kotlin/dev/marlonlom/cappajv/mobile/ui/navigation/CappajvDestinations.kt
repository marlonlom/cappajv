/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Coffee
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import dev.marlonlom.cappajv.R

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
enum class CappajvDestinations(@StringRes val title: Int, val icon: ImageVector, @StringRes val hint: Int) {

  /**
   * Home destination.
   */
  HOME(
    title = R.string.destination_home,
    icon = Icons.Rounded.Coffee,
    hint = R.string.destination_home,
  ),

  /**
   * Favorites destination.
   */
  FAVORITES(
    title = R.string.destination_favorites,
    icon = Icons.Rounded.Favorite,
    hint = R.string.destination_favorites,
  ),

  /**
   * Settings destination.
   */
  SETTINGS(
    title = R.string.destination_settings,
    icon = Icons.Rounded.Settings,
    hint = R.string.destination_settings,
  ),
}
