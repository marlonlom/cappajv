/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailUiState
import dev.marlonlom.apps.cappajv.features.catalog_detail.parts.CatalogDetailTopBar
import dev.marlonlom.apps.cappajv.features.catalog_detail.slots.CatalogDetailResultsSlot
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState


@ExperimentalFoundationApi
@Composable
fun DefaultPortraitCatalogDetailScreen(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  detailUiState: CatalogDetailUiState,
  isRouting: Boolean,
) {
  val contentHorizontalPadding = when {
    appState.isLandscape.not().and(appState.isMediumWidth) -> 40.dp
    appState.isLandscape.not().and(appState.isExpandedWidth) -> 80.dp
    else -> 20.dp
  }
  Column(
    modifier = Modifier
      .verticalScroll(rememberScrollState())
      .padding(horizontal = contentHorizontalPadding)
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.surface),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CatalogDetailTopBar(appState, isRouting)
    CatalogDetailResultsSlot(appState, appContentCallbacks, detailUiState)
  }
}
