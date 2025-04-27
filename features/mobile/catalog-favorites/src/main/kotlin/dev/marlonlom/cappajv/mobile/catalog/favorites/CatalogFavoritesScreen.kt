/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.favorites

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.mobile.catalog.favorites.component.CatalogGridItem
import dev.marlonlom.cappajv.mobile.catalog.favorites.component.UnFavoriteCatalogItemAlert
import dev.marlonlom.cappajv.mobile.catalog.favorites.component.empty
import dev.marlonlom.cappajv.mobile.catalog.favorites.component.header
import dev.marlonlom.cappajv.mobile.catalog.favorites.component.loadingIndicator
import dev.marlonlom.cappajv.mobile.catalog.favorites.domain.CatalogFavoritesUiState
import dev.marlonlom.cappajv.mobile.catalog.favorites.domain.CatalogFavoritesViewModel
import dev.marlonlom.cappajv.mobile.catalog.favorites.slot.CatalogFavoritesHeadlineSlot
import dev.marlonlom.cappajv.mobile.catalog.favorites.slot.CategoryTitleSlot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

/**
 * Displays the catalog favorites screen with a grid layout of favorite items.
 *
 * This composable renders a grid of favorite catalog items, allowing the user to interact
 * with individual items. The layout is responsive to the specified number of columns.
 *
 * @author marlonlom
 *
 * @param onCatalogGridItemClicked A callback invoked when a catalog item is clicked.
 * @param columnsCount The number of columns to use in the grid layout. Defaults to `2`.
 * @param viewModel The [CatalogFavoritesViewModel] instance used to provide data and handle logic.
 */
@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun CatalogFavoritesScreen(
  onCatalogGridItemClicked: (Long) -> Unit,
  columnsCount: Int = 2,
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  viewModel: CatalogFavoritesViewModel = koinViewModel(),
) {
  val uiState = viewModel.uiState.collectAsStateWithLifecycle()

  val gridCellsCount: @Composable (Int) -> Int = {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    val isCompactHeight = LocalConfiguration.current.screenHeightDp < 480

    when {
      columnsCount > 2 -> columnsCount
      isLandscape.and(isCompactHeight) -> 5
      isLandscape.and(!isCompactHeight) -> 3
      else -> 2
    }
  }

  val categoriesArray = LocalContext.current.resources.getStringArray(R.array.array_categories)
  val expandingCategories = remember {
    mutableStateListOf(*Array(3) { mutableStateOf(true) })
  }

  val shouldConfirmUnFavorite = remember { mutableStateOf(false) }
  val unFavoriteCatalogItem = remember { mutableStateOf<CatalogItemTuple?>(null) }

  if (shouldConfirmUnFavorite.value) {
    UnFavoriteCatalogItemAlert(
      shouldShowDialog = shouldConfirmUnFavorite,
      catalogItem = unFavoriteCatalogItem.value!!,
      onConfirm = {
        unFavoriteCatalogItem.value?.let {
          coroutineScope.launch {
            viewModel.undoFavorite(it.id)
          }
        }
      },
      onDismiss = {
        unFavoriteCatalogItem.value = null
      },
    )
  }

  LazyVerticalGrid(
    columns = GridCells.Fixed(gridCellsCount(columnsCount)),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    header {
      CatalogFavoritesHeadlineSlot()
    }

    when (uiState.value) {
      CatalogFavoritesUiState.Empty -> {
        empty()
      }

      CatalogFavoritesUiState.Loading -> {
        loadingIndicator()
      }

      is CatalogFavoritesUiState.Success -> {
        (uiState.value as CatalogFavoritesUiState.Success).catalogMap
          .toSortedMap(compareBy { it })
          .forEach { (category: String, tuples: List<CatalogItemTuple>) ->
            val categoryIndex = categoriesArray.indexOf(category)
            header {
              CategoryTitleSlot(
                title = category,
                itemsCount = tuples.size,
                index = categoryIndex,
                isExpanded = expandingCategories[categoryIndex],
                onTitleClicked = {
                  expandingCategories[categoryIndex].value = !expandingCategories[categoryIndex].value
                },
              )
            }

            if (expandingCategories[categoryIndex].value) {
              items(tuples.size, { i -> tuples[i].id }) { pos ->
                AnimatedVisibility(visible = expandingCategories[categoryIndex].value) {
                  CatalogGridItem(
                    item = tuples[pos],
                    onItemClicked = onCatalogGridItemClicked,
                    onItemLongClicked = {
                      unFavoriteCatalogItem.value = it
                      shouldConfirmUnFavorite.value = true
                    },
                  )
                }
              }
            }
          }
      }
    }
  }
}
