/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list.slots

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.R
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.apps.cappajv.features.catalog_list.parts.CatalogTupleRow
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * Catalog list tuples slot composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param catalogItemsListState Catalog items lazy list state.
 * @param catalogTuples Catalog tuples list.
 * @param onCatalogItemTupleClicked Action for catalog tuple selected.
 * @param modifier Modifier for this composable.
 */
@ExperimentalFoundationApi
@Composable
fun CatalogListTuplesSlot(
  appState: CappajvAppState,
  catalogItemsListState: LazyListState,
  catalogTuples: List<CatalogItemTuple>,
  onCatalogItemTupleClicked: (Long) -> Unit,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    state = catalogItemsListState,
    modifier = modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    stickyHeader {
      Text(
        modifier = modifier
          .background(MaterialTheme.colorScheme.surface)
          .fillMaxWidth()
          .paddingFromBaseline(top = 40.dp, bottom = 20.dp),
        text = stringResource(R.string.text_catalog_subtitle_all_products),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge,
      )
    }
    items(
      items = catalogTuples,
      key = CatalogItemTuple::id,
    ) { tuple ->
      CatalogTupleRow(
        appState = appState,
        tuple = tuple,
        onCatalogItemTupleClicked = onCatalogItemTupleClicked,
      )
    }
  }
}
