/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.catalog_list

import dev.marlonlom.cappajv.core.catalog.CatalogDataService
import dev.marlonlom.cappajv.core.catalog.Punctuation
import dev.marlonlom.cappajv.core.catalog.Response
import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation
import dev.marlonlom.cappajv.ui.util.slug
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import timber.log.Timber
import dev.marlonlom.cappajv.core.catalog.CatalogItem as RemoteCatalogItem

/**
 * Catalog list repository class
 *
 * @author marlonlom
 *
 * @property localDataSource Local data source.
 * @property catalogDataService Catalog data service.
 * @property coroutineDispatcher Coroutine dispatcher for this repository.
 */
class CatalogListRepository(
  private val localDataSource: LocalDataSource,
  private val catalogDataService: CatalogDataService,
  private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

  /** Catalog products list as flow. */
  val allProducts: Flow<CatalogListUiState> = localDataSource.getAllProducts()
    .transform { tuples ->
      when {
        tuples.isNotEmpty() -> CatalogListUiState.Listing(tuples.groupBy { it.category })
        else -> CatalogListUiState.Empty
      }.also { state ->
        emit(state)
      }
    }

  /**
   * Fetch catalog items from source.
   */
  internal suspend fun fetchCatalogItems() = withContext(coroutineDispatcher) {
    when (val listResponse = catalogDataService.fetchData()) {
      is Response.Success -> {
        Timber.d("[CatalogListRepository.fetchCatalogItems] Saving catalog items from source to database...")
        val catalogItems: List<RemoteCatalogItem> = listResponse.data

        val productItems = catalogItems.map { it.toEntity }
        val punctuations = catalogItems.map { item ->
          item.punctuations.mapIndexed { index, punctuation ->
            punctuation.toEntity(index, item.id)
          }
        }.flatten()

        localDataSource.insertAllProducts(*productItems.toTypedArray())
        localDataSource.insertAllPunctuations(*punctuations.toTypedArray())

        Timber.d("[CatalogListRepository.fetchCatalogItems] Saved catalog items from source to database...")
      }

      else -> Unit
    }
  }

}

/**
 * Returns the catalog item punctuation converted as product entity.
 *
 * @param index Index for consecutive id.
 * @param productId Product id.
 */
private fun Punctuation.toEntity(
  index: Int,
  productId: Long,
): CatalogPunctuation = CatalogPunctuation(
  id = index.toLong() + 1,
  catalogItemId = productId,
  label = this.label,
  points = this.pointsQty.toLong()
)

/** Returns the catalog item converted as product entity. */
private val RemoteCatalogItem.toEntity: CatalogItem
  get() = CatalogItem(
    id = id,
    title = title,
    slug = title.slug,
    titleNormalized = title.slug.replace("-", " "),
    picture = picture,
    category = category,
    detail = detail,
    samplePunctuation = punctuations.first().let { "${it.label}:${it.pointsQty} pts" },
    punctuationsCount = punctuations.count()
  )
