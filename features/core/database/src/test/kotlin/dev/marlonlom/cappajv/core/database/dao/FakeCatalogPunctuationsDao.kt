/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.core.database.dao

import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Fake catalog punctuations dao implementation class.
 *
 * @author marlonlom
 *
 * @property list Mutable punctuations list.
 */
class FakeCatalogPunctuationsDao(private val list: MutableList<CatalogPunctuation> = mutableListOf()) :
  CatalogPunctuationsDao {

  override fun findByProduct(productId: Long): Flow<List<CatalogPunctuation>> =
    flowOf(list.filter { item -> item.catalogItemId == productId })

  override fun insertAll(vararg punctuations: CatalogPunctuation) {
    list.addAll(punctuations)
  }

  override fun delete(productId: Long) {
    list.removeIf { it.id == productId }
  }

  override fun deleteAll() {
    list.clear()
  }
}
