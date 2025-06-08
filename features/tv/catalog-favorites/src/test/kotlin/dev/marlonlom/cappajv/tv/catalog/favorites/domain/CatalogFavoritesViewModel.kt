/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.catalog.favorites.domain

import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.domain.catalog.favorites.CatalogFavoritesRepository
import dev.marlonlom.cappajv.domain.catalog.favorites.CatalogFavoritesUiState
import dev.marlonlom.cappajv.tv.catalog.favorites.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@OptIn(ExperimentalCoroutinesApi::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CatalogFavoritesViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val localDataSource = mockk<LocalDataSource>()
  private val repository = mockk<CatalogFavoritesRepository>()
  private lateinit var viewModel: CatalogFavoritesViewModel

  @Test
  fun `Should check that Ui state value is success with not empty list`() = runTest {
    val catalogItem = CatalogItemTuple(
      id = 1,
      title = "Item 1",
      picture = "http://myimage.com/items/1.png",
      category = "Category 1",
      samplePunctuation = "",
      punctuationsCount = 1,
    )
    val productList = listOf(catalogItem)

    coEvery { localDataSource.getAllProducts() } returns flowOf(productList)
    coEvery { repository.favoritesList } returns flowOf(
      CatalogFavoritesUiState.Success(
        hashMapOf(
          "Category 1" to productList,
        ),
      ),
    )

    viewModel = CatalogFavoritesViewModel(repository)

    val result = mutableListOf<CatalogFavoritesUiState>()
    val job = launch {
      viewModel.uiState.toList(result)
    }
    advanceUntilIdle()

    assertNotNull(result.last())
    assertTrue(result.last() is CatalogFavoritesUiState.Success)
    val successState = result.last() as CatalogFavoritesUiState.Success
    assertTrue(successState.catalogMap.isNotEmpty())
    assertTrue(successState.catalogMap.values.flatten().contains(catalogItem))

    job.cancel()
  }

  @Test
  fun `Should check that Ui state value is empty`() = runTest {
    coEvery { localDataSource.getAllProducts() } returns flowOf(emptyList())
    coEvery { repository.favoritesList } returns flowOf(CatalogFavoritesUiState.Empty)

    viewModel = CatalogFavoritesViewModel(repository)

    val result = mutableListOf<CatalogFavoritesUiState>()
    val job = launch {
      viewModel.uiState.toList(result)
    }
    advanceUntilIdle()

    assertNotNull(result.last())
    assertTrue(result.last() is CatalogFavoritesUiState.Empty)

    job.cancel()
  }

  @Test
  fun `Should undo favorite item then check that Ui state value is empty`() = runTest {
    coJustRun { localDataSource.deleteFavorite(any()) }
    coEvery { localDataSource.getAllProducts() } returns flowOf(emptyList())
    coJustRun { repository.undoFavorite(any()) }
    coEvery { repository.favoritesList } returns flowOf(CatalogFavoritesUiState.Empty)

    viewModel = CatalogFavoritesViewModel(repository)

    viewModel.undoFavorite(1L)

    val result = mutableListOf<CatalogFavoritesUiState>()
    val job = launch {
      viewModel.uiState.toList(result)
    }
    advanceUntilIdle()

    assertNotNull(result.last())
    assertTrue(result.last() is CatalogFavoritesUiState.Empty)

    job.cancel()
  }
}
