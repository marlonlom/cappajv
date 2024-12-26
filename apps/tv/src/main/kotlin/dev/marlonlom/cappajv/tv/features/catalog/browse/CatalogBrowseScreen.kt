/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.browse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.NavigationDrawerScope
import androidx.tv.material3.Text
import androidx.tv.material3.rememberDrawerState
import dev.marlonlom.cappajv.tv.features.catalog.favorites.CatalogFavoritesScreen
import dev.marlonlom.cappajv.tv.features.catalog.home.CatalogHomeScreen
import dev.marlonlom.cappajv.tv.ui.CappajvTvUiState
import timber.log.Timber

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

/**
 * Catalog browse navigation drawer content composable ui.
 *
 * @author marlonlom
 *
 * @param appState The application ui state.
 * @param modifier The modifier for this composable
 */
@Composable
private fun NavigationDrawerScope.CatalogBrowseDrawerContent(
  appState: CappajvTvUiState,
  modifier: Modifier = Modifier,
) = Column(
  modifier = modifier
    .background(MaterialTheme.colorScheme.surfaceTint)
    .fillMaxHeight()
    .padding(12.dp)
    .selectableGroup(),
  horizontalAlignment = Alignment.Start,
  verticalArrangement = Arrangement.spacedBy(10.dp),
) {
  Spacer(Modifier.height(120.dp))

  CatalogBrowseMenuItems.entries.forEachIndexed { index, entry ->
    if (index == CatalogBrowseMenuItems.entries.size - 1) {
      Spacer(Modifier.weight(1.0f))
    }
    NavigationDrawerItem(
      selected = appState.browseMenuIndex == entry.ordinal,
      onClick = {
        Timber.d("[CatalogBrowseDrawerContent] entry=$entry")
        appState.changeBrowseMenuIndex(entry.ordinal)
        if (appState.browseMenuIndex == CatalogBrowseMenuItems.SETTINGS.ordinal) {
          appState.navigateToSettings()
          return@NavigationDrawerItem
        }
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
