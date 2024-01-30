/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database

import dev.marlonlom.apps.cappajv.core.database.dao.FakeCatalogProductsDao
import dev.marlonlom.apps.cappajv.core.database.dao.FakeCatalogPunctuationsDao
import dev.marlonlom.apps.cappajv.core.database.entities.ProductItem
import dev.marlonlom.apps.cappajv.core.database.entities.ProductItemPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

internal class LocalDataSourceTest {

  private lateinit var dataSource: LocalDataSource

  @Before
  fun setup() {
    dataSource = LocalDataSourceImpl(
      catalogProductsDao = FakeCatalogProductsDao(),
      catalogPunctuationsDao = FakeCatalogPunctuationsDao()
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
  fun `Should add products`() = runBlocking {
    val product = ProductItem(
      id = 1L,
      title = "Pod",
      slug = "pod",
      titleNormalized = "pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      detail = "Lorem ipsum"
    )
    dataSource.insertAllProducts(product)
    dataSource.findProduct(productId = product.id).collect { productItem ->
      assertNotNull(productItem)
      productItem?.let {
        assertEquals(1L, it.id)
        assertEquals("Pod", it.title)
        assertEquals("pod", it.slug)
        assertEquals("pod", it.titleNormalized)
        assertEquals("Lorem ipsum", it.detail)
        assertEquals("CategoryOne", it.category)
        assertEquals("https://noimage.no.com/no.png", it.picture)
      }
    }
  }

  @Test
  fun `Should add product and then delete it`() = runBlocking {
    val product = ProductItem(
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

  @Test
  fun `Should add product with punctuations`() = runBlocking {
    val product = ProductItem(
      id = 1L,
      title = "Pod",
      slug = "pod",
      titleNormalized = "pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      detail = "Lorem ipsum"
    )
    val productPoint = ProductItemPoint(
      id = 11L,
      productId = product.id,
      label = "Unidad",
      points = 1234L
    )

    dataSource.insertAllProducts(product)
    dataSource.insertAllPunctuations(productPoint)

    combine(
      dataSource.findProduct(product.id),
      dataSource.getPunctuations(product.id)
    ) { productItem, productItemPoints ->
      Pair(productItem, productItemPoints)
    }.collect { pair: Pair<ProductItem?, List<ProductItemPoint>> ->
      assertNotNull(pair.first)
      assertNotNull(pair.second)
      assertTrue(pair.second.isNotEmpty())
      pair.second.firstOrNull().let { itemPoint ->
        itemPoint?.let {
          assertEquals(productPoint.id, it.id)
          assertEquals(productPoint.productId, it.productId)
          assertEquals(productPoint.label, it.label)
          assertEquals(productPoint.points, it.points)
        }
      }
    }
  }

  @Test
  fun `Should add product with no punctuations`() = runBlocking {
    val product = ProductItem(
      id = 1L,
      title = "Pod",
      slug = "pod",
      titleNormalized = "pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      detail = "Lorem ipsum"
    )
    val productPoint = ProductItemPoint(
      id = 11L,
      productId = product.id,
      label = "Unidad",
      points = 1234L
    )

    dataSource.insertAllProducts(product)
    dataSource.insertAllPunctuations(productPoint)
    dataSource.deleteAllPunctuations()

    combine(
      dataSource.findProduct(product.id),
      dataSource.getPunctuations(product.id)
    ) { productItem, productItemPoints ->
      Pair(productItem, productItemPoints)
    }.collect { pair: Pair<ProductItem?, List<ProductItemPoint>> ->
      assertNotNull(pair.first)
      assertNotNull(pair.second)
      assertTrue(pair.second.isEmpty())
    }
  }

}
