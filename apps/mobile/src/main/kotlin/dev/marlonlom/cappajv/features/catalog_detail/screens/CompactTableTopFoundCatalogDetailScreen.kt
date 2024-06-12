/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.catalog_detail.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.features.catalog_detail.CatalogDetailUiState
import dev.marlonlom.cappajv.features.catalog_detail.parts.CatalogDetailProductDescriptionText
import dev.marlonlom.cappajv.features.catalog_detail.parts.CatalogDetailTopBar
import dev.marlonlom.cappajv.features.catalog_detail.slots.CompactTableTopFoundCatalogDetailHeadingSlot
import dev.marlonlom.cappajv.features.catalog_detail.slots.FoundCatalogDetailPointsSlot
import dev.marlonlom.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.cappajv.ui.main.CappajvAppState

/**
 * Compact tabletop found catalog detail screen composable.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 * @param foundDetail Found details ui state.
 * @param isRouting True/False if should navigate through routing.
 * @param onCatalogItemFavoriteChanged Action for catalog detail item favorite state changed.
 * @param contentHorizontalPadding Content horizontal padding.
 * @param hingeRatio Hinge ratio for column height separation.
 * @param modifier Modifier for this composable.
 */
@ExperimentalFoundationApi
@Composable
fun CompactTableTopFoundCatalogDetailScreen(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  foundDetail: CatalogDetailUiState.Found,
  isRouting: Boolean,
  onCatalogItemFavoriteChanged: (CatalogItem, Boolean) -> Unit,
  contentHorizontalPadding: Dp,
  hingeRatio: Float,
  modifier: Modifier = Modifier
) {
  val (product, isFavorite, points) = foundDetail.detail

  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = contentHorizontalPadding)
  ) {
    CatalogDetailTopBar(appState, isRouting)

    CompactTableTopFoundCatalogDetailHeadingSlot(
      appState = appState,
      appContentCallbacks = appContentCallbacks,
      product = product,
      isFavorite = isFavorite,
      onCatalogItemFavoriteChanged = onCatalogItemFavoriteChanged,
      hingeRatio = hingeRatio,
      contentHorizontalPadding = contentHorizontalPadding
    )

    Column(
      modifier = modifier
        .verticalScroll(rememberScrollState())
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.surface)
        .padding(horizontal = contentHorizontalPadding)
    ) {
      CatalogDetailProductDescriptionText(product)
      FoundCatalogDetailPointsSlot(appState, points)
    }
  }
}
