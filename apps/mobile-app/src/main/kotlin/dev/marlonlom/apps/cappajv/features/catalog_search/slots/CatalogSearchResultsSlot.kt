/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_search.slots

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import dev.marlonlom.apps.cappajv.features.catalog_search.CatalogSearchUiState
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * Catalog search results slot composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param searchResultUiState Catalog search result ui state.
 */
@ExperimentalFoundationApi
@Composable
fun CatalogSearchResultsSlot(
  appState: CappajvAppState,
  searchResultUiState: CatalogSearchUiState,
  onSearchedItemClicked: (Long) -> Unit,
) {
  when (searchResultUiState) {
    CatalogSearchUiState.None -> CatalogSearchWelcomeSlot(appState)

    CatalogSearchUiState.Searching -> CatalogSearchingSlot()

    CatalogSearchUiState.Empty -> CatalogEmptyResultsSlot(appState=appState)

    is CatalogSearchUiState.Success -> CatalogSuccessResultsSlot(
      searchResults = searchResultUiState.results,
      onSearchedItemClicked = onSearchedItemClicked
    )
  }
}
