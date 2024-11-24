/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.favorites

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.tv.R
import dev.marlonlom.cappajv.tv.features.catalog.categories.CategoryEntries
import dev.marlonlom.cappajv.tv.features.catalog.common.CatalogGridCategoryText
import dev.marlonlom.cappajv.tv.features.catalog.common.CatalogGridItemCompactCard
import dev.marlonlom.cappajv.tv.features.catalog.common.CatalogGridItemsLoadingIndicator
import dev.marlonlom.cappajv.tv.features.catalog.common.CatalogLazyVerticalGrid
import dev.marlonlom.cappajv.tv.features.catalog.favorites.CatalogFavoritesUiState.Success
import org.koin.androidx.compose.koinViewModel

/**
 * Catalog favorite list screen composable ui.
 *
 * @author marlonlom
 *
 * @param onCatalogItemClicked Action for Grid catalog item selected.
 * @param modifier The modifier for this composable.
 * @param viewModel The viewmodel for this composable.
 */
@Composable
fun CatalogFavoritesScreen(
  onCatalogItemClicked: (CatalogItemTuple) -> Unit = {},
  modifier: Modifier = Modifier,
  viewModel: CatalogFavoritesViewModel = koinViewModel()
) {
  var selectedTabIndex by remember { mutableIntStateOf(0) }
  val favoritesUiState = viewModel.uiState.collectAsStateWithLifecycle(
    lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current,
  )
  val context = LocalContext.current

  CatalogLazyVerticalGrid(
    headingTitle = R.string.text_favorites_title,
    selectedTabIndex = selectedTabIndex,
    onTabSelected = { index ->
      selectedTabIndex = index
    },
    catalogContent = {
      when (favoritesUiState.value) {
        CatalogFavoritesUiState.Empty -> {
          item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
              text = "No favorite items :(",
              modifier = modifier.fillMaxSize(),
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.onSurface,
              textAlign = TextAlign.Center
            )
          }
        }

        CatalogFavoritesUiState.Fetching -> {
          item(span = { GridItemSpan(maxLineSpan) }) {
            CatalogGridItemsLoadingIndicator()
          }
        }

        is Success -> {
          item(span = { GridItemSpan(maxLineSpan) }) {
            CatalogGridCategoryText(
              categoryTitle = CategoryEntries.entries[selectedTabIndex].text
            )
          }

          val categoryName = context.getString(CategoryEntries.entries[selectedTabIndex].text)
          val catalogItems = (favoritesUiState.value as Success).results.filter {
            it.category == categoryName
          }


          catalogItems.let {
            if (it.isEmpty()) {
              item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                  text = "No favorite items :(",
                  modifier = modifier.fillMaxSize(),
                  style = MaterialTheme.typography.titleMedium,
                  color = MaterialTheme.colorScheme.onSurface,
                  textAlign = TextAlign.Center
                )
              }
            } else {
              items(
                items = catalogItems,
                key = { favoriteItem -> favoriteItem.id }
              ) { favoriteItem ->
                CatalogGridItemCompactCard(
                  catalogItem = favoriteItem,
                  onCatalogItemClicked = onCatalogItemClicked
                )
              }
            }
          }



          item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier.height(48.dp))
          }
        }
      }
    }
  )
}
