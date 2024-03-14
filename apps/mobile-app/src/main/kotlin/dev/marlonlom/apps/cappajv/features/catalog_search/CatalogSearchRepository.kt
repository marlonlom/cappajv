/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_search

import dev.marlonlom.apps.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItemTuple
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * Catalog search repository.
 *
 * @author marlonlom
 *
 * @property localDataSource Local data source.
 * @property coroutineDispatcher Coroutine dispatcher for this repository.
 */
class CatalogSearchRepository(
  private val localDataSource: LocalDataSource,
  private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

  /**
   * Perform search using provided text.
   *
   * @param searchText Query text.
   */
  suspend fun performSearch(
    searchText: String
  ): Flow<List<CatalogItemTuple>> = withContext(coroutineDispatcher) {
    localDataSource.searchProducts("%$searchText%")
  }
}
