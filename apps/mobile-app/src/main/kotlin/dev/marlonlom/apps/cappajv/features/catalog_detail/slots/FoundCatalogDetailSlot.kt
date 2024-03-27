/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail.slots

import androidx.compose.runtime.Composable
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailUiState
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * Found catalog detail content slot composable.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param detailUiState Catalog detail ui state.
 */
@Composable
fun FoundCatalogDetailSlot(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  detailUiState: CatalogDetailUiState.Found,
) {
  val (product, points) = detailUiState.detail
  FoundCatalogDetailHeadingSlot(appState, appContentCallbacks, product)
}
