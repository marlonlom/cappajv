/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.catalog.detail

import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.core.database.entities.CatalogFavoriteItem
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

/**
 * Data class definition for catalog item detail.
 *
 * @author marlonlom
 *
 * @property product Catalog product detail
 * @property isFavorite True/False if catalog product is marked as favorite.
 * @property points Catalog product points list
 */
data class CatalogDetail(
  val product: CatalogItem,
  val isFavorite: Boolean,
  val points: List<CatalogPunctuation>,
)

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

  fun find(itemId: Long): Flow<CatalogDetail?> {
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
              CatalogDetail(
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
  suspend fun deleteFavorite(catalogId: Long) = localDataSource.deleteFavorite(catalogId)
}
