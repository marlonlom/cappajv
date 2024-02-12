/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list

import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItemTuple

/**
 * Catalog ui state sealed class.
 *
 * @author marlonlom
 */
sealed class CatalogListState {
  /**
   * Catalog ui state as loading state object.
   */
  data object Loading : CatalogListState()

  /**
   * Catalog ui state as empty results object.
   */
  data object Empty : CatalogListState()

  /**
   * Catalog ui state as non empty list results data class.
   *
   * @property map Grouped catalog items map.
   */
  data class Listing(val map: Map<String, List<CatalogItemTuple>>) : CatalogListState()
}
