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
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailRoute
import dev.marlonlom.apps.cappajv.features.catalog_favorites.CatalogFavoritesRoute
import dev.marlonlom.apps.cappajv.features.catalog_list.CatalogListRoute
import dev.marlonlom.apps.cappajv.features.catalog_search.CatalogSearchRoute
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import dev.marlonlom.apps.cappajv.ui.navigation.CatalogDestination.CatalogList
import dev.marlonlom.apps.cappajv.ui.navigation.CatalogDestination.Detail
import dev.marlonlom.apps.cappajv.ui.navigation.CatalogDestination.FavoriteProducts
import dev.marlonlom.apps.cappajv.ui.navigation.CatalogDestination.SearchProducts
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Main navigation host composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 */
@ExperimentalCoroutinesApi
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
    startDestination = CatalogList.route,
  ) {
    catalogListDestination(appState)
    catalogFavoritesDestination(appState)
    catalogSearchDestination(appState)
    catalogDetailDestination(appState)
  }
}

/**
 * Catalog list destination composable extension for navigation graph builder.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 */
@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
internal fun NavGraphBuilder.catalogListDestination(
  appState: CappajvAppState,
) {
  composable(CatalogList.route) {
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
@ExperimentalFoundationApi
internal fun NavGraphBuilder.catalogFavoritesDestination(
  appState: CappajvAppState,
) {
  composable(FavoriteProducts.route) {
    CatalogFavoritesRoute(appState)
  }
}

/**
 * Search catalog destination composable extension for navigation graph builder.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 */
@ExperimentalFoundationApi
internal fun NavGraphBuilder.catalogSearchDestination(
  appState: CappajvAppState,
) {
  composable(SearchProducts.route) {
    CatalogSearchRoute(appState)
  }
}

/**
 * Catalog details destination composable extension for navigation graph builder.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 */
@ExperimentalCoroutinesApi
private fun NavGraphBuilder.catalogDetailDestination(
  appState: CappajvAppState
) {
  composable(
    route = Detail.route,
    arguments = Detail.navArguments
  ) { backStackEntry ->
    val catalogDetailId = requireNotNull(backStackEntry.arguments?.getLong(Detail.ITEM_ID_ARG))
    CatalogDetailRoute(
      appState = appState,
      isRouting = true,
      catalogId = catalogDetailId
    )
  }
}
