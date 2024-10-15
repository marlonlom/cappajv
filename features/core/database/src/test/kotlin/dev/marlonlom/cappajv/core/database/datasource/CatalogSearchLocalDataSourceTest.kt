/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.core.database.datasource

import dev.marlonlom.cappajv.core.database.dao.FakeCatalogFavoriteItemsDao
import dev.marlonlom.cappajv.core.database.dao.FakeCatalogItemsDao
import dev.marlonlom.cappajv.core.database.dao.FakeCatalogPunctuationsDao
import dev.marlonlom.cappajv.core.database.dao.FakeCatalogSearchDao
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

internal class CatalogSearchLocalDataSourceTest {

  private lateinit var dataSource: LocalDataSource
  private val fakeCatalogSearchDao = FakeCatalogSearchDao()

  @Before
  fun setup() {
    fillSampleCatalogItems()
    dataSource = LocalDataSourceImpl(
      catalogItemsDao = FakeCatalogItemsDao(),
      catalogPunctuationsDao = FakeCatalogPunctuationsDao(),
      catalogFavoriteItemsDao = FakeCatalogFavoriteItemsDao(),
      catalogSearchDao = fakeCatalogSearchDao
    )
  }

  @After
  fun teardown() {
    clearSampleCatalogItems()
  }

  @Test
  fun `Should success after searching product by title`() = runBlocking {
    val expectedTitle = "torta"
    dataSource.searchProducts(expectedTitle).collect { list ->
      assertNotNull(list)
      assertTrue(list.isNotEmpty())
      assertEquals(2, list.filter { it.title.lowercase().contains(expectedTitle) }.size)
    }
  }

  @Test
  fun `Should fail after searching product by title`() = runBlocking {
    val expectedTitle = "chamfle"
    dataSource.searchProducts(expectedTitle).collect { list ->
      assertNotNull(list)
      assertFalse(list.map { it.title.lowercase() }.contains(expectedTitle))
      assertTrue(list.isEmpty())
    }
  }

  private fun clearSampleCatalogItems() {
    fakeCatalogSearchDao.deleteAll()
  }

  private fun fillSampleCatalogItems() {
    listOf(
      "affogato", "almojabana", "cappuccino",
      "chai", "chocolate", "granizado",
      "pandebono", "torta de banano", "torta de zanahoria"
    ).mapIndexed { index, title ->
      CatalogItem(
        id = index.toLong() + 1,
        title = title,
        slug = title,
        titleNormalized = title,
        picture = "https://noimage.no.com/$title.png",
        category = "CategoryOne",
        detail = title,
        samplePunctuation = "",
        punctuationsCount = 0
      )
    }.also { items ->
      fakeCatalogSearchDao.insertAll(
        *items.toTypedArray()
      )
    }
  }

}
