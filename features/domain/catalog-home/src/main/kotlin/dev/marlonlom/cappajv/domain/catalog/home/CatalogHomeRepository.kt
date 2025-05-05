/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.domain.catalog.home

import android.util.Log
import dev.marlonlom.cappajv.core.catalog.CatalogDataService
import dev.marlonlom.cappajv.core.catalog.CatalogItem
import dev.marlonlom.cappajv.core.catalog.Punctuation
import dev.marlonlom.cappajv.core.catalog.Response
import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import java.text.Normalizer
import java.util.Locale

/**
 * Repository for managing catalog data, providing access to local and remote data sources.
 *
 * @author marlonlom
 *
 * @property localDataSource The local data source for catalog items.
 * @property catalogDataService The remote data service for catalog items.
 * @property coroutineDispatcher The coroutine dispatcher for background operations (defaults to [Dispatchers.IO]).
 */
class CatalogHomeRepository(
  private val localDataSource: LocalDataSource,
  private val catalogDataService: CatalogDataService,
  private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

  /** Catalog products list as flow. */
  val allProducts: Flow<CatalogHomeUiState> = localDataSource.getAllProducts()
    .transform { tuples ->
      when {
        tuples.isNotEmpty() -> CatalogHomeUiState.Success(tuples.groupBy { it.category })
        else -> CatalogHomeUiState.Empty
      }.also { state ->
        emit(state)
      }
    }

  /** Fetches catalog items asynchronously. */
  suspend fun fetchCatalogItems() = withContext(coroutineDispatcher) {
    when (val listResponse = catalogDataService.fetchData()) {
      is Response.Success -> {
        Log.d("CatalogHomeRepository", "Saving catalog items from source to database...")
        val catalogItems: List<CatalogItem> = listResponse.data

        val productItems = catalogItems.map { it.toEntity }
        val punctuations = catalogItems.map { item ->
          item.punctuations.mapIndexed { index, punctuation ->
            punctuation.toEntity(index, item.id)
          }
        }.flatten()

        localDataSource.insertAllProducts(*productItems.toTypedArray())
        localDataSource.insertAllPunctuations(*punctuations.toTypedArray())

        Log.d("CatalogHomeRepository", "Saved catalog items from source to database...")
        Unit
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
private fun Punctuation.toEntity(index: Int, productId: Long): CatalogPunctuation = CatalogPunctuation(
  id = index.toLong() + 1,
  catalogItemId = productId,
  label = this.label,
  points = this.pointsQty.toLong(),
)

/** Returns the catalog item converted as product entity. */
private val CatalogItem.toEntity: dev.marlonlom.cappajv.core.database.entities.CatalogItem
  get() = dev.marlonlom.cappajv.core.database.entities.CatalogItem(
    id = id,
    title = title,
    slug = title.slug,
    titleNormalized = title.slug.replace("-", " "),
    picture = picture,
    category = category,
    detail = detail,
    samplePunctuation = punctuations.first().let { "${it.label}:${it.pointsQty} pts" },
    punctuationsCount = punctuations.count(),
  )

/**
 * Generates a URL-friendly slug from the String.
 *
 * This extension function normalizes the string, removes accents, converts to lowercase,
 * replaces special characters and spaces with hyphens, and removes duplicate hyphens.
 *
 * @author marlonlom
 *
 * @return The generated slug string.
 */
inline val String.slug: String
  get() = Normalizer.normalize(this, Normalizer.Form.NFD)
    .replace("\\p{Mn}+".toRegex(), "")
    .lowercase(Locale.getDefault())
    .replace("\n", " ")
    .replace("[^a-z\\d\\s]".toRegex(), " ")
    .split(" ")
    .joinToString("-")
    .replace("-+".toRegex(), "-")
