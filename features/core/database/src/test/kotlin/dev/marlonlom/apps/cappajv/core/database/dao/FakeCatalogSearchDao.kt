/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database.dao

import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItemTuple
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.Locale

/**
 * Fake catalog products search dao implementation class.
 *
 * @author marlonlom
 *
 * @property list Mutable punctuations list.
 */
internal class FakeCatalogSearchDao(
  private val list: MutableList<CatalogItem> = mutableListOf()
) : CatalogSearchDao {

  override fun searchProducts(
    searchText: String
  ): Flow<List<CatalogItemTuple>> = flowOf(
    list
      .filter {
        val searchingText = searchText.lowercase(Locale.getDefault())
        it.titleNormalized.lowercase().contains(searchingText).or(it.title.lowercase().contains(searchingText))
      }
      .map {
        CatalogItemTuple(
          it.id,
          it.title,
          it.picture,
          it.category,
          it.samplePunctuation,
          it.punctuationsCount
        )
      }
  )

  internal fun insertAll(vararg products: CatalogItem) {
    list.addAll(products)
  }

  internal fun deleteAll() {
    list.clear()
  }
}
