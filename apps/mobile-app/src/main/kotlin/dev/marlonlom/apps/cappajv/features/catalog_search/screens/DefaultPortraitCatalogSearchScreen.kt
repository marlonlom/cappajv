/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_search.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.features.catalog_search.CatalogSearchUiState
import dev.marlonlom.apps.cappajv.features.catalog_search.parts.CatalogSearchHeadline
import dev.marlonlom.apps.cappajv.features.catalog_search.slots.CatalogSearchInputSlot
import dev.marlonlom.apps.cappajv.features.catalog_search.slots.CatalogSearchResultsSlot
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * Default portrait catalog search screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param queryText Query text for searching.
 * @param showClearIcon True/False if query text should be cleared.
 * @param onSearchReady Action for query text ready for search.
 * @param searchResultUiState Catalog search results ui state.
 * @param onSearchedItemClicked Action for searched item clicked.
 */
@ExperimentalFoundationApi
@Composable
internal fun DefaultPortraitCatalogSearchScreen(
  appState: CappajvAppState,
  queryText: MutableState<String>,
  showClearIcon: State<Boolean>,
  onSearchReady: () -> Unit,
  searchResultUiState: CatalogSearchUiState,
  onSearchedItemClicked: (Long) -> Unit
) {
  val contentHorizontalPadding = when {
    appState.isLandscape.not().and(appState.isMediumWidth) -> 40.dp
    appState.isLandscape.not().and(appState.isExpandedWidth) -> 80.dp
    else -> 20.dp
  }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(contentHorizontalPadding)
  ) {
    CatalogSearchHeadline(appState)
    CatalogSearchInputSlot(
      appState = appState,
      queryText = queryText,
      showClearIcon = showClearIcon,
      onSearchReady = onSearchReady,
    )
    CatalogSearchResultsSlot(
      appState = appState,
      searchResultUiState = searchResultUiState,
      onSearchedItemClicked = onSearchedItemClicked,
    )
  }
}
