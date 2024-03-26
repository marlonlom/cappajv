/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database.dao

import androidx.room.Dao
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
   * Upsert product items.
   *
   * @param products product items as typed array.
   */
  @Upsert
  fun insertAll(vararg products: CatalogFavoriteItem)

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

}
