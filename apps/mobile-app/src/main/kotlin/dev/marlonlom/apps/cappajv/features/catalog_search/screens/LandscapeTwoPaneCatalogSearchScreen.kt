/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_search.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
 * Landscape two-pane catalog search screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param isRouting True/False if should navigate through routing.
 * @param queryText Query text for searching.
 * @param showClearIcon True/False if query text should be cleared.
 * @param onSearchReady Action for query text ready for search.
 * @param searchResultUiState Catalog search results ui state.
 * @param onSearchedItemClicked Action for searched item clicked.
 */
@ExperimentalFoundationApi
@Composable
internal fun LandscapeTwoPaneCatalogSearchScreen(
  appState: CappajvAppState,
  isRouting: Boolean,
  queryText: MutableState<String>,
  showClearIcon: State<Boolean>,
  onSearchReady: () -> Unit,
  searchResultUiState: CatalogSearchUiState,
  onSearchedItemClicked: (Long, Boolean) -> Unit,
) {
  Row {
    Column(
      modifier = Modifier
        .fillMaxWidth(0.45f)
        .fillMaxHeight()
        .padding(horizontal = 20.dp),
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
        isRouting = isRouting,
        onSearchedItemClicked = onSearchedItemClicked,
      )
    }
    Column(
      modifier = Modifier
        .background(MaterialTheme.colorScheme.primaryContainer)
        .fillMaxSize()
        .safeContentPadding(),
    ) {
      Text(text = "Detail")
    }
  }
}

