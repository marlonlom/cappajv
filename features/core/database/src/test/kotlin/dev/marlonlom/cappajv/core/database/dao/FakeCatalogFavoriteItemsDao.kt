/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.core.database.dao

import dev.marlonlom.cappajv.core.database.entities.CatalogFavoriteItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Fake catalog products dao implementation class.
 *
 * @author marlonlom
 *
 * @property list Mutable punctuations list.
 */
internal class FakeCatalogFavoriteItemsDao(
  private val list: MutableList<CatalogFavoriteItem> = mutableListOf(),
) : CatalogFavoriteItemsDao {

  override fun getFavoriteItems(): Flow<List<CatalogFavoriteItem>> = flowOf(list)

  override suspend fun insert(product: CatalogFavoriteItem) {
    list.add(product)
  }

  override fun deleteAll() {
    list.clear()
  }

  override suspend fun delete(productId: Long) {
    list.removeIf { it.id == productId }
  }

  override fun isFavorite(productId: Long): Flow<Int> = flowOf(list.count { it.id == productId })
}
