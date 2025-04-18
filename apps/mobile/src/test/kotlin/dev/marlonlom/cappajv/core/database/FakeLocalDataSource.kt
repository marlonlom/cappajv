/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.core.database

import dev.marlonlom.cappajv.core.catalog.CatalogDataService
import dev.marlonlom.cappajv.core.catalog.successOr
import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.core.database.entities.CatalogFavoriteItem
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation
import dev.marlonlom.cappajv.ui.util.slug
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import dev.marlonlom.cappajv.core.catalog.CatalogItem as RemoteCatalogItem

internal class FakeLocalDataSource(
  private val remoteDataService: CatalogDataService,
  private val localFavoriteItems: MutableList<CatalogFavoriteItem> = mutableListOf(),
) : LocalDataSource {

  override fun getAllProducts(): Flow<List<CatalogItemTuple>> {
    val listResponse = remoteDataService.fetchData().successOr(emptyList())
    return flowOf(
      listResponse.map {
        CatalogItemTuple(
          id = it.id,
          title = it.title,
          picture = it.picture,
          category = "Category one",
          samplePunctuation = "",
          punctuationsCount = 0,
        )
      },
    )
  }

  override fun findProduct(productId: Long): Flow<CatalogItem?> = flowOf(
    remoteDataService.fetchData()
      .successOr(emptyList())
      .find { it.id == productId }
      .let {
        if (it != null) {
          CatalogItem(
            id = it.id,
            title = it.title,
            slug = it.title.slug,
            titleNormalized = it.title.lowercase(),
            picture = it.picture,
            category = "Category one",
            detail = "Lorem ipsum",
            samplePunctuation = "",
            punctuationsCount = 0,
          )
        } else {
          null
        }
      },
  )

  override fun getPunctuations(productId: Long): Flow<List<CatalogPunctuation>> {
    val listResponse: RemoteCatalogItem = remoteDataService.fetchData()
      .successOr(emptyList())
      .find { it.id == productId } ?: return flowOf(emptyList())

    val units = listResponse.punctuations.mapIndexed { index, punctuation ->
      CatalogPunctuation(
        id = index.plus(1).toLong(),
        catalogItemId = listResponse.id,
        label = punctuation.label,
        points = punctuation.pointsQty.toLong(),
      )
    }
    return flowOf(units)
  }

  override fun getFavorites(): Flow<List<CatalogFavoriteItem>> = flowOf(localFavoriteItems)
  override fun searchProducts(searchText: String): Flow<List<CatalogItemTuple>> {
    val listResponse = remoteDataService.fetchData()
      .successOr(emptyList())
      .map {
        CatalogItem(
          id = it.id,
          title = it.title,
          slug = it.title.slug,
          titleNormalized = it.title.slug.replace("-", " "),
          picture = it.picture,
          category = "Category one",
          detail = "Lorem ipsum",
          samplePunctuation = "",
          punctuationsCount = it.punctuations.size - 1,
        )
      }

    val itemTuples: List<CatalogItemTuple> = listResponse.filter {
      val queryingText = searchText.lowercase().replace("%", "").trim()
      it.title.lowercase().contains(queryingText).or(
        it.titleNormalized.lowercase().contains(queryingText),
      )
    }.map {
      CatalogItemTuple(
        id = it.id,
        title = it.title,
        picture = it.picture,
        category = "Category one",
        samplePunctuation = "",
        punctuationsCount = it.punctuationsCount,
      )
    }

    return flowOf(itemTuples)
  }

  override fun insertAllProducts(vararg products: CatalogItem) = Unit

  override suspend fun insertFavoriteProduct(favoriteItem: CatalogFavoriteItem) {
    localFavoriteItems.add(favoriteItem)
  }

  override fun insertAllPunctuations(vararg punctuations: CatalogPunctuation) = Unit

  override fun deleteAllProducts() = Unit
  override fun deleteAllFavorites() {
    localFavoriteItems.clear()
  }

  override suspend fun deleteFavorite(productId: Long) {
    localFavoriteItems.removeIf { it.id == productId }
  }

  override fun deleteAllPunctuations() = Unit
  override fun isFavorite(productId: Long): Flow<Int> = flowOf(
    localFavoriteItems.count { it.id == productId },
  )
}
