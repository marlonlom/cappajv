/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.tv.ui.util.tvSafeContentPadding

/**
 * Catalog lazy vertical grid composable ui.
 *
 * @author marlonlom
 *
 * @param headingTitle Headline title as string resource.
 * @param selectedTabIndex Selected tab index value.
 * @param onTabSelected Action for tab selected.
 * @param catalogContent Lazy vertical Grid catalog contents.
 * @param modifier The modifier for this composable.
 */
@Composable
fun CatalogLazyVerticalGrid(
  @StringRes headingTitle: Int,
  selectedTabIndex: Int,
  onTabSelected: (Int) -> Unit,
  catalogContent: LazyGridScope.() -> Unit,
  startWidth: Dp,
  modifier: Modifier = Modifier,
) = LazyVerticalGrid(
  state = rememberLazyGridState(),
  modifier = modifier
    .fillMaxWidth()
    .tvSafeContentPadding(startWidth)
    .padding(horizontal = 20.dp),
  columns = GridCells.Adaptive(160.dp),
  verticalArrangement = Arrangement.spacedBy(20.dp),
  horizontalArrangement = Arrangement.spacedBy(20.dp),
) {
  item(span = { GridItemSpan(maxLineSpan) }) {
    CatalogHeadlineWithCategoriesSlot(
      title = headingTitle,
      selectedTabIndex = selectedTabIndex,
      onTabSelected = { index -> onTabSelected(index) },
    )
  }

  catalogContent()

  item(span = { GridItemSpan(maxLineSpan) }) {
    Spacer(modifier.height(48.dp))
  }
}
