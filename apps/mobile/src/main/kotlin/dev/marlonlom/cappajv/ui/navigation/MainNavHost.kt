/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.marlonlom.cappajv.features.catalog.detail.CatalogDetailRoute
import dev.marlonlom.cappajv.features.catalog.favorites.CatalogFavoritesRoute
import dev.marlonlom.cappajv.features.catalog.list.CatalogListRoute
import dev.marlonlom.cappajv.features.catalog.search.CatalogSearchRoute
import dev.marlonlom.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.cappajv.ui.main.CappajvAppState
import dev.marlonlom.cappajv.ui.navigation.CatalogDestination.CatalogList
import dev.marlonlom.cappajv.ui.navigation.CatalogDestination.Detail
import dev.marlonlom.cappajv.ui.navigation.CatalogDestination.FavoriteProducts
import dev.marlonlom.cappajv.ui.navigation.CatalogDestination.SearchProducts
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
fun MainNavHost(appState: CappajvAppState, appContentCallbacks: AppContentCallbacks) {
  NavHost(
    navController = appState.navController,
    startDestination = CatalogList.route,
  ) {
    catalogListDestination(appState, appContentCallbacks)
    catalogFavoritesDestination(appState, appContentCallbacks)
    catalogSearchDestination(appState, appContentCallbacks)
    catalogDetailDestination(appState, appContentCallbacks)
  }
}

/**
 * Catalog list destination composable extension for navigation graph builder.
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
internal fun NavGraphBuilder.catalogListDestination(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
) {
  composable(CatalogList.route) {
    CatalogListRoute(appState, appContentCallbacks)
  }
}

/**
 * Favorites products destination composable extension for navigation graph builder.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 */
@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
internal fun NavGraphBuilder.catalogFavoritesDestination(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
) {
  composable(FavoriteProducts.route) {
    CatalogFavoritesRoute(appState, appContentCallbacks)
  }
}

/**
 * Search catalog destination composable extension for navigation graph builder.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 */
@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
internal fun NavGraphBuilder.catalogSearchDestination(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
) {
  composable(SearchProducts.route) {
    CatalogSearchRoute(appState, appContentCallbacks)
  }
}

/**
 * Catalog details destination composable extension for navigation graph builder.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 */
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
private fun NavGraphBuilder.catalogDetailDestination(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
) {
  composable(
    route = Detail.route,
    arguments = Detail.navArguments,
  ) { backStackEntry ->
    val catalogDetailId = requireNotNull(backStackEntry.arguments?.getLong(Detail.ITEM_ID_ARG))
    CatalogDetailRoute(
      appState = appState,
      appContentCallbacks = appContentCallbacks,
      isRouting = true,
      catalogId = catalogDetailId,
    )
  }
}
