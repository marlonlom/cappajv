/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database.datasource

import dev.marlonlom.apps.cappajv.core.database.dao.FakeCatalogFavoriteItemsDao
import dev.marlonlom.apps.cappajv.core.database.dao.FakeCatalogItemsDao
import dev.marlonlom.apps.cappajv.core.database.dao.FakeCatalogPunctuationsDao
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItem
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

internal class CatalogItemsLocalDataSourceTest {

  private lateinit var dataSource: LocalDataSource

  @Before
  fun setup() {
    dataSource = LocalDataSourceImpl(
      catalogItemsDao = FakeCatalogItemsDao(),
      catalogPunctuationsDao = FakeCatalogPunctuationsDao(),
      catalogFavoriteItemsDao = FakeCatalogFavoriteItemsDao()
    )
  }

  @Test
  fun `Should return empty list of products`() = runBlocking {
    dataSource.getAllProducts().collect { items ->
      assertNotNull(items)
      assertTrue(items.isEmpty())
    }
  }

  @Test
  fun `Should add products and get full detail`() = runBlocking {
    val product = CatalogItem(
      id = 1L,
      title = "Pod",
      slug = "pod",
      titleNormalized = "pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      detail = "Lorem ipsum"
    )
    dataSource.insertAllProducts(product)
    dataSource.findProduct(product.id).collect { item: CatalogItem? ->
      assertNotNull(item)
      when {
        item != null -> {
          assertEquals(1L, item.id)
          assertEquals("Pod", item.title)
          assertEquals("pod", item.slug)
          assertEquals("pod", item.titleNormalized)
          assertEquals("CategoryOne", item.category)
          assertEquals("Lorem ipsum", item.detail)
          assertEquals("https://noimage.no.com/no.png", item.picture)
        }

        else -> fail()
      }
    }
  }

  @Test
  fun `Should add products and get tuple detail`() = runBlocking {
    val product = CatalogItem(
      id = 1L,
      title = "Pod",
      slug = "pod",
      titleNormalized = "pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      detail = "Lorem ipsum"
    )
    dataSource.insertAllProducts(product)
    dataSource.getAllProducts()
      .filter { list -> list.indexOfFirst { it.id == product.id } >= 0 }
      .collect { list ->
        assertNotNull(list)
        assertTrue(list.isNotEmpty())
        val tuple = list.find { it.id == product.id }
        when {
          tuple != null -> {
            assertNotNull(tuple)
            assertEquals(1L, tuple.id)
            assertEquals("Pod", tuple.title)
            assertEquals("CategoryOne", tuple.category)
            assertEquals("https://noimage.no.com/no.png", tuple.picture)
          }

          else -> fail()
        }
      }
  }

  @Test
  fun `Should add product and then delete it`() = runBlocking {
    val product = CatalogItem(
      id = 1L,
      title = "Pod",
      slug = "pod",
      titleNormalized = "pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      detail = "Lorem ipsum"
    )
    dataSource.insertAllProducts(product)
    dataSource.deleteAllProducts()
    dataSource.findProduct(productId = product.id).collect { productItem ->
      assertNull(productItem)
    }
  }

}
