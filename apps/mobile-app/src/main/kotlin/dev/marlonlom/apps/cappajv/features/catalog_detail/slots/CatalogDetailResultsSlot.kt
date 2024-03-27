/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail.slots

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailUiState
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

@Composable
fun CatalogDetailResultsSlot(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  detailUiState: CatalogDetailUiState,
) = when (detailUiState) {
  is CatalogDetailUiState.Found -> {
    FoundCatalogDetailSlot(appState, appContentCallbacks, detailUiState)
  }

  CatalogDetailUiState.NotFound -> Text("Select from list for view its detailed information.")
}
