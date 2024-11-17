/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.tv.R
import dev.marlonlom.cappajv.tv.features.catalog.categories.CategoryEntries
import dev.marlonlom.cappajv.tv.ui.slots.HeadlineWithCategoriesSlot

/**
 * Catalog home list screen composable ui.
 *
 * @author marlonlom
 *
 * @param modifier The modifier for this composable.
 */
@Composable
fun CatalogHomeScreen(
  modifier: Modifier = Modifier
) {
  var selectedTabIndex by remember { mutableIntStateOf(0) }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    HeadlineWithCategoriesSlot(
      title = R.string.text_home_title,
      selectedTabIndex = selectedTabIndex,
      onTabSelected = { index ->
        selectedTabIndex = index
      },
    )

    LazyVerticalGrid(
      state = rememberLazyGridState(),
      modifier = modifier.fillMaxWidth(),
      columns = GridCells.Fixed(4),
    ) {
      item(span = { GridItemSpan(maxLineSpan) }) {
        Text(
          text = stringResource(CategoryEntries.entries[selectedTabIndex].text),
          style = MaterialTheme.typography.bodyLarge,
          color = MaterialTheme.colorScheme.onSurface,
        )
      }
    }
  }
}
