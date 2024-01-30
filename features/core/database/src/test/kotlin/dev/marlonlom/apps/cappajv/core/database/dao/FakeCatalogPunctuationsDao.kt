/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database.dao

import dev.marlonlom.apps.cappajv.core.database.entities.ProductItemPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Fake catalog punctuations dao implementation class.
 *
 * @author marlonlom
 *
 * @property list Mutable punctuations list.
 */
class FakeCatalogPunctuationsDao(
  private val list: MutableList<ProductItemPoint> = mutableListOf()
) : CatalogPunctuationsDao {

  override fun findByProduct(productId: Long): Flow<List<ProductItemPoint>> =
    flowOf(list.filter { item -> item.productId == productId })

  override fun insertAll(vararg punctuations: ProductItemPoint) {
    list.addAll(punctuations)
  }

  override fun deleteAll() {
    list.clear()
  }
}
