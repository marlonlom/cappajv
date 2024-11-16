/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.catalog.favorites.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.features.catalog.favorites.CatalogFavoritesUiState
import dev.marlonlom.cappajv.features.catalog.favorites.parts.CatalogFavoritesHeadline
import dev.marlonlom.cappajv.features.catalog.favorites.slots.CatalogFavoritesResultsSlot
import dev.marlonlom.cappajv.ui.main.CappajvAppState

/**
 * Default portrait catalog favorites screen composable ui.
 *
 * @author Marlonlom
 *
 * @param appState Application ui state.
 * @param favoritesListState Catalog favorites ui state.
 * @param isRouting True/False if should navigate through routing.
 * @param onFavoriteItemClicked Action for favorite item clicked.
 * @param onFavoriteItemRemoved Action for favorite item removed.
 */
@ExperimentalFoundationApi
@Composable
fun DefaultPortraitCatalogFavoritesScreen(
  appState: CappajvAppState,
  favoritesListState: CatalogFavoritesUiState,
  isRouting: Boolean,
  onFavoriteItemClicked: (Long, Boolean) -> Unit,
  onFavoriteItemRemoved: (Long) -> Unit,
) {
  val contentHorizontalPadding = when {
    appState.isLandscape.not().and(appState.isMediumWidth) -> 40.dp
    appState.isLandscape.not().and(appState.isExpandedWidth) -> 80.dp
    else -> 20.dp
  }
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(contentHorizontalPadding),
  ) {
    CatalogFavoritesHeadline(appState)
    CatalogFavoritesResultsSlot(
      appState = appState,
      favoritesListState = favoritesListState,
      isRouting = isRouting,
      onFavoriteItemClicked = onFavoriteItemClicked,
      onFavoriteItemRemoved = onFavoriteItemRemoved,
    )
  }
}
