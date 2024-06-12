/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.catalog_detail

import dev.marlonlom.cappajv.core.catalog_source.CatalogDataService
import dev.marlonlom.cappajv.core.database.FakeLocalDataSource
import dev.marlonlom.cappajv.core.database.entities.CatalogFavoriteItem
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.ui.util.slug
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import java.util.Locale

internal class CatalogDetailRepositoryTest {

  private lateinit var repository: CatalogDetailRepository

  @Before
  fun setUp() {
    repository = CatalogDetailRepository(
      FakeLocalDataSource(
        CatalogDataService(Locale.getDefault().language)
      )
    )
  }

  @Test
  fun `Should return single product item`() = runTest {
    val expectedItem = CatalogItem(
      id = 15396L,
      title = "Granizado",
      slug = "Granizado".slug,
      titleNormalized = "Granizado".lowercase(),
      picture = "https://juanvaldez.com/wp-content/uploads/2022/10/Granizado-juan-Valdez.jpg",
      category = "Category one",
      detail = "Lorem ipsum",
      samplePunctuation = "",
      punctuationsCount = 0,
    )
    val foundProduct = repository.find(expectedItem.id).first()
    assertNotNull(foundProduct)
    assertEquals(expectedItem, foundProduct!!.product)
    assertTrue(foundProduct.points.isNotEmpty())
  }

  @Test
  fun `Should return null product item`() = runTest {
    val foundProduct = repository.find(-1L).first()
    assertNull(foundProduct)
  }

  @Test
  fun `Should mark single product item as favorite`() = runTest {
    val favoriteItem = CatalogFavoriteItem(
      id = 15396L,
      title = "Granizado",
      picture = "https://juanvaldez.com/wp-content/uploads/2022/10/Granizado-juan-Valdez.jpg",
      category = "Category one",
      samplePunctuation = "",
      punctuationsCount = 0,
    )
    repository.saveFavorite(favoriteItem)
    val foundProduct = repository.find(favoriteItem.id).first()
    assertNotNull(foundProduct)
    if (foundProduct != null) {
      assertTrue(foundProduct.isFavorite)
    } else {
      fail()
    }
  }

  @Test
  fun `Should mark single product item as not favorite after marking it`() = runTest {
    val favoriteItem = CatalogFavoriteItem(
      id = 15396L,
      title = "Granizado",
      picture = "https://juanvaldez.com/wp-content/uploads/2022/10/Granizado-juan-Valdez.jpg",
      category = "Category one",
      samplePunctuation = "",
      punctuationsCount = 0,
    )
    repository.saveFavorite(favoriteItem)
    repository.deleteFavorite(favoriteItem.id)
    val foundProduct = repository.find(favoriteItem.id).first()
    assertNotNull(foundProduct)
    if (foundProduct != null) {
      assertFalse(foundProduct.isFavorite)
    } else {
      fail()
    }
  }

}
