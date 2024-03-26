/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.apps.cappajv.features.catalog_search.screens.CatalogSearchRouteScreen
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

/**
 * Catalog search route composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param viewModel Catalog search viewmodel.
 */
@ExperimentalFoundationApi
@Composable
fun CatalogSearchRoute(
  appState: CappajvAppState,
  viewModel: CatalogSearchViewModel = koinViewModel(),
) {
  val queryText = rememberSaveable { viewModel.queryText }
  val showClearIcon = remember {
    derivedStateOf { viewModel.queryText.value.isNotEmpty() }
  }
  val searchResultState by viewModel.searchResult.collectAsStateWithLifecycle()
  CatalogSearchRouteScreen(
    appState = appState,
    queryText = queryText,
    showClearIcon = showClearIcon,
    onSearchReady = viewModel::onQueryTextChanged,
    searchResultUiState = searchResultState,
    onSearchedItemClicked = { catalogId, isRouting ->
      Timber.d("[CatalogSearchRoute] clicked item[$catalogId], isRouting=$isRouting ")
    },
  )
}
