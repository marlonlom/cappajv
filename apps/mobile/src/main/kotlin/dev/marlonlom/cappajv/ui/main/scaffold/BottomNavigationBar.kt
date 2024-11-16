/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.ui.main.scaffold

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.marlonlom.cappajv.ui.navigation.CatalogDestination

/**
 * Main bottom navigation bar composable ui.
 *
 * @author marlonlom
 *
 * @param selectedPosition Selected index.
 * @param onSelectedPositionChanged Action for selected navigation bar item index changed.
 */
@Composable
fun BottomNavigationBar(selectedPosition: Int, onSelectedPositionChanged: (Int, String) -> Unit) {
  NavigationBar {
    CatalogDestination.topCatalogDestinations.forEachIndexed { index, destination ->
      NavigationBarItem(
        selected = selectedPosition == index,
        onClick = {
          onSelectedPositionChanged(index, destination.route)
        },
        icon = {
          Icon(
            imageVector = destination.icon,
            contentDescription = stringResource(id = destination.title),
          )
        },
        label = {
          Text(text = stringResource(id = destination.title))
        },
      )
    }
  }
}
