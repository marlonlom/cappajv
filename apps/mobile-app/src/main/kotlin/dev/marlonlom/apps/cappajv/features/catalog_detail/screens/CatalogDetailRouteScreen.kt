/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailUiState
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * Catalog detail route screen content composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 * @param detailUiState Catalog detail ui state.
 * @param isRouting True/False if should navigate through routing.
 * @param onCatalogItemFavoriteChanged Action for catalog detail item favorite state changed.
 */
@ExperimentalFoundationApi
@Composable
fun CatalogDetailRouteScreen(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  detailUiState: CatalogDetailUiState,
  isRouting: Boolean,
  onCatalogItemFavoriteChanged: (CatalogItem, Boolean) -> Unit,
) = when {
  else -> DefaultPortraitCatalogDetailScreen(
    appState = appState,
    appContentCallbacks = appContentCallbacks,
    detailUiState = detailUiState,
    isRouting = isRouting,
    onCatalogItemFavoriteChanged = onCatalogItemFavoriteChanged
  )
}
