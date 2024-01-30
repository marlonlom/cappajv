/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list

import dev.marlonlom.apps.cappajv.core.catalog_source.CatalogDataService
import dev.marlonlom.apps.cappajv.core.catalog_source.CatalogItem
import dev.marlonlom.apps.cappajv.core.catalog_source.Punctuation
import dev.marlonlom.apps.cappajv.core.catalog_source.Response
import dev.marlonlom.apps.cappajv.core.database.LocalDataSource
import dev.marlonlom.apps.cappajv.core.database.entities.ProductItem
import dev.marlonlom.apps.cappajv.core.database.entities.ProductItemPoint
import dev.marlonlom.apps.cappajv.features.catalog_list.CatalogListState.Empty
import dev.marlonlom.apps.cappajv.features.catalog_list.CatalogListState.Listing
import dev.marlonlom.apps.cappajv.ui.util.slug
import dev.marlonlom.apps.cappajv.ui.util.toSentenceCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
 * Catalog list repository class
 *
 * @author marlonlom
 *
 * @property localDataSource Local data source.
 * @property catalogDataService Catalog data service.
 */
class CatalogListRepository(
  private val localDataSource: LocalDataSource,
  private val catalogDataService: CatalogDataService,
  private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

  /** Catalog products list as flow. */
  val allProducts: Flow<CatalogListState> = flow {

    val allProducts: List<ProductItem> = localDataSource.getAllProducts().first()
    if (allProducts.isEmpty()) {
      when (val listResponse = catalogDataService.fetchData()) {

        is Response.Success -> {

          val catalogItems: List<CatalogItem> = listResponse.data

          val productItems = catalogItems.map { it.toEntity }
          val punctuations: List<ProductItemPoint> = catalogItems.map { item ->
            item.punctuations.mapIndexed { index, punctuation ->
              punctuation.toEntity(index, item.id)
            }
          }.flatten()

          emit(Listing(productItems.groupBy { it.category }))

          /* get a way to save the items before emitting the data.
          saveCatalogToDatabase(productItems, punctuations)
          */
        }

        is Response.Failure -> emit(Empty)
      }
    } else {
      emit(Listing(allProducts.groupBy { it.category }))
    }

  }

  private fun saveCatalogToDatabase(
    productItems: List<ProductItem>,
    punctuations: List<ProductItemPoint>
  ) {
    coroutineDispatcher.run {
      localDataSource.insertAllProducts(*productItems.toTypedArray())
      localDataSource.insertAllPunctuations(*punctuations.toTypedArray())
    }
  }
}

/**
 * Returns the catalog item punctuation converted as product entity.
 *
 * @param index Index for consecutive id.
 * @param productId Product id.
 */
private fun Punctuation.toEntity(index: Int, productId: Long): ProductItemPoint =
  ProductItemPoint(id = index.toLong() + 1, productId = productId, label = this.label, points = this.pointsQty.toLong())

/** Returns the catalog item converted as product entity. */
private val CatalogItem.toEntity: ProductItem
  get() = ProductItem(id, title, title.slug, title.toSentenceCase, picture, category, detail)
