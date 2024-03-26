/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_favorites.slots

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import dev.marlonlom.apps.cappajv.features.catalog_favorites.CatalogFavoritesUiState
import dev.marlonlom.apps.cappajv.features.catalog_favorites.CatalogFavoritesUiState.Empty
import dev.marlonlom.apps.cappajv.features.catalog_favorites.CatalogFavoritesUiState.Fetching
import dev.marlonlom.apps.cappajv.features.catalog_favorites.CatalogFavoritesUiState.Success
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * Catalog favorites results slot composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param favoritesListState Catalog favorites ui state.
 * @param isRouting True/False if should navigate through routing.
 * @param onFavoriteItemClicked Action for favorite item clicked.
 * @param onFavoriteItemRemoved Action for favorite item removed.
 */
@ExperimentalFoundationApi
@Composable
fun CatalogFavoritesResultsSlot(
  appState: CappajvAppState,
  favoritesListState: CatalogFavoritesUiState,
  isRouting: Boolean,
  onFavoriteItemClicked: (Long, Boolean) -> Unit,
  onFavoriteItemRemoved: (Long) -> Unit,
) = when (favoritesListState) {
  Empty -> CatalogFavoritesEmptyResultSlot(appState)

  Fetching -> CatalogFavoritesFetchingSlot()

  is Success -> {
    CatalogFavoritesSuccessResultsSlot(
      list = favoritesListState.results,
      isRouting = isRouting,
      onFavoriteItemClicked = onFavoriteItemClicked,
      onFavoriteItemRemoved = onFavoriteItemRemoved,
    )
  }
}
