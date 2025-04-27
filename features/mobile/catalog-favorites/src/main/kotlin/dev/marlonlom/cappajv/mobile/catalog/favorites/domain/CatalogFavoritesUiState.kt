/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.favorites.domain

import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple

/**
 * Represents the UI state for catalog favorites.
 *
 * This sealed class is used to model different UI states when working with
 * the favorites section in a catalog.
 *
 * @author marlonlom
 */
sealed class CatalogFavoritesUiState {

  /**
   * Represents an empty state, when there are no favorite items available.
   * @author marlonlom
   */
  data object Empty : CatalogFavoritesUiState()

  /**
   * Represents a loading state while the favorite items are being fetched.
   * @author marlonlom
   */
  data object Loading : CatalogFavoritesUiState()

  /**
   * Represents a successful state with a map of favorite catalog items.
   * @author marlonlom
   *
   * @property catalogMap A map where the key is a category or identifier (e.g., section name),
   * and the value is a list of [CatalogItemTuple] objects belonging to that category.
   */
  data class Success(val catalogMap: Map<String, List<CatalogItemTuple>>) : CatalogFavoritesUiState()
}
