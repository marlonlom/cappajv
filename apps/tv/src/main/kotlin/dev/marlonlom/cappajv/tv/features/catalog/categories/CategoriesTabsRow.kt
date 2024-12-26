/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.categories

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Tab
import androidx.tv.material3.TabDefaults
import androidx.tv.material3.TabRow
import androidx.tv.material3.TabRowDefaults
import dev.marlonlom.cappajv.tv.R

/**
 * Categories tab with icons composable ui.
 *
 * @author marlonlom
 *
 * @param selectedTabIndex Selected tab index value.
 * @param onTabSelected Action for tab selected.
 * @param modifier The modifier for this composable.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CategoriesTabsRow(selectedTabIndex: Int, onTabSelected: (Int) -> Unit, modifier: Modifier = Modifier) = TabRow(
  modifier = modifier.focusRestorer(),
  selectedTabIndex = selectedTabIndex,
  indicator = { tabPositions, doesTabRowHaveFocus ->
    TabRowDefaults.UnderlinedIndicator(
      currentTabPosition = tabPositions[selectedTabIndex],
      doesTabRowHaveFocus = doesTabRowHaveFocus,
      activeColor = MaterialTheme.colorScheme.secondary,
      inactiveColor = MaterialTheme.colorScheme.secondaryContainer,
    )
  },
  tabs = {
    CategoryEntries.entries.sortedBy { it.ordinal }.forEach { category ->
      Tab(
        modifier = modifier
          .padding(horizontal = 10.dp)
          .testTag(stringResource(R.string.text_tag_category_tab, category.ordinal)),
        selected = selectedTabIndex == category.ordinal,
        onFocus = {},
        onClick = {
          onTabSelected(category.ordinal)
        },
        colors = TabDefaults.underlinedIndicatorTabColors(
          contentColor = MaterialTheme.colorScheme.surfaceTint,
          inactiveContentColor = MaterialTheme.colorScheme.surfaceTint,
          selectedContentColor = MaterialTheme.colorScheme.secondaryContainer,
          focusedContentColor = MaterialTheme.colorScheme.secondary,
        ),
      ) {
        Icon(
          imageVector = category.icon,
          contentDescription = stringResource(category.text),
          modifier = modifier
            .size(36.dp)
            .padding(bottom = 10.dp),
        )
      }
    }
  },
)
