/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database.dao

import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Fake catalog products dao implementation class.
 *
 * @author marlonlom
 *
 * @property list Mutable punctuations list.
 */
internal class FakeCatalogProductsDao(
  private val list: MutableList<CatalogItem> = mutableListOf()
) : CatalogProductsDao {

  override fun getProducts(): Flow<List<CatalogItem>> = flowOf(list)

  override fun findProduct(productId: Long): Flow<CatalogItem?> =
    flowOf(list.find { item -> item.id == productId })

  override fun insertAll(vararg products: CatalogItem) {
    list.addAll(products)
  }

  override fun deleteAll() {
    list.clear()
  }
}
