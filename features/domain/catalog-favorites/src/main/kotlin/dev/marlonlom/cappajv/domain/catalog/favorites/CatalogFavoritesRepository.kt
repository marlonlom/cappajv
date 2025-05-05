/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.domain.catalog.favorites

import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import kotlinx.coroutines.flow.map

/**
 * Catalog favorites repository.
 *
 * @author marlonlom
 *
 * @property localDataSource Local data source.
 */
class CatalogFavoritesRepository(private val localDataSource: LocalDataSource) {

  /**
   * Retrieves the list of favorite catalog items from the local data source,
   * transforms them into a list of [CatalogItemTuple], groups them by category,
   * and emits an appropriate [CatalogFavoritesUiState].
   *
   * This is a Flow-based asynchronous operation.
   *
   * @return A Flow emitting [CatalogFavoritesUiState] representing either a successful state
   * with data grouped by category, or an empty state if no favorites are present.
   */
  val favoritesList = localDataSource.getFavorites()
    .map { f ->
      f.map {
        CatalogItemTuple(
          it.id,
          it.title,
          it.picture,
          it.category,
          it.samplePunctuation,
          it.punctuationsCount,
        )
      }
    }.map { tuples ->
      if (tuples.isNotEmpty()) {
        CatalogFavoritesUiState.Success(tuples.groupBy { it.category })
      } else {
        CatalogFavoritesUiState.Empty
      }
    }

  /**
   * Removes a catalog item from the favorites list.
   *
   * @param catalogId The unique identifier of the catalog item to be removed from favorites.
   */
  suspend fun undoFavorite(catalogId: Long) = localDataSource.deleteFavorite(catalogId)
}
