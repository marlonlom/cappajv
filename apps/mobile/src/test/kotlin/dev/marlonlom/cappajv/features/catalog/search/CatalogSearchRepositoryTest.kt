/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.catalog.search

import dev.marlonlom.cappajv.core.catalog.CatalogDataService
import dev.marlonlom.cappajv.core.database.FakeLocalDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.Locale

internal class CatalogSearchRepositoryTest {

  private lateinit var repository: CatalogSearchRepository

  @Before
  fun setUp() {
    repository = CatalogSearchRepository(
      localDataSource = FakeLocalDataSource(
        CatalogDataService(Locale.getDefault().language),
      ),
    )
  }

  @Test
  fun `Should success after searching product by title`() = runBlocking {
    val expectedTitle = "torta"
    repository.performSearch(expectedTitle).collect { list ->
      Assert.assertNotNull(list)
      Assert.assertTrue(list.isNotEmpty())
      Assert.assertEquals(3, list.filter { it.title.lowercase().contains(expectedTitle) }.size)
    }
  }

  @Test
  fun `Should fail after searching product by title`() = runBlocking {
    val expectedTitle = "chamfle"
    repository.performSearch(expectedTitle).collect { list ->
      Assert.assertNotNull(list)
      Assert.assertFalse(list.map { it.title.lowercase() }.contains(expectedTitle))
      Assert.assertTrue(list.isEmpty())
    }
  }
}
