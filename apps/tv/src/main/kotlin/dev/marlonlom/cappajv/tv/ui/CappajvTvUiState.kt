/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.marlonlom.cappajv.tv.ui.navigation.TvScreenRoute
import kotlinx.coroutines.flow.map

/**
 * Cappajv Application ui state class.
 *
 * @author marlonlom
 *
 * @property navHostController Navigation host controller.
 */
@Stable
class CappajvTvUiState(
  val navHostController: NavHostController
) {

  var browseMenuIndex by mutableIntStateOf(0)
  var homeCategoryIndex by mutableIntStateOf(0)
  var favoritesCategoryIndex by mutableIntStateOf(0)

  val currentRouteFlow = navHostController.currentBackStackEntryFlow.map {
    it.destination.route
  }

  fun navigateToHome() {
    navHostController.navigate(TvScreenRoute.Catalog)
  }

  fun navigateToSettings() {
    navHostController.navigate(TvScreenRoute.Settings)
  }

  fun navigateToDetails(itemId: Long) {
    navHostController.navigate(TvScreenRoute.Detail(itemId.toString()))
  }

  fun navigateBack() {
    navHostController.popBackStack()
  }

  fun changeBrowseMenuIndex(ordinal: Int) {
    browseMenuIndex = ordinal
  }

  fun changeHomeCategoryIndex(index: Int) {
    homeCategoryIndex = index
  }

  fun changeFavoritesCategoryIndex(index: Int) {
    favoritesCategoryIndex = index
  }
}

/**
 * Remembers an instance of [CappajvTvUiState] using a navigation
 * host controller.
 *
 * @author marlonlom
 *
 * @param navHostController
 */
@Composable
fun rememberCappajvAppState(
  navHostController: NavHostController = rememberNavController()
) = remember(navHostController) { CappajvTvUiState(navHostController) }

