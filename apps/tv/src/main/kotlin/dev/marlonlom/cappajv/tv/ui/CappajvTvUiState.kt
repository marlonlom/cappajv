/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
class CappajvAppState(
  val navHostController: NavHostController
) {

  var browseMenuIndex: Int = -1

  val detailId: Long = -1L

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
    navHostController.navigate(TvScreenRoute.Detail(itemId))
  }

  fun backToHome() {
    navHostController.popBackStack()
  }

  fun changeBrowseMenuIndex(ordinal: Int) {
    browseMenuIndex = ordinal
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

