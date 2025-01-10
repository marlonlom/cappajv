/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Coffee
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import dev.marlonlom.cappajv.R

/**
 * Application navigation destination sealed class.
 *
 * @author marlonlom
 *
 * @property route Destination route.
 * @property title Destination title as string resource.
 * @property icon Icon for destination
 */
sealed class CatalogDestination(val route: String, @StringRes val title: Int, val icon: ImageVector) {

  /**
   * Catalog list destination data object.
   *
   * @author marlonlom
   */
  data object CatalogList : CatalogDestination(
    route = "catalog/home",
    title = R.string.destination_home,
    icon = Icons.Rounded.Coffee,
  )

  /**
   * Favorite catalog products list destination data object.
   *
   * @author marlonlom
   */
  data object FavoriteProducts : CatalogDestination(
    route = "catalog/favorites",
    title = R.string.destination_favorites,
    icon = Icons.Rounded.Favorite,
  )

  /**
   * Search catalog products list destination data object.
   *
   * @author marlonlom
   */
  data object SearchProducts : CatalogDestination(
    route = "catalog/search",
    title = R.string.destination_search,
    icon = Icons.Rounded.Search,
  )

  /**
   * Settings destination data object.
   *
   * @author marlonlom
   */
  data object Settings : CatalogDestination(
    route = "settings",
    title = R.string.destination_settings,
    icon = Icons.Rounded.Settings,
  )

  /**
   * Catalog Detail destination data object.
   *
   * @author marlonlom
   */
  data object Detail : CatalogDestination(
    route = "catalog/detail/{itemId}",
    title = R.string.destination_detail,
    icon = Icons.Rounded.Info,
  ) {

    /**
     * Creates detail route for selected product item id.
     *
     * @param itemId Selected product item id.
     */
    fun createRoute(itemId: Long) = "catalog/detail/$itemId"

    const val ITEM_ID_ARG = "itemId"

    /** Navigation arguments for detail destination route. */
    val navArguments = listOf(
      navArgument(ITEM_ID_ARG) {
        type = NavType.LongType
        defaultValue = 0L
      },
    )
  }

  companion object {

    /**
     * Catalog top destination list.
     *
     * @author marlonlom
     */
    val topCatalogDestinations: List<CatalogDestination>
      get() = listOf(CatalogList, FavoriteProducts, SearchProducts, Settings)
  }
}
