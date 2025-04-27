/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.home

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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.mobile.catalog.home.component.CatalogGridItem
import dev.marlonlom.cappajv.mobile.catalog.home.component.header
import dev.marlonlom.cappajv.mobile.catalog.home.component.loadingIndicator
import dev.marlonlom.cappajv.mobile.catalog.home.domain.CatalogHomeUiState
import dev.marlonlom.cappajv.mobile.catalog.home.domain.CatalogHomeViewModel
import dev.marlonlom.cappajv.mobile.catalog.home.slot.CatalogHomeHeadlineSlot
import dev.marlonlom.cappajv.mobile.catalog.home.slot.CategoryTitleSlot
import org.koin.androidx.compose.koinViewModel

/**
 * Displays the home screen of the coffee catalog app.
 *
 * @author marlonlom
 *
 * @param onCatalogGridItemClicked A callback invoked when a catalog item is clicked,
 * providing the item's ID as a [Long].
 * @param columnsCount The number of columns to use in the catalog grid. Defaults to `2`.
 * @param viewModel The [CatalogHomeViewModel] instance used to provide UI state and handle events.
 */
@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun CatalogHomeScreen(
  onCatalogGridItemClicked: (Long) -> Unit,
  columnsCount: Int = 2,
  viewModel: CatalogHomeViewModel = koinViewModel(),
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

  LazyVerticalGrid(
    columns = GridCells.Fixed(gridCellsCount(columnsCount)),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    header {
      CatalogHomeHeadlineSlot()
    }

    when (uiState.value) {
      CatalogHomeUiState.Empty -> {
      }

      CatalogHomeUiState.Loading -> {
        loadingIndicator()
      }

      is CatalogHomeUiState.Success -> {
        (uiState.value as CatalogHomeUiState.Success).catalogMap
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
                    tuples[pos],
                    onCatalogGridItemClicked,
                  )
                }
              }
            }
          }
      }
    }
  }
}
