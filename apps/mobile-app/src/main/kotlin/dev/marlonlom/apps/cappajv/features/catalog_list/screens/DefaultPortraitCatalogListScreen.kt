/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.apps.cappajv.features.catalog_list.parts.CatalogListHeadline
import dev.marlonlom.apps.cappajv.features.catalog_list.slots.CatalogCategoriesChipGroup
import dev.marlonlom.apps.cappajv.features.catalog_list.slots.CatalogListBanner
import dev.marlonlom.apps.cappajv.features.catalog_list.slots.CatalogListTuplesSlot
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * Default portrait catalog list screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param catalogItemsListState Catalog items lazy list state.
 * @param catalogItems Catalog items list.
 * @param categories Categories list.
 * @param selectedCategory Selected category name.
 * @param onSelectedCategoryChanged Action for category selected.
 * @param onCatalogItemSelected Action for catalog item selected.
 * @param modifier Modifier for this composable.
 */
@ExperimentalPagerApi
@ExperimentalLayoutApi
@ExperimentalFoundationApi
@Composable
fun DefaultPortraitCatalogListScreen(
  appState: CappajvAppState,
  catalogItemsListState: LazyListState,
  catalogItems: List<CatalogItemTuple>,
  categories: List<String>,
  selectedCategory: String,
  onSelectedCategoryChanged: (String) -> Unit,
  onCatalogItemSelected: (Long) -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .safeContentPadding(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    CatalogListHeadline(appState)
    CatalogListBanner()
    CatalogCategoriesChipGroup(
      categories = categories,
      selectedCategory = selectedCategory,
      onCategoryChipSelected = { onSelectedCategoryChanged(it) },
      isScrollable = true,
    )
    CatalogListTuplesSlot(
      appState = appState,
      catalogItemsListState = catalogItemsListState,
      catalogTuples = catalogItems,
      onCatalogItemTupleClicked = { onCatalogItemSelected(it) },
    )
  }
}
