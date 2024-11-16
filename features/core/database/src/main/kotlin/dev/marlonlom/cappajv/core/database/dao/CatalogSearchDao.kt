/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import kotlinx.coroutines.flow.Flow

/**
 * Catalog product search data access object interface definition.
 *
 * @author marlonlom
 *
 */
@Dao
interface CatalogSearchDao {

  /**
   * Query for retrieving product items list by provided text.
   *
   * @return Product items list, or empty list, as Flow.
   */
  @Query(
    "SELECT c.id, c.title, c.category, c.picture, c.samplePunctuation, c.punctuationsCount " +
      "FROM catalog_item c WHERE LOWER(c.titleNormalized) LIKE :searchText " +
      "OR LOWER(c.title) LIKE :searchText ",
  )
  fun searchProducts(searchText: String): Flow<List<CatalogItemTuple>>
}
