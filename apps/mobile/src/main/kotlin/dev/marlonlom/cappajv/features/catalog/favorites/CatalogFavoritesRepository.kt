/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.catalog.favorites

import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Catalog favorites repository.
 *
 * @author marlonlom
 *
 * @property localDataSource Local data source.
 */
class CatalogFavoritesRepository(
  private val localDataSource: LocalDataSource,
) {

  /** Returns Favorite catalog items list as flow. */
  val favoritesListFlow: Flow<List<CatalogItemTuple>> = localDataSource.getFavorites().map { f ->
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
  }

  /**
   * Deletes a catalog item marked as favorite, using its provided id.
   *
   * @param catalogId Catalog item id.
   */
  suspend fun deleteFavorite(catalogId: Long) = localDataSource.deleteFavorite(catalogId)
}
