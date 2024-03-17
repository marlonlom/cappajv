/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_search

import dev.marlonlom.apps.cappajv.core.database.datasource.LocalDataSource

/**
 * Catalog search repository.
 *
 * @author marlonlom
 *
 * @property localDataSource Local data source.
 */
class CatalogSearchRepository(
  private val localDataSource: LocalDataSource,
) {

  /**
   * Perform search using provided text.
   *
   * @param searchText Query text.
   */
  fun performSearch(searchText: String) = localDataSource.searchProducts("%$searchText%")

}
