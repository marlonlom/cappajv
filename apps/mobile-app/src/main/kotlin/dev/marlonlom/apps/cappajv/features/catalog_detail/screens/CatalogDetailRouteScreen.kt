/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail.screens

import androidx.compose.runtime.Composable
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailUiState
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

@Composable
fun CatalogDetailRouteScreen(
  appState: CappajvAppState,
  isRouting: Boolean,
  detailUiState: CatalogDetailUiState
) = when {
  else -> DefaultPortraitCatalogDetailScreen(
    appState,
    detailUiState,
    isRouting
  )
}