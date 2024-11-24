/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.ui.navigation

import kotlinx.serialization.Serializable

/**
 * Application tv screens sealed interface definition.
 *
 * @author marlonlom
 *
 * @property route Screen route.
 */
@Serializable
sealed class CappajvTvScreen(
  val route: String
) {

  /**
   * Catalog Home tv screen object.
   *
   * @author marlonlom
   *
   */
  @Serializable
  data object Home : CappajvTvScreen("home")

  /**
   * Catalog favorites tv screen object.
   *
   * @author marlonlom
   *
   */
  @Serializable
  data object Favorites : CappajvTvScreen("favorites")

  /**
   * Catalog settings tv screen object.
   *
   * @author marlonlom
   *
   */
  @Serializable
  data object Settings : CappajvTvScreen("settings")


  /**
   * Catalog detail tv screen.
   *
   * @property itemId Catalog item id.
   */
  @Serializable
  data class Detail(
    private val itemId: String
  ) : CappajvTvScreen("details/$itemId")
}
