/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.catalog_detail.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.features.catalog_detail.CatalogDetailUiState
import dev.marlonlom.cappajv.ui.layout.DevicePosture
import dev.marlonlom.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.cappajv.ui.main.CappajvAppState
import dev.marlonlom.cappajv.ui.main.scaffold.ScaffoldContentType

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

  appState.isCompactWidth
    .and(appState.scaffoldContentType == ScaffoldContentType.SinglePane)
    .and(appState.isLandscape.not()).and(
      appState.devicePosture is DevicePosture.Separating.TableTop
    ) -> {
    CompactTableTopCatalogDetailScreen(
      appState = appState,
      appContentCallbacks = appContentCallbacks,
      detailUiState = detailUiState,
      isRouting = isRouting,
      onCatalogItemFavoriteChanged = onCatalogItemFavoriteChanged
    )
  }

  else -> DefaultPortraitCatalogDetailScreen(
    appState = appState,
    appContentCallbacks = appContentCallbacks,
    detailUiState = detailUiState,
    isRouting = isRouting,
    onCatalogItemFavoriteChanged = onCatalogItemFavoriteChanged
  )
}
