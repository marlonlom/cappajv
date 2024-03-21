/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_search.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import dev.marlonlom.apps.cappajv.features.catalog_search.CatalogSearchUiState
import dev.marlonlom.apps.cappajv.ui.layout.DevicePosture
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import dev.marlonlom.apps.cappajv.ui.navigation.NavigationType

/**
 * Catalog search route screen content composable ui.
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
fun CatalogSearchRouteScreen(
  appState: CappajvAppState,
  queryText: MutableState<String>,
  showClearIcon: State<Boolean>,
  onSearchReady: () -> Unit,
  searchResultUiState: CatalogSearchUiState,
  onSearchedItemClicked: (Long) -> Unit,
) {
  when {
    appState.isLandscape
      .and(appState.devicePosture is DevicePosture.Separating.Book)
      .and(appState.navigationType == NavigationType.NAVIGATION_RAIL) -> {
      LandscapeTwoPaneCatalogSearchScreen(
        appState = appState,
        queryText = queryText,
        showClearIcon = showClearIcon,
        onSearchReady = onSearchReady,
        searchResultUiState = searchResultUiState,
        onSearchedItemClicked = onSearchedItemClicked
      )
    }

    appState.isLandscape
      .and(appState.devicePosture == DevicePosture.Normal)
      .and(appState.navigationType == NavigationType.NAVIGATION_RAIL) -> {
      LandscapeTwoPaneCatalogSearchScreen(
        appState = appState,
        queryText = queryText,
        showClearIcon = showClearIcon,
        onSearchReady = onSearchReady,
        searchResultUiState = searchResultUiState,
        onSearchedItemClicked = onSearchedItemClicked
      )
    }

    else -> {
      DefaultPortraitCatalogSearchScreen(
        appState = appState,
        queryText = queryText,
        showClearIcon = showClearIcon,
        onSearchReady = onSearchReady,
        searchResultUiState = searchResultUiState,
        onSearchedItemClicked = onSearchedItemClicked
      )
    }
  }
}
