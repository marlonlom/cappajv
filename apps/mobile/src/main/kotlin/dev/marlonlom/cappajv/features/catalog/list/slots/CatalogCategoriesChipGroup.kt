/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.catalog.list.slots

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Coffee
import androidx.compose.material.icons.twotone.CoffeeMaker
import androidx.compose.material.icons.twotone.Cookie
import androidx.compose.material.icons.twotone.FilterList
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * Catalog categories chip group slot composable ui.
 *
 * @author marlonlom
 *
 * @param selectedCategory Selected category.
 * @param onCategoryChipSelected Action for category changed.
 */
@ExperimentalLayoutApi
@Composable
fun CatalogCategoriesChipGroup(
  categories: List<String>,
  selectedCategory: String,
  onCategoryChipSelected: (String) -> Unit,
) {
  val list = categories.mapIndexed { index, category ->
    Pair(
      first = category,
      second = when (index) {
        0 -> Icons.TwoTone.FilterList
        1 -> Icons.TwoTone.Coffee
        2 -> Icons.TwoTone.CoffeeMaker
        else -> Icons.TwoTone.Cookie
      },
    )
  }

  val addCategoryFilterChip: @Composable (Pair<String, ImageVector>) -> Unit = { category ->
    FilterChip(
      selected = selectedCategory == category.first,
      onClick = {
        onCategoryChipSelected(category.first)
      },
      label = {
        Text(category.first)
      },
      leadingIcon = {
        Icon(
          imageVector = category.second,
          contentDescription = "${category.first} icon",
          modifier = Modifier.size(FilterChipDefaults.IconSize),
        )
      },
    )
  }

  LazyRow(
    horizontalArrangement = Arrangement.spacedBy(
      space = 10.dp,
      alignment = Alignment.Start,
    ),
  ) {
    items(
      items = list,
      key = { it.first },
    ) { category ->
      addCategoryFilterChip(category)
    }
  }
}
