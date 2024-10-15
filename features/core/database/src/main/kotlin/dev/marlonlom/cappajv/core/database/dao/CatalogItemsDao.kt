/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import kotlinx.coroutines.flow.Flow

/**
 * Catalog products data access object interface definition.
 *
 * @author marlonlom
 *
 */
@Dao
interface CatalogItemsDao {

  /**
   * Query for retrieving product items list.
   *
   * @return Product items list, or empty list, as Flow.
   */
  @Query("SELECT c.id, c.title, c.category, c.picture, c.samplePunctuation, c.punctuationsCount FROM catalog_item c")
  fun getProducts(): Flow<List<CatalogItemTuple>>

  /**
   * Query for retrieving flow with single product item using its id.
   *
   * @param productId Product item id.
   * @return Found product item, or null.
   */
  @Query("SELECT * FROM catalog_item WHERE id = :productId ")
  fun findProduct(productId: Long): Flow<CatalogItem?>

  /**
   * Upsert product items.
   *
   * @param products product items as typed array.
   */
  @Upsert
  fun insertAll(vararg products: CatalogItem)

  /**
   * Deletes all product items in local storage.
   */
  @Query("DELETE FROM catalog_item")
  fun deleteAll()

}
