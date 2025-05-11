/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.catalog.home

import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.domain.catalog.home.CatalogHomeUiState
import dev.marlonlom.cappajv.tv.catalog.home.domain.CatalogHomeViewModel
import dev.marlonlom.cappajv.tv.designsystem.component.CatalogItemTvCard
import dev.marlonlom.cappajv.tv.designsystem.slot.CategoryTitleSlot
import org.koin.androidx.compose.koinViewModel

/**
 * Displays the home screen of the coffee catalog tv app.
 *
 * @author marlonlom
 *
 * @param onItemClicked A callback invoked when a catalog item is clicked,
 * providing the item's ID as a [Long].
 * @param viewModel The [CatalogHomeViewModel] instance used to provide UI state and handle events.
 */
@Composable
fun CatalogHomeTvScreen(onItemClicked: (Long) -> Unit, viewModel: CatalogHomeViewModel = koinViewModel()) {
  val uiState = viewModel.uiState.collectAsStateWithLifecycle()
  val categoriesArray = LocalContext.current.resources.getStringArray(R.array.array_categories)
  val childFocusRequester = remember { FocusRequester() }

  LazyColumn(
    modifier = Modifier.focusProperties {
      onEnter = {
        down = childFocusRequester
      }
    },
  ) {
    when (uiState.value) {
      CatalogHomeUiState.Empty -> {}

      CatalogHomeUiState.Loading -> {
        item(
          content = {
            Box(
              modifier = Modifier.fillMaxSize(),
              contentAlignment = Alignment.Center,
            ) {
              Text(
                text = "Loading...",
                color = MaterialTheme.colorScheme.onBackground,
              )
            }
          },
        )
      }

      is CatalogHomeUiState.Success -> {
        (uiState.value as CatalogHomeUiState.Success).catalogMap.toSortedMap(compareBy { it })
          .forEach { (category: String, tuples: List<CatalogItemTuple>) ->
            val categoryIndex = categoriesArray.indexOf(category)
            item { CategoryTitleSlot(category, categoryIndex) }
            item {
              LazyRow(
                modifier = Modifier
                  .focusRequester(childFocusRequester)
                  .focusGroup(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
              ) {
                items(
                  count = tuples.size,
                  key = { pos -> tuples[pos].id },
                  itemContent = { pos ->
                    CatalogItemTvCard(
                      itemId = tuples[pos].id,
                      itemTitle = tuples[pos].title,
                      itemPhoto = tuples[pos].picture,
                      onItemClicked = onItemClicked,
                    )
                  },
                )
              }
            }
          }

        item(content = { Spacer(Modifier.height(48.dp)) })
      }
    }
  }
}
