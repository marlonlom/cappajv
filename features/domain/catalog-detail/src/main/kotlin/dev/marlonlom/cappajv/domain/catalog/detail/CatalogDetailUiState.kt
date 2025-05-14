/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.domain.catalog.detail

/**
 * Represents the UI states for displaying details of a catalog item.
 *
 * @author marlonlom
 */
sealed class CatalogDetailUiState {
  /**
   * Indicates that the requested catalog item was not found.
   *
   * @author marlonlom
   */
  data object NotFound : CatalogDetailUiState()

  /**
   * Indicates that the details for the catalog item are currently being loaded.
   *
   * @author marlonlom
   */
  data object Loading : CatalogDetailUiState()

  /**
   * Indicates that the details for the catalog item were successfully found.
   *
   * @author marlonlom
   *
   * @property detail The [CatalogDetailItem] containing the item's details.
   */
  data class Found(val detail: CatalogDetailItem) : CatalogDetailUiState()
}
