/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database.dao

import dev.marlonlom.apps.cappajv.core.database.entities.CatalogFavoriteItem
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
  private val list: MutableList<CatalogFavoriteItem> = mutableListOf()
) : CatalogFavoriteItemsDao {

  override fun getFavoriteItems(): Flow<List<CatalogFavoriteItem>> = flowOf(list)

  override fun insertAll(vararg products: CatalogFavoriteItem) {
    list.addAll(products)
  }

  override fun deleteAll() {
    list.clear()
  }

  override fun delete(productId: Long) {
    list.removeIf { it.id == productId }
  }
}
