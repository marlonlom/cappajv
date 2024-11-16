/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.core.database.dao

import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Fake catalog products dao implementation class.
 *
 * @author marlonlom
 *
 * @property list Mutable punctuations list.
 */
internal class FakeCatalogItemsDao(
  private val list: MutableList<CatalogItem> = mutableListOf(),
) : CatalogItemsDao {

  override fun getProducts(): Flow<List<CatalogItemTuple>> = flowOf(
    list.map {
      CatalogItemTuple(it.id, it.title, it.picture, it.category, it.samplePunctuation, it.punctuationsCount)
    },
  )

  override fun findProduct(productId: Long): Flow<CatalogItem?> = flowOf(list.find { item -> item.id == productId })

  override fun insertAll(vararg products: CatalogItem) {
    list.addAll(products)
  }

  override fun deleteAll() {
    list.clear()
  }
}
