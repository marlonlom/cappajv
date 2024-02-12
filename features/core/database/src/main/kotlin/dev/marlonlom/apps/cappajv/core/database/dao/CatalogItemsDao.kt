/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItem
import kotlinx.coroutines.flow.Flow

/**
 * Catalog products data access object interface definition.
 *
 * @author marlonlom
 *
 */
@Dao
interface CatalogProductsDao {

  /**
   * Query for retrieving product items list.
   *
   * @return Product items list, or empty list, as Flow.
   */
  @Query("SELECT * FROM cappa_product")
  fun getProducts(): Flow<List<CatalogItem>>

  /**
   * Query for retrieving flow with single product item using its id.
   *
   * @param productId Product item id.
   * @return Found product item, or null.
   */
  @Query("SELECT * FROM cappa_product WHERE product_id = :productId ")
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
  @Query("DELETE FROM cappa_product")
  fun deleteAll()

}
