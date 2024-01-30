/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.marlonlom.apps.cappajv.core.database.entities.ProductItemPoint
import kotlinx.coroutines.flow.Flow

/**
 * Catalog punctuations data access object interface definition.
 *
 * @author marlonlom
 *
 */
@Dao
interface CatalogPunctuationsDao {

  /**
   * Query for retrieving product item points list, aka punctuations list.
   *
   * @param productId Product item id.
   * @return Found product item points list, or empty list.
   */
  @Query("SELECT * FROM cappa_punctuation WHERE punctuation_product_id = :productId ")
  fun findByProduct(productId: Long): Flow<List<ProductItemPoint>>

  /**
   * Upsert product item punctuations.
   *
   * @param punctuations product item points as typed array.
   */
  @Upsert
  fun insertAll(vararg punctuations: ProductItemPoint)

  /**
   * Deletes all product item points in local storage.
   */
  @Query("DELETE FROM cappa_punctuation")
  fun deleteAll()

}
