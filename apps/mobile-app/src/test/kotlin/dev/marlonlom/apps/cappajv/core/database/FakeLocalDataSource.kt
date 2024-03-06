/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package  dev.marlonlom.apps.cappajv.core.database

import dev.marlonlom.apps.cappajv.core.catalog_source.CatalogDataService
import dev.marlonlom.apps.cappajv.core.catalog_source.successOr
import dev.marlonlom.apps.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogFavoriteItem
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogPunctuation
import dev.marlonlom.apps.cappajv.ui.util.slug
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import dev.marlonlom.apps.cappajv.core.catalog_source.CatalogItem as RemoteCatalogItem

internal class FakeLocalDataSource(
  private val remoteDataService: CatalogDataService,
  private val localFavoriteItems: MutableList<CatalogFavoriteItem> = mutableListOf()
) : LocalDataSource {

  override fun getAllProducts(): Flow<List<CatalogItemTuple>> {
    val listResponse = remoteDataService.fetchData().successOr(emptyList())
    return flowOf(listResponse.map {
      CatalogItemTuple(
        id = it.id,
        title = it.title,
        picture = it.picture,
        category = "Category one",
        samplePunctuation = "",
        punctuationsCount = 0,
      )
    })
  }

  override fun findProduct(productId: Long): Flow<CatalogItem> {
    val listResponse = remoteDataService.fetchData()
      .successOr(emptyList())
      .find { it.id == productId }
      .let {
        if (it != null) CatalogItem(
          id = it.id,
          title = it.title,
          slug = it.title.slug,
          titleNormalized = it.title.lowercase(),
          picture = it.picture,
          category = "Category one",
          detail = "Lorem ipsum",
          samplePunctuation = "",
          punctuationsCount = 0,
        ) else NONE
      }
    return flowOf(listResponse)
  }

  override fun getPunctuations(productId: Long): Flow<List<CatalogPunctuation>> {
    val listResponse: RemoteCatalogItem = remoteDataService.fetchData()
      .successOr(emptyList())
      .find { it.id == productId } ?: return flowOf(emptyList())

    val units = listResponse.punctuations.mapIndexed { index, punctuation ->
      CatalogPunctuation(
        id = index.plus(1).toLong(),
        catalogItemId = listResponse.id,
        label = punctuation.label,
        points = punctuation.pointsQty.toLong()
      )
    }
    return flowOf(units)
  }

  override fun getFavorites(): Flow<List<CatalogFavoriteItem>> = flowOf(localFavoriteItems)

  override fun insertAllProducts(vararg products: CatalogItem) = Unit

  override fun insertAllFavoriteProducts(vararg favoriteItems: CatalogFavoriteItem) {
    localFavoriteItems.addAll(favoriteItems)
  }

  override fun insertAllPunctuations(vararg punctuations: CatalogPunctuation) = Unit

  override fun deleteAllProducts() = Unit
  override fun deleteAllFavorites() {
    localFavoriteItems.clear()
  }

  override fun deleteFavorite(productId: Long) {
    localFavoriteItems.removeIf { it.id == productId }
  }

  override fun deleteAllPunctuations() = Unit

  companion object {
    val NONE = CatalogItem(
      id = -1,
      title = "",
      slug = "",
      titleNormalized = "",
      picture = "",
      category = "",
      detail = "",
      samplePunctuation = "",
      punctuationsCount = 0
    )
  }
}
