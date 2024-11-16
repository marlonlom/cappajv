/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.catalog.list.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.features.catalog.list.parts.CatalogListHeadline
import dev.marlonlom.cappajv.features.catalog.list.slots.CatalogCategoriesChipGroup
import dev.marlonlom.cappajv.features.catalog.list.slots.CatalogListBanner
import dev.marlonlom.cappajv.features.catalog.list.slots.CatalogListTuplesSlot
import dev.marlonlom.cappajv.ui.layout.DevicePosture
import dev.marlonlom.cappajv.ui.main.CappajvAppState
import dev.marlonlom.cappajv.ui.main.scaffold.ScaffoldContentType

/**
 * Landscape compact catalog list screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param isRouting True/False if should navigate through routing.
 * @param catalogItemsListState Catalog items lazy list state.
 * @param catalogItems Catalog items list.
 * @param categories Categories list.
 * @param selectedCategory Selected category name.
 * @param onSelectedCategoryChanged Action for category selected.
 * @param onCatalogItemSelected Action for catalog item selected.
 * @param modifier Modifier for this composable.
 */
@ExperimentalLayoutApi
@ExperimentalFoundationApi
@Composable
fun LandscapeCompactCatalogListScreen(
  appState: CappajvAppState,
  isRouting: Boolean,
  catalogItemsListState: LazyListState,
  catalogItems: List<CatalogItemTuple>,
  categories: List<String>,
  selectedCategory: String,
  onSelectedCategoryChanged: (String) -> Unit,
  onCatalogItemSelected: (Long, Boolean) -> Unit,
  modifier: Modifier = Modifier,
) {
  val splitRatio = when {
    appState.scaffoldContentType is ScaffoldContentType.TwoPane ->
      (appState.scaffoldContentType as ScaffoldContentType.TwoPane).hingeRatio

    (appState.scaffoldContentType is ScaffoldContentType.SinglePane)
      .and(appState.devicePosture is DevicePosture.Separating.Book) ->
      0.4f

    else -> 0.45f
  }

  Row(
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(20.dp),
  ) {
    Column(modifier = modifier.fillMaxWidth(splitRatio)) {
      CatalogListHeadline(appState)
      CatalogListBanner(appState)
      CatalogCategoriesChipGroup(
        categories = categories,
        selectedCategory = selectedCategory,
        onCategoryChipSelected = { onSelectedCategoryChanged(it) },
      )
    }
    Column(modifier = modifier.safeContentPadding()) {
      CatalogListTuplesSlot(
        appState = appState,
        catalogItemsListState = catalogItemsListState,
        catalogTuples = catalogItems,
        onCatalogItemTupleClicked = { onCatalogItemSelected(it, isRouting) },
      )
    }
  }
}
