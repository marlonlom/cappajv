/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.catalog.favorites.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import dev.marlonlom.cappajv.features.catalog.favorites.CatalogFavoritesUiState
import dev.marlonlom.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.cappajv.ui.main.CappajvAppState

/**
 * Default portrait catalog favorites screen composable ui.
 *
 * @author Marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 * @param selectedCatalogId Selected catalog item id.
 * @param favoritesListState Catalog favorites ui state.
 * @param onFavoriteItemClicked Action for favorite item clicked.
 * @param onFavoriteItemRemoved Action for favorite item removed.
 */
@ExperimentalFoundationApi
@Composable
fun CatalogFavoritesRouteScreen(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  selectedCatalogId: Long,
  favoritesListState: CatalogFavoritesUiState,
  onFavoriteItemClicked: (Long, Boolean) -> Unit,
  onFavoriteItemRemoved: (Long) -> Unit,
) = when {
  else -> DefaultPortraitCatalogFavoritesScreen(
    appState = appState,
    favoritesListState = favoritesListState,
    isRouting = true,
    onFavoriteItemClicked = onFavoriteItemClicked,
    onFavoriteItemRemoved = onFavoriteItemRemoved,
  )
}
