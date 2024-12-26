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
sealed class TvScreenRoute(
  val route: String
) {

  /**
   * Catalog listings tv screen route object.
   *
   * @author marlonlom
   *
   */
  @Serializable
  data object Catalog : TvScreenRoute("catalog")

  /**
   * Catalog settings tv screen route object.
   *
   * @author marlonlom
   *
   */
  @Serializable
  data object Settings : TvScreenRoute("settings")


  /**
   * Catalog detail tv screen route data class.
   *
   * @property itemId Catalog item id.
   */
  @Serializable
  data class Detail(
    val itemId: String
  ) : TvScreenRoute("details/$itemId")
}
