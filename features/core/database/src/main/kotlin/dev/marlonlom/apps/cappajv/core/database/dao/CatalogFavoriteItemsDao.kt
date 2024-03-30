/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogFavoriteItem
import kotlinx.coroutines.flow.Flow

/**
 * Catalog favorite products data access object interface definition.
 *
 * @author marlonlom
 *
 */
@Dao
interface CatalogFavoriteItemsDao {

  /**
   * Query for retrieving product items list.
   *
   * @return Product items list, or empty list, as Flow.
   */
  @Query("SELECT f.* FROM catalog_item_favorite f")
  fun getFavoriteItems(): Flow<List<CatalogFavoriteItem>>

  /**
   * Inserts catalog product item.
   *
   * @param product Catalog product item.
   */
  @Insert
  suspend fun insert(product: CatalogFavoriteItem)

  /**
   * Deletes all product items in local storage.
   */
  @Query("DELETE FROM catalog_item_favorite")
  fun deleteAll()

  /**
   * Deletes favorite product item in local storage using its item id.
   *
   * @param productId Product item id.
   */
  @Query("DELETE FROM catalog_item_favorite WHERE id = :productId")
  suspend fun delete(productId: Long)

  /**
   * Returns 1 if a product with the provided ID exists as a favorite, otherwise returns 0.
   *
   * @param productId Product item id.
   * @return Number that indicates if product id exists as favorite, as Flow.
   */
  @Query("SELECT COUNT(f.id) FROM catalog_item_favorite f WHERE f.id = :productId")
  fun isFavorite(productId: Long): Flow<Int>

}
