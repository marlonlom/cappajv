/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail

import dev.marlonlom.apps.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogPunctuation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

/**
 * Data class definition for catalog item detail.
 *
 * @property product product detail
 * @property points product points list
 */
data class CatalogDetail(
  val product: CatalogItem,
  val points: List<CatalogPunctuation>
)

/**
 * Catalog details repository class.
 *
 * @property localDataSource local data source dependency
 * @property coroutineDispatcher coroutine dispatcher
 */
class CatalogDetailRepository(
  private val localDataSource: LocalDataSource,
  private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

  fun find(itemId: Long): Flow<CatalogDetail?> {
    coroutineDispatcher.run {
      return combine(
        localDataSource.findProduct(itemId),
        localDataSource.getPunctuations(itemId)
      ) { product, points ->
        try {
          return@combine product?.let {
            if (product.id == -1L) {
              null
            } else {
              CatalogDetail(product, points)
            }
          }
        } catch (e: Exception) {
          return@combine null
        }
      }
    }
  }

}
