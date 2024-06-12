/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.catalog_favorites

import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple

/**
 * Catalog favorites ui state.
 *
 * @author marlonlom
 */
sealed class CatalogFavoritesUiState {

  /**
   * Empty results phase of catalog favorites ui state.
   *
   * @author marlonlom
   */
  data object Empty : CatalogFavoritesUiState()

  /**
   * Fetching phase of catalog favorites ui state.
   *
   * @author marlonlom
   */
  data object Fetching : CatalogFavoritesUiState()

  /**
   * Success result phase of catalog favorites ui state.
   *
   * @author marlonlom
   *
   * @property results
   */
  data class Success(val results: List<CatalogItemTuple>) : CatalogFavoritesUiState()

}
