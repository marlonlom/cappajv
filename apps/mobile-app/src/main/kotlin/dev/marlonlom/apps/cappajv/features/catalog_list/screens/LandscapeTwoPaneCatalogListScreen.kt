/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailRoute
import dev.marlonlom.apps.cappajv.features.catalog_list.parts.CatalogListHeadline
import dev.marlonlom.apps.cappajv.features.catalog_list.slots.CatalogCategoriesChipGroup
import dev.marlonlom.apps.cappajv.features.catalog_list.slots.CatalogListBanner
import dev.marlonlom.apps.cappajv.features.catalog_list.slots.CatalogListTuplesSlot
import dev.marlonlom.apps.cappajv.ui.layout.DevicePosture
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Landscape Two pane catalog list screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks. Application content callbacks.
 * @param isRouting True/False if should navigate through routing.
 * @param catalogItemsListState Catalog items lazy list state.
 * @param catalogItems Catalog items list.
 * @param categories Categories list.
 * @param selectedCategory Selected category name.
 * @param selectedCatalogId Selected catalog item id.
 * @param onSelectedCategoryChanged Action for category selected.
 * @param onCatalogItemSelected Action for catalog item selected.
 * @param modifier Modifier for this composable.
 */
@ExperimentalCoroutinesApi
@ExperimentalLayoutApi
@ExperimentalFoundationApi
@Composable
fun LandscapeTwoPaneCatalogListScreen(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  isRouting: Boolean,
  catalogItemsListState: LazyListState,
  catalogItems: List<CatalogItemTuple>,
  categories: List<String>,
  selectedCategory: String,
  selectedCatalogId: Long,
  onSelectedCategoryChanged: (String) -> Unit,
  onCatalogItemSelected: (Long, Boolean) -> Unit,
  modifier: Modifier = Modifier,
) {
  val listColumnWidthFraction = when {
    appState.devicePosture is DevicePosture.Separating.Book -> 0.4f
    else -> 0.33f
  }
  Row(
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(20.dp)
  ) {
    Column(
      modifier = modifier.fillMaxWidth(listColumnWidthFraction)
    ) {
      CatalogListHeadline(appState)
      CatalogListBanner(appState)
      CatalogCategoriesChipGroup(
        categories = categories,
        selectedCategory = selectedCategory,
        onCategoryChipSelected = { onSelectedCategoryChanged(it) },
      )
      CatalogListTuplesSlot(
        appState = appState,
        catalogItemsListState = catalogItemsListState,
        catalogTuples = catalogItems,
        onCatalogItemTupleClicked = { onCatalogItemSelected(it, isRouting) },
      )
    }
    Column(
      modifier = modifier
        .fillMaxSize()
        .safeContentPadding(),
    ) {
      CatalogDetailRoute(
        appState = appState,
        appContentCallbacks = appContentCallbacks,
        isRouting = isRouting,
        catalogId = selectedCatalogId,
      )
    }
  }
}
