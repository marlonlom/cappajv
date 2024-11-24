/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.browse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.NavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.NavigationDrawerItemDefaults
import androidx.tv.material3.Text
import androidx.tv.material3.rememberDrawerState
import dev.marlonlom.cappajv.tv.features.catalog.favorites.CatalogFavoritesScreen
import dev.marlonlom.cappajv.tv.features.catalog.home.CatalogHomeScreen

/**
 * Tv browse content screen composable ui.
 *
 * @author marlonlom
 *
 * @param modifier The modifier for this composable.
 */
@Composable
fun TvBrowseScreen(modifier: Modifier = Modifier) {
  var selectedIndex by remember { mutableIntStateOf(0) }
  val currentDrawerState = rememberDrawerState(DrawerValue.Closed)
  val currentCtx = LocalContext.current

  NavigationDrawer(
    modifier = modifier
      .background(MaterialTheme.colorScheme.background)
      .fillMaxWidth()
      .selectableGroup(),
    drawerState = currentDrawerState,
    drawerContent = {
      Column(
        modifier = modifier
          .background(MaterialTheme.colorScheme.surfaceTint)
          .fillMaxHeight()
          .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
      ) {
        TvBrowseMenuItems.entries.forEachIndexed { index, entry ->
          if (index == TvBrowseMenuItems.entries.size - 1) {
            Spacer(Modifier.height(40.dp))
          }
          NavigationDrawerItem(
            modifier = modifier.padding(10.dp),
            shape = NavigationDrawerItemDefaults.shape(
              shape = MaterialTheme.shapes.medium.copy(
                all = CornerSize(50)
              ),
            ),
            selected = selectedIndex == index,
            onClick = {
              selectedIndex = index
              println("menu changed: $selectedIndex - ${currentCtx.getString(entry.text)}")
            },
            leadingContent = {
              Icon(
                imageVector = entry.icon,
                contentDescription = null,
              )
            },
            content = {
              Text(stringResource(entry.text))
            },
          )
        }
      }
    },
  ) {
    when (selectedIndex) {
      TvBrowseMenuItems.FAVORITE.ordinal -> {
        CatalogFavoritesScreen()
      }

      else -> {
        CatalogHomeScreen()
      }
    }
  }
}
