/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.home

import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple

/**
 * Catalog home ui state sealed class.
 *
 * @author marlonlom
 */
sealed class CatalogHomeUiState {
  /** Catalog ui state as loading state object. */
  data object Loading : CatalogHomeUiState()

  /** Catalog ui state as empty results object. */
  data object Empty : CatalogHomeUiState()

  /**
   * Catalog ui state as non empty list results data class.
   *
   * @property catalogMap Grouped catalog items.
   */
  data class Listing(
    val catalogMap: Map<String, List<CatalogItemTuple>>,
  ) : CatalogHomeUiState()
}
