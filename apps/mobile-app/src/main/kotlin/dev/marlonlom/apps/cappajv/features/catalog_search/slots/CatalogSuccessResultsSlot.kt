/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_search.slots

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
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
import dev.marlonlom.apps.cappajv.features.catalog_search.parts.CatalogSearchedItemCard

/**
 * Catalog success results slot composable ui.
 *
 * @author marlonlom
 *
 * @param searchResults Catalog searched items list.
 * @param isRouting True/False if should navigate through routing.
 * @param onSearchedItemClicked Action for searched item clicked.
 * @param modifier Modifier for this composable.
 *
 */
@ExperimentalFoundationApi
@Composable
fun CatalogSuccessResultsSlot(
  searchResults: List<CatalogItemTuple>,
  isRouting: Boolean,
  onSearchedItemClicked: (Long, Boolean) -> Unit,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(top = 20.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    stickyHeader {
      Text(
        modifier = modifier
          .fillMaxWidth()
          .background(MaterialTheme.colorScheme.surface)
          .paddingFromBaseline(top = 20.dp, bottom = 20.dp),
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        text = stringResource(R.string.text_catalog_search_success_subtitle, searchResults.size),
        style = MaterialTheme.typography.titleMedium
      )
    }

    items(
      items = searchResults,
      key = CatalogItemTuple::id,
    ) { row ->
      CatalogSearchedItemCard(
        tuple = row,
        isRouting = isRouting,
        onSearchedItemClicked = onSearchedItemClicked
      )
    }
  }
}
