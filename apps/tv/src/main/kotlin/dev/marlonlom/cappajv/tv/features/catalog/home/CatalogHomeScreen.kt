/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.tv.R
import dev.marlonlom.cappajv.tv.features.catalog.categories.CategoryEntries
import dev.marlonlom.cappajv.tv.features.catalog.common.CatalogGridItemCompactCard
import dev.marlonlom.cappajv.tv.features.catalog.common.CatalogGridItemsLoadingIndicator
import dev.marlonlom.cappajv.tv.features.catalog.common.CatalogLazyVerticalGrid
import org.koin.androidx.compose.koinViewModel

/**
 * Catalog home list screen composable ui.
 *
 * @author marlonlom
 *
 * @param onCatalogItemClicked Action for Grid catalog item selected.
 * @param viewModel The viewmodel for this composable.
 */
@Composable
fun CatalogHomeScreen(
  startWidth: Dp,
  selectedCategory: Int,
  onCatalogItemClicked: (CatalogItemTuple) -> Unit,
  onCategorySelected: (Int) -> Unit,
  viewModel: CatalogHomeViewModel = koinViewModel()
) {
  val homeUiState = viewModel.uiState.collectAsStateWithLifecycle(
    lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current,
  )
  val context = LocalContext.current

  CatalogLazyVerticalGrid(
    headingTitle = R.string.text_home_title,
    selectedTabIndex = selectedCategory,
    onTabSelected = { index ->
      onCategorySelected(index)
    },
    catalogContent = {
      when (homeUiState.value) {
        CatalogHomeUiState.Empty -> Unit

        CatalogHomeUiState.Loading -> {
          item(
            span = { GridItemSpan(maxLineSpan) },
            content = {
              CatalogGridItemsLoadingIndicator()
            },
          )
        }

        is CatalogHomeUiState.Listing -> {
          val catalogMap: Map<String, List<CatalogItemTuple>> =
            (homeUiState.value as CatalogHomeUiState.Listing).catalogMap
          val categoryName = context.getString(CategoryEntries.entries[selectedCategory].text)

          item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
              modifier = Modifier
                .fillMaxWidth(),
              text = categoryName,
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onSurface,
            )
          }

          catalogMap[categoryName]?.let { tuples ->
            items(tuples, { tuple -> tuple.id }) { tuple ->
              CatalogGridItemCompactCard(
                catalogItem = tuple,
                onCatalogItemClicked = { onCatalogItemClicked(tuple) },
              )
            }
          }
        }
      }
    },
    startWidth = startWidth
  )
}
