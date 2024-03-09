/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.marlonlom.apps.cappajv.features.catalog_favorites.FavoriteProductsRoute
import dev.marlonlom.apps.cappajv.features.catalog_list.CatalogListRoute
import dev.marlonlom.apps.cappajv.features.catalog_search.CatalogSearchRoute
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * Main navigation host composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 */
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
fun MainNavHost(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
) {
  NavHost(
    navController = appState.navController,
    startDestination = CatalogDestination.CatalogList.route,
  ) {
    catalogListDestination(appState)
    catalogFavoritesDestination(appState)
    catalogSearchDestination(appState)
  }
}

/**
 * Catalog list destination composable extension for navigation graph builder.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 */
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
internal fun NavGraphBuilder.catalogListDestination(
  appState: CappajvAppState,
) {
  composable(CatalogDestination.CatalogList.route) {
    CatalogListRoute(appState)
  }
}

/**
 * Favorites products destination composable extension for navigation graph builder.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 */
internal fun NavGraphBuilder.catalogFavoritesDestination(
  appState: CappajvAppState,
) {
  composable(CatalogDestination.FavoriteProducts.route) {
    FavoriteProductsRoute(appState)
  }
}

/**
 * Search catalog destination composable extension for navigation graph builder.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 */
internal fun NavGraphBuilder.catalogSearchDestination(
  appState: CappajvAppState,
) {
  composable(CatalogDestination.SearchProducts.route) {
    CatalogSearchRoute(appState)
  }
}
