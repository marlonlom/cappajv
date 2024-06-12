/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.catalog_search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.cappajv.features.catalog_search.screens.CatalogSearchRouteScreen
import dev.marlonlom.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.cappajv.ui.main.CappajvAppState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.koinViewModel

/**
 * Catalog search route composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 * @param viewModel Catalog search viewmodel.
 */
@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun CatalogSearchRoute(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  viewModel: CatalogSearchViewModel = koinViewModel(),
) {
  val queryText = rememberSaveable { viewModel.queryText }
  val showClearIcon = remember {
    derivedStateOf { viewModel.queryText.value.isNotEmpty() }
  }
  val searchResultState by viewModel.searchResult.collectAsStateWithLifecycle(
    lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current
  )
  val selectedCatalogId by viewModel.selectedCatalogId.collectAsStateWithLifecycle(
    lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current
  )
  CatalogSearchRouteScreen(
    appState = appState,
    appContentCallbacks = appContentCallbacks,
    queryText = queryText,
    showClearIcon = showClearIcon,
    onSearchReady = viewModel::onQueryTextChanged,
    searchResultUiState = searchResultState,
    selectedCatalogId = selectedCatalogId,
    onSearchedItemClicked = { catalogId, isRouting ->
      viewModel.selectCatalogItem(catalogId)
      if (isRouting) {
        appState.goToDetail(catalogId)
      }
    },
  )
}
