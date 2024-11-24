/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
 * @param modifier The modifier for this composable.
 * @param viewModel The viewmodel for this composable.
 */
@Composable
fun CatalogHomeScreen(
  onCatalogItemClicked: (CatalogItemTuple) -> Unit = {},
  modifier: Modifier = Modifier,
  viewModel: CatalogHomeViewModel = koinViewModel()
) {
  var selectedTabIndex by remember { mutableIntStateOf(0) }
  val homeUiState = viewModel.uiState.collectAsStateWithLifecycle(
    lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current,
  )

  CatalogLazyVerticalGrid(
    headingTitle = R.string.text_home_title,
    selectedTabIndex = selectedTabIndex,
    onTabSelected = { index ->
      selectedTabIndex = index
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
          item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
              modifier = modifier
                .fillMaxWidth(),
              text = stringResource(CategoryEntries.entries[selectedTabIndex].text),
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onSurface,
            )
          }

          val catalogMap: Map<String, List<CatalogItemTuple>> =
            (homeUiState.value as CatalogHomeUiState.Listing).catalogMap
          val categoryName = catalogMap.keys.filterIndexed { pos, _ ->
            pos == selectedTabIndex
          }.first()
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
    }
  )
}
