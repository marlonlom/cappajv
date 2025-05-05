/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.domain.catalog.detail

import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.core.database.entities.CatalogFavoriteItem
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@OptIn(ExperimentalCoroutinesApi::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CatalogDetailRepositoryTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val localDataSource = mockk<LocalDataSource>()
  private lateinit var repository: CatalogDetailRepository

  @Test
  fun `Should emit null result when finding catalog detail using id -1`() = runTest {
    val catalogItem = CatalogItem(
      id = -1L,
      title = "Pod",
      picture = "https://noimage.no.com/pod.png",
      category = "Category 1",
      slug = "pod",
      titleNormalized = "pod",
      detail = "Lorem ipsum",
      samplePunctuation = "",
      punctuationsCount = 0,
    )

    coEvery { localDataSource.findProduct(any(Long::class)) } returns flowOf(catalogItem)
    coEvery { localDataSource.isFavorite(any(Long::class)) } returns flowOf(0)
    coEvery { localDataSource.getPunctuations(any(Long::class)) } returns flowOf(emptyList())

    repository = CatalogDetailRepository(localDataSource)

    val result = mutableListOf<CatalogDetailItem?>()
    val job = launch(UnconfinedTestDispatcher()) {
      repository.find(catalogItem.id).toList(result)
    }

    advanceUntilIdle()

    assertNull(result.last())

    job.cancel()
  }

  @Test
  fun `Should emit null result when finding catalog details`() = runTest {
    coEvery { localDataSource.findProduct(any(Long::class)) } returns flowOf(null)
    coEvery { localDataSource.isFavorite(any(Long::class)) } returns flowOf(0)
    coEvery { localDataSource.getPunctuations(any(Long::class)) } returns flowOf(emptyList())

    repository = CatalogDetailRepository(localDataSource)

    val result = mutableListOf<CatalogDetailItem?>()
    val job = launch(UnconfinedTestDispatcher()) {
      result.add(repository.find(2L).lastOrNull())
    }

    advanceUntilIdle()

    assertNull(result.last())

    job.cancel()
  }

  @Test
  fun `Should emit valid result when finding catalog details`() = runTest {
    val catalogItem = CatalogItem(
      id = 1L,
      title = "Pod",
      picture = "https://noimage.no.com/pod.png",
      category = "Category 1",
      slug = "pod",
      titleNormalized = "pod",
      detail = "Lorem ipsum",
      samplePunctuation = "Unit: 1234",
      punctuationsCount = 1,
    )
    val punctuation = CatalogPunctuation(
      id = 9L,
      catalogItemId = 1L,
      label = "Unit",
      points = 1234L,
    )

    coEvery { localDataSource.findProduct(any(Long::class)) } returns flowOf(catalogItem)
    coEvery { localDataSource.isFavorite(any(Long::class)) } returns flowOf(0)
    coEvery { localDataSource.getPunctuations(any(Long::class)) } returns flowOf(listOf(punctuation))

    repository = CatalogDetailRepository(localDataSource)

    val result = mutableListOf<CatalogDetailItem?>()
    val job = launch(UnconfinedTestDispatcher()) {
      repository.find(catalogItem.id).toList(result)
    }

    advanceUntilIdle()

    val foundItem = result.last()
    assertNotNull(foundItem)
    if (foundItem != null) {
      assertFalse(foundItem.isFavorite)

      assertNotNull(foundItem.points)
      assertTrue(foundItem.points.isNotEmpty())

      assertNotNull(foundItem.product)
      assertEquals(catalogItem.id, foundItem.product.id)
      assertEquals(catalogItem.title, foundItem.product.title)
      assertEquals(catalogItem.titleNormalized, foundItem.product.titleNormalized)
      assertEquals(catalogItem.detail, foundItem.product.detail)
      assertEquals(catalogItem.picture, foundItem.product.picture)
      assertEquals(catalogItem.category, foundItem.product.category)
      assertEquals(catalogItem.slug, foundItem.product.slug)
      assertEquals(catalogItem.samplePunctuation, foundItem.product.samplePunctuation)
      assertEquals(
        catalogItem.punctuationsCount,
        foundItem.product.punctuationsCount,
      )
    }

    job.cancel()
  }

  @Test
  fun `Should emit valid result when finding favorite catalog details`() = runTest {
    val catalogItem = CatalogItem(
      id = 2L,
      title = "Chai",
      picture = "https://noimage.no.com/chai.png",
      category = "Category 2",
      slug = "chai",
      titleNormalized = "chai",
      detail = "Lorem ipsum",
      samplePunctuation = "Unit: 1234",
      punctuationsCount = 1,
    )
    val punctuation = CatalogPunctuation(
      id = 99L,
      catalogItemId = catalogItem.id,
      label = "Small",
      points = 1235L,
    )

    coEvery { localDataSource.findProduct(any(Long::class)) } returns flowOf(catalogItem)
    coEvery { localDataSource.isFavorite(any(Long::class)) } returns flowOf(1)
    coEvery { localDataSource.getPunctuations(any(Long::class)) } returns flowOf(
      listOf(
        punctuation,
        punctuation.copy(id = 98L, label = "Medium", points = 2030L),
      ),
    )

    repository = CatalogDetailRepository(localDataSource)

    val result = mutableListOf<CatalogDetailItem?>()
    val job = launch(UnconfinedTestDispatcher()) {
      repository.find(catalogItem.id).toList(result)
    }

    advanceUntilIdle()

    val foundItem = result.last()
    assertNotNull(foundItem)
    if (foundItem != null) {
      assertTrue(foundItem.isFavorite)

      assertNotNull(foundItem.points)
      assertTrue(foundItem.points.isNotEmpty())

      assertNotNull(foundItem.product)
      assertEquals(catalogItem, foundItem.product)
    }

    job.cancel()
  }

  @Test
  fun `Should save catalog detail as favorite`() = runTest {
    val catalogItem = CatalogItem(
      id = 2L,
      title = "Latte",
      picture = "https://noimage.no.com/latte.png",
      category = "Category 1",
      slug = "latte",
      titleNormalized = "latte",
      detail = "Lorem ipsum",
      samplePunctuation = "",
      punctuationsCount = 0,
    )

    val favoriteItem = catalogItem.let {
      CatalogFavoriteItem(
        id = it.id,
        title = it.title,
        picture = it.picture,
        category = it.category,
        samplePunctuation = it.samplePunctuation,
        punctuationsCount = it.punctuationsCount,
      )
    }

    coEvery { localDataSource.insertFavoriteProduct(any(CatalogFavoriteItem::class)) } returns Unit
    coEvery { localDataSource.findProduct(any(Long::class)) } returns flowOf(catalogItem)
    coEvery { localDataSource.isFavorite(any(Long::class)) } returns flowOf(1)
    coEvery { localDataSource.getPunctuations(any(Long::class)) } returns flowOf(emptyList())

    repository = CatalogDetailRepository(localDataSource)

    val result = mutableListOf<CatalogDetailItem?>()
    val job = launch(UnconfinedTestDispatcher()) {
      repository.saveFavorite(favoriteItem)
      repository.find(catalogItem.id).toList(result)
    }

    advanceUntilIdle()

    val foundItem = result.last()
    assertNotNull(foundItem)
    if (foundItem != null) {
      assertTrue(foundItem.isFavorite)

      assertNotNull(foundItem.points)
      assertFalse(foundItem.points.isNotEmpty())

      assertNotNull(foundItem.product)
      assertEquals(catalogItem, foundItem.product)
    }

    job.cancel()
  }

  @Test
  fun `Should undo catalog detail as favorite`() = runTest {
    val catalogItem = CatalogItem(
      id = 2L,
      title = "Latte",
      picture = "https://noimage.no.com/latte.png",
      category = "Category 1",
      slug = "latte",
      titleNormalized = "latte",
      detail = "Lorem ipsum",
      samplePunctuation = "",
      punctuationsCount = 0,
    )

    coEvery { localDataSource.deleteFavorite(any(Long::class)) } returns Unit
    coEvery { localDataSource.findProduct(any(Long::class)) } returns flowOf(catalogItem)
    coEvery { localDataSource.isFavorite(any(Long::class)) } returns flowOf(0)
    coEvery { localDataSource.getPunctuations(any(Long::class)) } returns flowOf(emptyList())

    repository = CatalogDetailRepository(localDataSource)

    val result = mutableListOf<CatalogDetailItem?>()
    val job = launch(UnconfinedTestDispatcher()) {
      repository.undoFavorite(catalogItem.id)
      repository.find(catalogItem.id).toList(result)
    }

    advanceUntilIdle()

    val foundItem = result.last()
    assertNotNull(foundItem)
    if (foundItem != null) {
      assertFalse(foundItem.isFavorite)

      assertNotNull(foundItem.points)
      assertTrue(foundItem.points.isEmpty())

      assertNotNull(foundItem.product)
      assertEquals(catalogItem, foundItem.product)
    }

    job.cancel()
  }
}
