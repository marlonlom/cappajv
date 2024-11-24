/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.browse

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import dev.marlonlom.cappajv.tv.R

/**
 * TV Browse menu items enum class.
 *
 * @author marlonlom
 *
 * @constructor
 * Constructs the enum for TV Browse menu items
 *
 * @param text Item text as string resource.
 * @param icon Item icon as image vector.
 */
enum class TvBrowseMenuItems(
  @StringRes val text: Int,
  val icon: ImageVector,
) {

  /** TV Browse menu items enum: Home */
  HOME(R.string.text_menu_home, Icons.Default.Home),

  /** TV Browse menu items enum: Favorite */
  FAVORITES(R.string.text_menu_favorite, Icons.Default.Favorite),

  /** TV Browse menu items enum: Settings */
  SETTINGS(R.string.text_menu_settings, Icons.Default.Settings),
}
