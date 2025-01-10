/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.catalog.search

import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple

/**
 * Catalog search ui state.
 *
 * @author marlonlom
 */
sealed class CatalogSearchUiState {

  /**
   * Default catalog search ui state.
   *
   * @author marlonlom
   *
   */
  data object None : CatalogSearchUiState()

  /**
   * Searching phase of catalog search ui state.
   *
   * @author marlonlom
   *
   */
  data object Searching : CatalogSearchUiState()

  /**
   * Empty results phase of catalog search ui state.
   *
   * @author marlonlom
   *
   */
  data object Empty : CatalogSearchUiState()

  /**
   * Success result phase of catalog search ui state.
   *
   * @author marlonlom
   *
   * @property results Catalog tuples result list.
   */
  data class Success(val results: List<CatalogItemTuple>) : CatalogSearchUiState()
}
