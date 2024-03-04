/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list

import dev.marlonlom.apps.cappajv.core.catalog_source.CatalogDataService
import dev.marlonlom.apps.cappajv.core.database.FakeLocalDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import java.util.Locale

internal class CatalogListRepositoryTest {

  private lateinit var repository: CatalogListRepository

  @Before
  fun setUp() {
    repository = CatalogListRepository(
      localDataSource = FakeLocalDataSource(
        CatalogDataService(Locale.getDefault().language)
      ),
      catalogDataService = CatalogDataService(Locale.getDefault().language)
    )
  }

  @Test
  fun `Should return non empty list`() = runTest {
    when (val catalogListState = repository.allProducts.first()) {
      is CatalogListState.Listing -> {
        assertTrue(catalogListState.map.isNotEmpty())
      }

      else -> fail()
    }
  }
}
