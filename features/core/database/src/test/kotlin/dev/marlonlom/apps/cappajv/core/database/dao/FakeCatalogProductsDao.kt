/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database.dao

import dev.marlonlom.apps.cappajv.core.database.entities.ProductItem
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
  private val list: MutableList<ProductItem> = mutableListOf()
) : CatalogProductsDao {

  override fun getProducts(): Flow<List<ProductItem>> = flowOf(list)

  override fun findProduct(productId: Long): Flow<ProductItem?> =
    flowOf(list.find { item -> item.id == productId })

  override fun insertAll(vararg products: ProductItem) {
    list.addAll(products)
  }

  override fun deleteAll() {
    list.clear()
  }
}
