/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.features.catalog_search.parts.CatalogSearchHeadline
import dev.marlonlom.apps.cappajv.features.catalog_search.slots.CatalogSearchSlot
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun SearchProductsRoute(
  appState: CappajvAppState,
  viewModel: CatalogSearchViewModel = koinViewModel(),
) {
  val contentHorizontalPadding = when {
    appState.isLandscape.not().and(appState.isMediumWidth) -> 40.dp
    appState.isLandscape.not().and(appState.isExpandedWidth) -> 80.dp
    else -> 20.dp
  }

  val queryText = rememberSaveable { mutableStateOf("") }
  val showClearIcon = remember { derivedStateOf { queryText.value.isNotEmpty() } }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(contentHorizontalPadding)
  ) {
    CatalogSearchHeadline(appState)
    CatalogSearchSlot(
      appState = appState,
      queryText = queryText,
      showClearIcon = showClearIcon,
      onSearchReady = {
        Timber.d("[SearchProductsRoute] onSearchReady(${queryText.value})")
      },
    )
  }
}
