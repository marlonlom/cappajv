/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.tv.features.catalog.categories.CategoriesTabsRow

/**
 * Catalog headline with title and categories slot composable ui.
 *
 * @author marlonlom
 *
 * @param title Headline title as string resource.
 * @param selectedTabIndex Selected tab index value.
 * @param onTabSelected Action for tab selected.
 * @param modifier The modifier for this composable.
 */
@Composable
fun CatalogHeadlineWithCategoriesSlot(
  @StringRes title: Int,
  selectedTabIndex: Int,
  onTabSelected: (Int) -> Unit,
  modifier: Modifier = Modifier,
) = Row(
  modifier = modifier
    .fillMaxWidth()
    .padding(top = 60.dp, bottom = 20.dp),
  verticalAlignment = Alignment.CenterVertically,
  horizontalArrangement = Arrangement.SpaceBetween,
) {
  Text(
    modifier = modifier.paddingFromBaseline(40.dp, 20.dp),
    text = stringResource(title),
    fontWeight = FontWeight.Bold,
    color = MaterialTheme.colorScheme.onSurface,
    style = MaterialTheme.typography.headlineLarge,
  )

  CategoriesTabsRow(selectedTabIndex, onTabSelected)
}
