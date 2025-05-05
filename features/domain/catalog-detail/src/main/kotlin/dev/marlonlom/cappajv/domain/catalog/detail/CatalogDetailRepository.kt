/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.domain.catalog.detail

import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.core.database.entities.CatalogFavoriteItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

/**
 * Catalog details repository class.
 *
 * @author marlonlom
 *
 * @property localDataSource local data source dependency
 * @property coroutineDispatcher coroutine dispatcher
 */
class CatalogDetailRepository(
  private val localDataSource: LocalDataSource,
  private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

  /**
   * Finds and returns a [Flow] that emits the [CatalogDetailItem] associated with the given item ID.
   * Emits `null` if the item is not found.
   *
   * @param itemId The unique ID of the catalog item to retrieve.
   * @return A [Flow] that emits the [CatalogDetailItem] or `null` if no matching item exists.
   */
  fun find(itemId: Long): Flow<CatalogDetailItem?> {
    coroutineDispatcher.run {
      return combine(
        localDataSource.findProduct(itemId),
        localDataSource.isFavorite(itemId),
        localDataSource.getPunctuations(itemId),
      ) { product, isFavorite, points ->
        try {
          return@combine product?.let {
            if (product.id == -1L) {
              null
            } else {
              CatalogDetailItem(
                product = product,
                isFavorite = isFavorite > 0,
                points = points,
              )
            }
          }
        } catch (e: Exception) {
          return@combine null
        }
      }
    }
  }

  /**
   * Inserts a catalog item marked as favorite.
   *
   * @param favoriteItem Catalog favorite item to be saved.
   */
  suspend fun saveFavorite(favoriteItem: CatalogFavoriteItem) = localDataSource.insertFavoriteProduct(favoriteItem)

  /**
   * Deletes a catalog item marked as favorite, using its provided id.
   *
   * @param catalogId Catalog item id.
   */
  suspend fun undoFavorite(catalogId: Long) = localDataSource.deleteFavorite(catalogId)
}
