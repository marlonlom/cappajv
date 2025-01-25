/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.browse

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.rememberDrawerState
import dev.marlonlom.cappajv.tv.features.catalog.favorites.CatalogFavoritesScreen
import dev.marlonlom.cappajv.tv.features.catalog.home.CatalogHomeScreen
import dev.marlonlom.cappajv.tv.ui.CappajvTvUiState

/**
 * Catalog browse composable ui.
 *
 * @author marlonlom
 *
 * @param appState The application ui state.
 * @param modifier The modifier for this composable
 */
@Composable
fun CatalogBrowseScreen(appState: CappajvTvUiState, modifier: Modifier = Modifier) {
  val catalogBrowseDrawerState = rememberDrawerState(DrawerValue.Closed)
  val catalogBrowseStartWidth: Dp by remember {
    derivedStateOf {
      when (catalogBrowseDrawerState.currentValue) {
        DrawerValue.Closed -> 80.dp
        DrawerValue.Open -> 280.dp
      }
    }
  }
  ModalNavigationDrawer(
    drawerState = catalogBrowseDrawerState,
    modifier = modifier
      .background(MaterialTheme.colorScheme.background),
    drawerContent = {
      CatalogBrowseDrawerContent(appState)
    },
    scrimBrush = SolidColor(MaterialTheme.colorScheme.scrim.copy(alpha = 0.5f)),
    content = {
      when (appState.browseMenuIndex) {
        CatalogBrowseMenuItems.FAVORITES.ordinal -> {
          CatalogFavoritesScreen(
            startWidth = catalogBrowseStartWidth,
            selectedCategory = appState.favoritesCategoryIndex,
            onCatalogItemClicked = {
              appState.navigateToDetails(it.id)
            },
            onCategorySelected = { index ->
              appState.changeFavoritesCategoryIndex(index)
            },
          )
        }

        else -> {
          CatalogHomeScreen(
            startWidth = catalogBrowseStartWidth,
            selectedCategory = appState.homeCategoryIndex,
            onCatalogItemClicked = {
              appState.navigateToDetails(it.id)
            },
            onCategorySelected = { index ->
              appState.changeHomeCategoryIndex(index)
            },
          )
        }
      }
    },
  )
}
