/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.domain.catalog.home

import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple

/**
 * Represents the UI state for the home screen of the catalog feature.
 *
 * @author marlonlom
 */
sealed class CatalogHomeUiState {
  /**
   * Catalog ui state as loading state object.
   * @author marlonlom
   */
  data object Loading : CatalogHomeUiState()

  /**
   * Catalog ui state as empty results object.
   * @author marlonlom
   */
  data object Empty : CatalogHomeUiState()

  /**
   * Catalog ui state as non empty list results data class.
   * @author marlonlom
   *
   * @property catalogMap Grouped catalog items.
   */
  data class Success(val catalogMap: Map<String, List<CatalogItemTuple>>) : CatalogHomeUiState()
}
