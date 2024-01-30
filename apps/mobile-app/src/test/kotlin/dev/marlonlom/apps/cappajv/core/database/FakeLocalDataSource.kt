/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package  dev.marlonlom.apps.cappajv.core.database

import dev.marlonlom.apps.cappajv.core.catalog_source.CatalogDataService
import dev.marlonlom.apps.cappajv.core.catalog_source.CatalogItem
import dev.marlonlom.apps.cappajv.core.catalog_source.successOr
import dev.marlonlom.apps.cappajv.core.database.entities.ProductItem
import dev.marlonlom.apps.cappajv.core.database.entities.ProductItemPoint
import dev.marlonlom.apps.cappajv.ui.util.slug
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class FakeLocalDataSource(
  private val remoteDataService: CatalogDataService
) : LocalDataSource {

  override fun getAllProducts(): Flow<List<ProductItem>> {
    val listResponse = remoteDataService.fetchData().successOr(emptyList())
    return flowOf(listResponse.map {
      ProductItem(
        id = it.id,
        title = it.title,
        slug = it.title.slug,
        titleNormalized = it.title.lowercase(),
        picture = it.picture,
        category = "Category one",
        detail = "Lorem ipsum"
      )
    })
  }

  override fun findProduct(productId: Long): Flow<ProductItem> {
    val listResponse: ProductItem = remoteDataService.fetchData()
      .successOr(emptyList())
      .find { it.id == productId }
      .let {
        if (it != null) ProductItem(
          id = it.id,
          title = it.title,
          slug = it.title.slug,
          titleNormalized = it.title.lowercase(),
          picture = it.picture,
          category = "Category one",
          detail = "Lorem ipsum"
        ) else NONE
      }
    return flowOf(listResponse)
  }

  override fun getPunctuations(productId: Long): Flow<List<ProductItemPoint>> {
    val listResponse: CatalogItem = remoteDataService.fetchData()
      .successOr(emptyList())
      .find { it.id == productId } ?: return flowOf(emptyList())

    val units = listResponse.punctuations.mapIndexed { index, punctuation ->
      ProductItemPoint(
        id = index.plus(1).toLong(),
        productId = listResponse.id,
        label = punctuation.label,
        points = punctuation.pointsQty.toLong()
      )
    }
    return flowOf(units)
  }

  override fun insertAllProducts(vararg products: ProductItem) = Unit
  override fun insertAllPunctuations(vararg punctuations: ProductItemPoint) = Unit

  override fun deleteAllProducts() = Unit

  override fun deleteAllPunctuations() = Unit

  companion object {
    val NONE = ProductItem(-1, "", "", "", "", "", "")
  }
}
