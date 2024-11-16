/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation
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
   *
   * @return Found product item points list, or empty list.
   */
  @Query("SELECT * FROM catalog_punctuation WHERE catalogItemId = :productId ")
  fun findByProduct(productId: Long): Flow<List<CatalogPunctuation>>

  /**
   * Upsert product item punctuations.
   *
   * @param punctuations product item points as typed array.
   */
  @Upsert
  fun insertAll(vararg punctuations: CatalogPunctuation)

  /**
   * Deletes all product item points in local storage.
   *
   * @param productId Product item id.
   */
  @Query("DELETE FROM catalog_punctuation WHERE id = :productId")
  fun delete(productId: Long)

  /**
   * Deletes all product items in local storage.
   */
  @Query("DELETE FROM catalog_punctuation")
  fun deleteAll()
}
