/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailRoute
import dev.marlonlom.apps.cappajv.features.catalog_list.parts.CatalogListHeadline
import dev.marlonlom.apps.cappajv.features.catalog_list.slots.CatalogCategoriesChipGroup
import dev.marlonlom.apps.cappajv.features.catalog_list.slots.CatalogListBanner
import dev.marlonlom.apps.cappajv.features.catalog_list.slots.CatalogListTuplesSlot
import dev.marlonlom.apps.cappajv.ui.layout.DevicePosture
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import kotlinx.coroutines.ExperimentalCoroutinesApi


/**
 * TableTop catalog list screen composable ui.
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
@ExperimentalCoroutinesApi
@ExperimentalLayoutApi
@ExperimentalFoundationApi
@Composable
fun TableTopCatalogListScreen(
  appState: CappajvAppState,
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
  val hingeRatio = (appState.devicePosture as DevicePosture.Separating.TableTop).hingeRatio

  Column(
    modifier = modifier.fillMaxWidth(),
  ) {
    Row(
      modifier = modifier.fillMaxHeight(hingeRatio),
      horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
      Column(modifier = modifier.fillMaxWidth(0.45f)) {
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
    Row(
      modifier = modifier
        .fillMaxSize()
        .padding(top = 30.dp)
        .safeContentPadding()
        .background(
          MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
        ),
    ) {
      CatalogDetailRoute(
          appState = appState,
          isRouting = isRouting,
          catalogId = selectedCatalogId,
      )
    }
  }
}
