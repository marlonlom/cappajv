/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.marlonlom.cappajv.tv.features.catalog.favorites.CatalogFavoritesScreen
import dev.marlonlom.cappajv.tv.features.catalog.home.CatalogHomeScreen
import dev.marlonlom.cappajv.tv.features.settings.SettingsScreen
import dev.marlonlom.cappajv.tv.ui.CappajvAppState

/**
 * Tv navigation host composable ui.
 *
 * @author marlonlom
 *
 * @param appState The application ui state.
 */
@Composable
fun CappajvTvNavigationHost(
  appState: CappajvAppState,
) = NavHost(
  navController = appState.navHostController,
  startDestination = CappajvTvScreen.Home
) {
  composable<CappajvTvScreen.Home> {
    CatalogHomeScreen()
  }

  composable<CappajvTvScreen.Favorites> {
    CatalogFavoritesScreen()
  }

  composable<CappajvTvScreen.Settings> {
    SettingsScreen()
  }

}
