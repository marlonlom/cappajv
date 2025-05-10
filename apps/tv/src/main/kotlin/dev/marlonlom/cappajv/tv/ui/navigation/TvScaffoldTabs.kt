/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Tab
import androidx.tv.material3.TabDefaults
import androidx.tv.material3.TabRow
import androidx.tv.material3.TabRowDefaults
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.tv.R
import dev.marlonlom.cappajv.tv.designsystem.component.CappajvTvBanner

/**
 * Internal composable function for displaying and handling tabs within a TV scaffold.
 *
 * This composable provides a row of tab indicators and manages tab selection.
 *
 * @author marlonlom
 *
 * @param tabIndex The currently selected tab index.
 * @param onTabChanged A callback function invoked when the selected tab changes. It receives the new tab index.
 */
@Composable
internal fun TvScaffoldTabs(tabIndex: Int, onTabChanged: (Int) -> Unit, focusedTab: FocusRequester) = Row(
  modifier = Modifier.padding(vertical = 24.dp),
  verticalAlignment = Alignment.CenterVertically,
) {
  TabRow(
    modifier = Modifier.focusProperties { onEnter = { focusedTab.requestFocus() } },
    selectedTabIndex = tabIndex,
    indicator = { tabPositions, doesTabRowHaveFocus ->
      TabRowDefaults.UnderlinedIndicator(
        currentTabPosition = tabPositions[tabIndex],
        doesTabRowHaveFocus = doesTabRowHaveFocus,
      )
    },
  ) {
    TvDestinations.entries.forEachIndexed { index, destination ->
      val tabModifier = if (tabIndex == index) {
        Modifier.focusRequester(focusedTab)
      } else {
        Modifier
      }

      Tab(
        modifier = tabModifier,
        selected = tabIndex == index,
        onFocus = { onTabChanged.invoke(index) },
        colors = TabDefaults.underlinedIndicatorTabColors(
          selectedContentColor = MaterialTheme.colorScheme.primary,
          focusedSelectedContentColor = MaterialTheme.colorScheme.primary,
        ),
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
          Icon(imageVector = destination.icon, contentDescription = stringResource(destination.hint))
          Text(
            text = stringResource(destination.title),
            style = MaterialTheme.typography.bodySmall,
          )
        }
      }
    }
  }

  Spacer(Modifier.weight(1.0f))

  CappajvTvBanner(appName = R.string.app_name, brandImg = R.drawable.img_logo)
}
