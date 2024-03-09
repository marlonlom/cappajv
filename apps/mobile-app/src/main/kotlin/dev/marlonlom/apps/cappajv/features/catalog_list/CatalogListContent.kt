/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.apps.cappajv.features.catalog_list.screens.CompactTableTopCatalogListScreen
import dev.marlonlom.apps.cappajv.features.catalog_list.screens.DefaultPortraitCatalogListScreen
import dev.marlonlom.apps.cappajv.features.catalog_list.screens.LandscapeCompactCatalogListScreen
import dev.marlonlom.apps.cappajv.ui.layout.DevicePosture
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import dev.marlonlom.apps.cappajv.ui.main.scaffold.ScaffoldContentType

/**
 * Catalog list content composable ui, conditioned by application ui state.
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
 */
@ExperimentalFoundationApi
@ExperimentalLayoutApi
@Composable
fun CatalogListContent(
  appState: CappajvAppState,
  catalogItemsListState: LazyListState,
  catalogItems: List<CatalogItemTuple>,
  categories: List<String>,
  selectedCategory: String,
  onSelectedCategoryChanged: (String) -> Unit,
  onCatalogItemSelected: (Long) -> Unit,
) {
  when {
    appState.isLandscape.and(appState.devicePosture == DevicePosture.Normal).and(appState.isCompactHeight) -> {
      LandscapeCompactCatalogListScreen(
        appState = appState,
        catalogItemsListState = catalogItemsListState,
        catalogItems = catalogItems,
        categories = categories,
        selectedCategory = selectedCategory,
        onSelectedCategoryChanged = onSelectedCategoryChanged,
        onCatalogItemSelected = onCatalogItemSelected,
      )
    }

    appState.isCompactHeight.and(appState.isLandscape)
      .and(appState.scaffoldContentType == ScaffoldContentType.SinglePane)
      .and(appState.devicePosture is DevicePosture.Separating.Book) -> {
      LandscapeCompactCatalogListScreen(
        appState = appState,
        catalogItemsListState = catalogItemsListState,
        catalogItems = catalogItems,
        categories = categories,
        selectedCategory = selectedCategory,
        onSelectedCategoryChanged = onSelectedCategoryChanged,
        onCatalogItemSelected = onCatalogItemSelected,
      )
    }

    appState.isCompactWidth.and(appState.isLandscape.not())
      .and(appState.devicePosture is DevicePosture.Separating.TableTop) -> {
      CompactTableTopCatalogListScreen(
        appState = appState,
        catalogItemsListState = catalogItemsListState,
        catalogItems = catalogItems,
        categories = categories,
        selectedCategory = selectedCategory,
        onSelectedCategoryChanged = onSelectedCategoryChanged,
        onCatalogItemSelected = onCatalogItemSelected,
      )
    }

    else -> {
      DefaultPortraitCatalogListScreen(
        appState = appState,
        catalogItemsListState = catalogItemsListState,
        catalogItems = catalogItems,
        categories = categories,
        selectedCategory = selectedCategory,
        onSelectedCategoryChanged = onSelectedCategoryChanged,
        onCatalogItemSelected = onCatalogItemSelected,
      )
    }
  }
}
