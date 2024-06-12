/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.catalog_detail.slots

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.features.catalog_detail.CatalogDetailUiState
import dev.marlonlom.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.cappajv.ui.main.CappajvAppState

/**
 * Catalog detail results slot composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 * @param detailUiState Catalog detail ui state.
 * @param onCatalogItemFavoriteChanged Action for catalog detail item favorite state changed.
 */
@ExperimentalFoundationApi
@Composable
fun CatalogDetailResultsSlot(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  detailUiState: CatalogDetailUiState,
  onCatalogItemFavoriteChanged: (CatalogItem, Boolean) -> Unit,
) = when (detailUiState) {
  is CatalogDetailUiState.Found -> {
    FoundCatalogDetailSlot(
      appState = appState,
      appContentCallbacks = appContentCallbacks,
      detailUiState = detailUiState,
      onCatalogItemFavoriteChanged = onCatalogItemFavoriteChanged
    )
  }

  CatalogDetailUiState.NotFound -> Text("Select from list for view its detailed information.")
}
