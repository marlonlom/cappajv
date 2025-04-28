/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.domain

import dev.marlonlom.cappajv.core.database.entities.CatalogFavoriteItem
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.mobile.catalog.detail.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@OptIn(ExperimentalCoroutinesApi::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CatalogDetailViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val repository = mockk<CatalogDetailRepository>()
  private lateinit var viewModel: CatalogDetailViewModel

  @Test
  fun `Should emit null result when finding catalog details`() = runTest {
    coEvery { repository.find(any(Long::class)) } returns flowOf(null)

    viewModel = CatalogDetailViewModel(repository)
    viewModel.find(789L)

    val result = mutableListOf<CatalogDetailUiState?>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.detail.toList(result)
    }

    advanceUntilIdle()

    val foundItem = result.last()
    assertNotNull(foundItem)
    assertTrue(foundItem == CatalogDetailUiState.NotFound)

    job.cancel()
  }

  @Test
  fun `Should save catalog detail as favorite then fetch it`() = runTest {
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

    coEvery { repository.saveFavorite(any(CatalogFavoriteItem::class)) } returns Unit
    coEvery { repository.find(any(Long::class)) } returns flowOf(
      CatalogDetailItem(
        product = catalogItem,
        isFavorite = true,
        points = emptyList(),
      ),
    )

    viewModel = CatalogDetailViewModel(repository)
    viewModel.toggleFavorite(catalogItem, true)
    viewModel.find(catalogItem.id)

    val result = mutableListOf<CatalogDetailUiState?>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.detail.toList(result)
    }

    advanceUntilIdle()

    val foundItem = result.last()
    assertNotNull(foundItem)
    assertTrue(foundItem is CatalogDetailUiState.Found)
    if (foundItem is CatalogDetailUiState.Found) {
      assertTrue(foundItem.detail.isFavorite)

      assertNotNull(foundItem.detail.points)
      assertTrue(foundItem.detail.points.isEmpty())

      assertNotNull(foundItem.detail.product)
      assertEquals(catalogItem, foundItem.detail.product)
    }

    job.cancel()
  }

  @Test
  fun `Should undo catalog detail as favorite then fetch it`() = runTest {
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

    coEvery { repository.undoFavorite(any(Long::class)) } returns Unit
    coEvery { repository.find(any(Long::class)) } returns flowOf(
      CatalogDetailItem(
        product = catalogItem,
        isFavorite = false,
        points = emptyList(),
      ),
    )

    viewModel = CatalogDetailViewModel(repository)
    viewModel.toggleFavorite(catalogItem, false)
    viewModel.find(catalogItem.id)

    val result = mutableListOf<CatalogDetailUiState?>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.detail.toList(result)
    }

    advanceUntilIdle()

    val foundItem = result.last()
    assertNotNull(foundItem)
    assertTrue(foundItem is CatalogDetailUiState.Found)
    if (foundItem is CatalogDetailUiState.Found) {
      assertFalse(foundItem.detail.isFavorite)

      assertNotNull(foundItem.detail.points)
      assertTrue(foundItem.detail.points.isEmpty())

      assertNotNull(foundItem.detail.product)
      assertEquals(catalogItem, foundItem.detail.product)
    }

    job.cancel()
  }
}
