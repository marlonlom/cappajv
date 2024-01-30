/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail

import dev.marlonlom.apps.cappajv.core.catalog_source.CatalogDataService
import dev.marlonlom.apps.cappajv.core.database.FakeLocalDataSource
import dev.marlonlom.apps.cappajv.core.database.entities.ProductItem
import dev.marlonlom.apps.cappajv.ui.util.slug
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

internal class CatalogDetailRepositoryTest {

  private lateinit var repository: CatalogDetailRepository

  @Before
  fun setUp() {
    repository = CatalogDetailRepository(
      FakeLocalDataSource(
        CatalogDataService()
      )
    )
  }

  @Test
  fun `Should return single product item`() = runTest {
    val expectedItem = ProductItem(
      id = 15396L,
      title = "Granizado",
      slug = "Granizado".slug,
      titleNormalized = "Granizado".lowercase(),
      picture = "https://juanvaldez.com/wp-content/uploads/2022/10/Granizado-juan-Valdez.jpg",
      category = "Category one",
      detail = "Lorem ipsum",
    )
    val foundProduct = repository.find(expectedItem.id).first()
    assertNotNull(foundProduct)
    assertEquals(expectedItem, foundProduct!!.product)
    assertTrue(foundProduct.points.isNotEmpty())
  }

  @Test
  fun `Should return null product item`() = runTest {
    val foundProduct = repository.find(1L).first()
    assertNull(foundProduct)
  }

}
