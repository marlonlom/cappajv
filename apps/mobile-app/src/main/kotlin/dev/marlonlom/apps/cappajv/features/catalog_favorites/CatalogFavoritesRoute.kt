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
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import org.koin.androidx.compose.koinViewModel

/**
 * Catalog favorites route composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 * @param viewModel Catalog favorites viewmodel.
 */
@ExperimentalFoundationApi
@Composable
fun CatalogFavoritesRoute(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  viewModel: CatalogFavoritesViewModel = koinViewModel(),
) {
  val favoritesListState by viewModel.favoritesListState.collectAsStateWithLifecycle(
    lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current
  )
  val selectedCatalogId by viewModel.selectedCatalogId.collectAsStateWithLifecycle(
    lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current
  )
  CatalogFavoritesRouteScreen(
    appState = appState,
    appContentCallbacks = appContentCallbacks,
    selectedCatalogId = selectedCatalogId,
    favoritesListState = favoritesListState,
    onFavoriteItemClicked = { catalogId, isRouting ->
      viewModel.selectCatalogItem(catalogId)
      if (isRouting) {
        appState.goToDetail(catalogId)
      }
    },
    onFavoriteItemRemoved = viewModel::deleteFavorite,
  )
}
