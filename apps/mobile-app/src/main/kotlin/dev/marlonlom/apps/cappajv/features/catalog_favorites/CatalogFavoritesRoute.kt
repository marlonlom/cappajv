/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.apps.cappajv.features.catalog_favorites.screens.CatalogFavoritesRouteScreen
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

/**
 * Catalog favorites route composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param viewModel Catalog favorites viewmodel.
 */
@ExperimentalFoundationApi
@Composable
fun CatalogFavoritesRoute(
  appState: CappajvAppState,
  viewModel: CatalogFavoritesViewModel = koinViewModel(),
) {
  val favoritesListState by viewModel.favoritesListState.collectAsStateWithLifecycle()
  CatalogFavoritesRouteScreen(
    appState = appState,
    favoritesListState = favoritesListState,
    onFavoriteItemClicked = { catalogId, isRouting ->
      Timber.d("[CatalogFavoritesRoute] clicked item[$catalogId], isRouting=$isRouting ")
    },
    onFavoriteItemRemoved = viewModel::deleteFavorite,
  )
}
