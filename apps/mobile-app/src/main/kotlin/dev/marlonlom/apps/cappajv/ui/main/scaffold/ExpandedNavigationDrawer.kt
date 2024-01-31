/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.ui.main.scaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.ui.navigation.CatalogDestination

/**
 * Expanded navigation drawer composable ui.
 *
 * @author marlonlom
 *
 * @param selectedPosition Selected index.
 * @param onSelectedPositionChanged Action for selected navigation bar item index changed.
 */
@Composable
fun ExpandedNavigationDrawer(
  selectedPosition: Int,
  onSelectedPositionChanged: (Int, String) -> Unit,
  content: @Composable () -> Unit,
) {
  PermanentNavigationDrawer(
    drawerContent = {
      PermanentDrawerSheet(
        modifier = Modifier
          .width(200.dp)
          .padding(top = 40.dp)
          .padding(horizontal = 20.dp),
      ) {
        NavigationDrawerContent(
          selectedPosition = selectedPosition,
          onSelectedPositionChanged = onSelectedPositionChanged
        )
      }
    },
  ) {
    content()
  }
}

@Composable
internal fun NavigationDrawerContent(
  selectedPosition: Int,
  onSelectedPositionChanged: (Int, String) -> Unit
) {
  CatalogDestination.topCatalogDestinations.forEachIndexed { index, destination ->
    NavigationDrawerItem(
      shape = MaterialTheme.shapes.large,
      selected = selectedPosition == index,
      onClick = {
        onSelectedPositionChanged(index, destination.route)
      },
      icon = {
        Icon(
          imageVector = destination.icon,
          contentDescription = stringResource(id = destination.title)
        )
      },
      label = {
        Text(text = stringResource(id = destination.title))
      },
    )
  }
}
