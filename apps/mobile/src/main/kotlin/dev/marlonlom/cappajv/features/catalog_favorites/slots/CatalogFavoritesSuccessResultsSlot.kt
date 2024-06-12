/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.catalog_favorites.slots

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.features.catalog_favorites.parts.CatalogFavoriteItemCard

/**
 * Catalog favorites success results slot composable ui.
 *
 * @author marlonlom
 *
 * @param list Catalog favorite items list.
 * @param isRouting True/False if should navigate through routing.
 * @param onFavoriteItemClicked Action for favorite item clicked.
 * @param onFavoriteItemRemoved Action for favorite item removed.
 * @param modifier Modifier for this composable.
 */
@ExperimentalFoundationApi
@Composable
fun CatalogFavoritesSuccessResultsSlot(
  list: List<CatalogItemTuple>,
  isRouting: Boolean,
  onFavoriteItemClicked: (Long, Boolean) -> Unit,
  onFavoriteItemRemoved: (Long) -> Unit,
  modifier: Modifier = Modifier,
) = LazyColumn(
  modifier = modifier
    .fillMaxSize()
    .padding(top = 20.dp),
  verticalArrangement = Arrangement.spacedBy(10.dp),
) {
  items(
    items = list,
    key = CatalogItemTuple::id,
  ) { row ->
    CatalogFavoriteItemCard(
      row = row,
      isRouting = isRouting,
      onFavoriteItemClicked = onFavoriteItemClicked,
      onFavoriteItemRemoved = onFavoriteItemRemoved
    )
  }
}
