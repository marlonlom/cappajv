/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.marlonlom.cappajv.tv.ui.navigation.CappajvTvScreen
import kotlinx.coroutines.flow.map

/**
 * Cappajv Application ui state class.
 *
 * @author marlonlom
 *
 * @property navHostController Navigation host controller.
 */
class CappajvAppState(
  val navHostController: NavHostController
) {

  val currentRouteFlow = navHostController.currentBackStackEntryFlow.map {
    it.destination.route
  }

  fun navigateToHome() {
    navHostController.navigate(CappajvTvScreen.Home)
  }

  fun navigateToFavorites() {
    navHostController.navigate(CappajvTvScreen.Favorites)
  }

  fun navigateToSettings() {
    navHostController.navigate(CappajvTvScreen.Settings)
  }

  fun showCatalogDetails(itemId: String) {
    navHostController.navigate(CappajvTvScreen.Detail(itemId))
  }

  fun backToHome() {
    navHostController.popBackStack()
    navigateToHome()
  }
}

/**
 * Remembers an instance of [CappajvAppState] using a navigation
 * host controller.
 *
 * @author marlonlom
 *
 * @param navHostController
 */
@Composable
fun rememberCappajvAppState(
  navHostController: NavHostController = rememberNavController()
) = remember(navHostController) { CappajvAppState(navHostController) }

