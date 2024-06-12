/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.catalog_detail.slots

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.features.catalog_detail.CatalogDetailUiState
import dev.marlonlom.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.cappajv.ui.main.CappajvAppState

/**
 * Found catalog detail content slot composable.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 * @param detailUiState Found catalog item information.
 * @param onCatalogItemFavoriteChanged Action for catalog detail item favorite state changed.
 */
@ExperimentalFoundationApi
@Composable
fun FoundCatalogDetailSlot(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  detailUiState: CatalogDetailUiState.Found,
  onCatalogItemFavoriteChanged: (CatalogItem, Boolean) -> Unit,
) {
  val (product, isFavorite, points) = detailUiState.detail
  FoundCatalogDetailHeadingSlot(
    appState = appState,
    appContentCallbacks = appContentCallbacks,
    product = product,
    isFavorite = isFavorite,
    onCatalogItemFavoriteChanged = onCatalogItemFavoriteChanged
  )
  FoundCatalogDetailPointsSlot(appState, points)
}
