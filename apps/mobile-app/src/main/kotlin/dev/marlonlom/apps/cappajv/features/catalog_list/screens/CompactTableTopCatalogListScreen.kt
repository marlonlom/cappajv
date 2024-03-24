/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.apps.cappajv.features.catalog_list.parts.CatalogListHeadline
import dev.marlonlom.apps.cappajv.features.catalog_list.slots.CatalogCategoriesChipGroup
import dev.marlonlom.apps.cappajv.features.catalog_list.slots.CatalogListBanner
import dev.marlonlom.apps.cappajv.features.catalog_list.slots.CatalogListTuplesSlot
import dev.marlonlom.apps.cappajv.ui.layout.DevicePosture
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * Compact tableTop catalog list screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param catalogItemsListState
 * @param catalogItems
 * @param categories
 * @param selectedCategory
 * @param onSelectedCategoryChanged
 * @param onCatalogItemSelected
 */
@ExperimentalLayoutApi
@ExperimentalFoundationApi
@Composable
fun CompactTableTopCatalogListScreen(
  appState: CappajvAppState,
  catalogItemsListState: LazyListState,
  catalogItems: List<CatalogItemTuple>,
  categories: List<String>,
  selectedCategory: String,
  onSelectedCategoryChanged: (String) -> Unit,
  onCatalogItemSelected: (Long) -> Unit,
  modifier: Modifier = Modifier,
) {

  val hingeRatio = (appState.devicePosture as DevicePosture.Separating.TableTop).hingeRatio

  Column(
    modifier = modifier
      .fillMaxWidth()
      .safeContentPadding(),
    verticalArrangement = Arrangement.SpaceAround
  ) {

    Column(
      modifier = modifier
        .fillMaxHeight(hingeRatio),
    ) {
      CatalogListHeadline(appState)
      CatalogListBanner(appState)
      CatalogCategoriesChipGroup(
        categories = categories,
        selectedCategory = selectedCategory,
        onCategoryChipSelected = { onSelectedCategoryChanged(it) },
      )
    }

    Spacer(modifier = Modifier.height(60.dp))

    CatalogListTuplesSlot(
      appState = appState,
      catalogItemsListState = catalogItemsListState,
      catalogTuples = catalogItems,
      onCatalogItemTupleClicked = { onCatalogItemSelected(it) },
    )

  }
}
