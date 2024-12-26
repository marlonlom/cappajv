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
sealed class TvScreen(
  val route: String
) {

  /**
   * Catalog listings tv screen object.
   *
   * @author marlonlom
   *
   */
  @Serializable
  data object Listings : TvScreen("listings")

  /**
   * Catalog settings tv screen object.
   *
   * @author marlonlom
   *
   */
  @Serializable
  data object Settings : TvScreen("settings")


  /**
   * Catalog detail tv screen.
   *
   * @property itemId Catalog item id.
   */
  @Serializable
  data class Details(
    private val itemId: String
  ) : TvScreen("details/$itemId")
}
