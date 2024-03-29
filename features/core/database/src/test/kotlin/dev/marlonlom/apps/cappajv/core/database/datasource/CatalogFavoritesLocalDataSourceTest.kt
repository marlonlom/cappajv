/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database.datasource

import dev.marlonlom.apps.cappajv.core.database.dao.FakeCatalogFavoriteItemsDao
import dev.marlonlom.apps.cappajv.core.database.dao.FakeCatalogItemsDao
import dev.marlonlom.apps.cappajv.core.database.dao.FakeCatalogPunctuationsDao
import dev.marlonlom.apps.cappajv.core.database.dao.FakeCatalogSearchDao
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogFavoriteItem
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

internal class CatalogFavoritesLocalDataSourceTest {

  private lateinit var dataSource: LocalDataSource

  @Before
  fun setup() {
    dataSource = LocalDataSourceImpl(
      catalogItemsDao = FakeCatalogItemsDao(),
      catalogPunctuationsDao = FakeCatalogPunctuationsDao(),
      catalogFavoriteItemsDao = FakeCatalogFavoriteItemsDao(),
      catalogSearchDao = FakeCatalogSearchDao()
    )
  }

  @Test
  fun `Should return empty list of favorite products`() = runBlocking {
    dataSource.getFavorites().collect { items ->
      assertNotNull(items)
      assertTrue(items.isEmpty())
    }
  }

  @Test
  fun `Should add favorite products`() = runBlocking {
    val product = CatalogFavoriteItem(
      id = 1L,
      title = "Pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      samplePunctuation = "",
      punctuationsCount = 0,
    )
    dataSource.insertAllFavoriteProducts(product)
    dataSource.getFavorites().collect { items ->
      assertNotNull(items)
      assertTrue(items.isNotEmpty())
      items.find { it.id == 1L }?.let {
        assertEquals(1L, it.id)
        assertEquals("Pod", it.title)
        assertEquals("CategoryOne", it.category)
        assertEquals("https://noimage.no.com/no.png", it.picture)
      }
    }
  }

  @Test
  fun `Should add favorite product and then delete it`() = runBlocking {
    val product = CatalogFavoriteItem(
      id = 1L,
      title = "Pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      samplePunctuation = "",
      punctuationsCount = 0,
    )
    dataSource.insertAllFavoriteProducts(product)
    dataSource.deleteAllFavorites()
    dataSource.getFavorites()
      .filter { list -> list.indexOfFirst { it.id == 1L } >= 0 }
      .collect { list ->
        assertTrue(list.isEmpty())
      }
  }

  @Test
  fun `Should add favorite products and then delete one`() = runBlocking {
    val products = arrayOf(
      CatalogFavoriteItem(
        id = 1L,
        title = "Pod",
        picture = "https://noimage.no.com/no.png",
        category = "CategoryOne",
        samplePunctuation = "",
        punctuationsCount = 0,
      ),
      CatalogFavoriteItem(
        id = 2L,
        title = "Tinto",
        picture = "https://noimage.no.com/no.png",
        category = "CategoryTwo",
        samplePunctuation = "",
        punctuationsCount = 0,
      )
    )
    dataSource.insertAllFavoriteProducts(*products)
    dataSource.deleteFavorite(2L)
    dataSource.getFavorites()
      .collect { list ->
        assertTrue(list.isNotEmpty())
        assertNotNull(list.firstOrNull())
      }
  }

  @Test
  fun `Should insert then fail check that is favorite item`() = runBlocking {
    val actual = dataSource.isFavorite(1234L).first()
    assertEquals(0, actual)
  }

  @Test
  fun shouldInsertThenSuccessCheckThatIsFavoriteItem() = runBlocking {
    val entity = CatalogFavoriteItem(
      id = 1L,
      title = "Pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      samplePunctuation = "",
      punctuationsCount = 0,
    )
    dataSource.insertAllFavoriteProducts(entity)
    val actual = dataSource.isFavorite(entity.id).first()
    assertEquals(1, actual)
  }

}
