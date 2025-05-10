/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.tv.catalog.home.CatalogHomeTvScreen

/**
 * A composable scaffold layout for TV interfaces.
 *
 * @author marlonlom
 */
@Composable
fun TvScaffold() {
  val (focusRequester, focusedTab) = remember { FocusRequester.createRefs() }
  LaunchedEffect(Unit) { focusRequester.requestFocus() }
  var selectedTabIndex by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier
      .focusRequester(focusRequester)
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .padding(horizontal = 48.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    TvScaffoldTabs(
      tabIndex = selectedTabIndex,
      onTabChanged = {
        focusRequester.saveFocusedChild()
        selectedTabIndex = it
      },
      focusedTab = focusedTab,
    )

    when (selectedTabIndex) {
      TvDestinations.HOME.ordinal -> {
        CatalogHomeTvScreen(
          onItemClicked = {},
          focusRequester = focusRequester,
        )
      }

      TvDestinations.FAVORITES.ordinal -> {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center,
        ) {
          Text(
            text = "Tab: ${TvDestinations.FAVORITES.name}",
            color = MaterialTheme.colorScheme.onBackground,
          )
        }
      }

      TvDestinations.SETTINGS.ordinal -> {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center,
        ) {
          Text(
            text = "Tab: ${TvDestinations.SETTINGS.name}",
            color = MaterialTheme.colorScheme.onBackground,
          )
        }
      }
    }
  }
}
