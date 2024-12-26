/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.marlonlom.cappajv.tv.features.catalog.browse.CatalogBrowseScreen
import dev.marlonlom.cappajv.tv.features.catalog.details.CatalogDetailScreen
import dev.marlonlom.cappajv.tv.features.settings.SettingsScreen
import dev.marlonlom.cappajv.tv.ui.CappajvTvUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Tv navigation host composable ui.
 *
 * @author marlonlom
 *
 * @param appState The application ui state.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun TvNavigationHost(
  appState: CappajvTvUiState,
) = NavHost(
  navController = appState.navHostController,
  startDestination = TvScreenRoute.Catalog
) {
  composable<TvScreenRoute.Catalog>(
    enterTransition = { fadeIn() },
    exitTransition = { fadeOut() },
  ) {
    CatalogBrowseScreen(appState = appState)
  }

  composable<TvScreenRoute.Settings>(
    enterTransition = { fadeIn() },
    exitTransition = { fadeOut() },
  ) {
    SettingsScreen()
  }

  composable<TvScreenRoute.Detail>(
    enterTransition = { fadeIn() },
    exitTransition = { fadeOut() },
  ) { backstackEntry ->
    val detailId = backstackEntry.toRoute<TvScreenRoute.Detail>()
    CatalogDetailScreen(
      detailId = detailId.itemId,
      onNavigationBack = {
        appState.navigateBack()
      },
    )
  }

}
