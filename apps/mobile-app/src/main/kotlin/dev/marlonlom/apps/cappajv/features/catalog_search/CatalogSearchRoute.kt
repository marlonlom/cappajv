/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.apps.cappajv.features.catalog_search.parts.CatalogSearchHeadline
import dev.marlonlom.apps.cappajv.features.catalog_search.slots.CatalogSearchInputSlot
import dev.marlonlom.apps.cappajv.features.catalog_search.slots.CatalogSearchResultsSlot
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@ExperimentalFoundationApi
@Composable
fun CatalogSearchRoute(
  appState: CappajvAppState,
  viewModel: CatalogSearchViewModel = koinViewModel(),
) {
  val contentHorizontalPadding = when {
    appState.isLandscape.not().and(appState.isMediumWidth) -> 40.dp
    appState.isLandscape.not().and(appState.isExpandedWidth) -> 80.dp
    else -> 20.dp
  }

  val queryText = rememberSaveable { viewModel.queryText }
  val showClearIcon = remember {
    derivedStateOf { viewModel.queryText.value.isNotEmpty() }
  }

  val searchResultState by viewModel.searchResult.collectAsStateWithLifecycle()

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
      onSearchReady = viewModel::onQueryTextChanged,
    )
    CatalogSearchResultsSlot(
      appState = appState,
      searchResultUiState = searchResultState,
      onSearchedItemClicked = {
        Timber.d("[CatalogSearchRoute] clicked item[$it] ")
      },
    )
  }
}
