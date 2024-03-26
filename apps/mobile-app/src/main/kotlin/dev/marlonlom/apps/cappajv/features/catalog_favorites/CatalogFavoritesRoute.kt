/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.features.catalog_list.CatalogListViewModel
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteProductsRoute(
  appState: CappajvAppState,
  viewModel: CatalogFavoritesViewModel = koinViewModel(),
) {
  val contentHorizontalPadding = when {
    appState.isLandscape.not().and(appState.isMediumWidth) -> 40.dp
    appState.isLandscape.not().and(appState.isExpandedWidth) -> 80.dp
    else -> 20.dp
  }
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(contentHorizontalPadding)
  ) {
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .paddingFromBaseline(top = 40.dp, bottom = 20.dp),
      text = "Favorite products",
      style = MaterialTheme.typography.headlineLarge
    )

  }
}
