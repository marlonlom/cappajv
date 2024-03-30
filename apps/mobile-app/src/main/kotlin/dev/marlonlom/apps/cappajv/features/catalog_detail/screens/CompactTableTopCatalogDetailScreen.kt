/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailUiState
import dev.marlonlom.apps.cappajv.ui.layout.DevicePosture
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * TableTop catalog detail screen composable ui.
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
fun CompactTableTopCatalogDetailScreen(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  detailUiState: CatalogDetailUiState,
  isRouting: Boolean,
  onCatalogItemFavoriteChanged: (CatalogItem, Boolean) -> Unit,
) {
  val contentHorizontalPadding = when {
    appState.isLandscape.not().and(appState.isMediumWidth) -> 40.dp
    appState.isLandscape.not().and(appState.isExpandedWidth) -> 80.dp
    else -> 20.dp
  }

  val hingeRatio = (appState.devicePosture as DevicePosture.Separating.TableTop).hingeRatio

  when (detailUiState) {
    is CatalogDetailUiState.Found -> {
      CompactTableTopFoundCatalogDetailScreen(
        appState = appState,
        appContentCallbacks = appContentCallbacks,
        foundDetail = detailUiState,
        isRouting = isRouting,
        onCatalogItemFavoriteChanged = onCatalogItemFavoriteChanged,
        contentHorizontalPadding = contentHorizontalPadding,
        hingeRatio = hingeRatio
      )
    }

    CatalogDetailUiState.NotFound -> Text("Select from list for view its detailed information.")
  }
}
