/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.favorites.domain

import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.core.database.entities.CatalogFavoriteItem
import dev.marlonlom.cappajv.mobile.catalog.favorites.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@OptIn(ExperimentalCoroutinesApi::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CatalogFavoritesRepositoryTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val localDataSource = mockk<LocalDataSource>()
  private lateinit var repository: CatalogFavoritesRepository

  @Test
  fun `Should emit success ui state with grouped catalog products`() = runTest {
    val productList = listOf(
      CatalogFavoriteItem(1, "Product A", "url1", "Category 1", "", 0),
      CatalogFavoriteItem(2, "Product B", "url2", "Category 2", "", 0),
      CatalogFavoriteItem(3, "Product C", "url3", "Category 1", "", 0),
    )
    coEvery { localDataSource.getFavorites() } returns flowOf(productList)

    repository = CatalogFavoritesRepository(localDataSource)

    val result = mutableListOf<CatalogFavoritesUiState>()
    val job = launch(UnconfinedTestDispatcher()) {
      repository.favoritesList.toList(result)
    }

    advanceUntilIdle()

    assertTrue(result.isNotEmpty())
    val lastState = result.last()
    assertTrue(lastState is CatalogFavoritesUiState.Success)
    val successState = lastState as CatalogFavoritesUiState.Success
    assertEquals(2, successState.catalogMap.size)
    assertTrue(successState.catalogMap.containsKey("Category 1"))
    assertTrue(successState.catalogMap.containsKey("Category 2"))
    assertEquals(2, successState.catalogMap["Category 1"]?.size)
    assertEquals(1, successState.catalogMap["Category 2"]?.size)
    assertTrue(successState.catalogMap["Category 1"]?.any { it.id == 1L } ?: false)
    assertTrue(successState.catalogMap["Category 1"]?.any { it.id == 3L } ?: false)
    assertTrue(successState.catalogMap["Category 2"]?.any { it.id == 2L } ?: false)

    job.cancel()
  }

  @Test
  fun `Should emit empty ui state`() = runTest {
    coEvery { localDataSource.getFavorites() } returns flowOf(emptyList())

    repository = CatalogFavoritesRepository(localDataSource)

    val result = mutableListOf<CatalogFavoritesUiState>()
    val job = launch(UnconfinedTestDispatcher()) {
      repository.favoritesList.toList(result)
    }

    advanceUntilIdle()

    assertTrue(result.isNotEmpty())
    assertTrue(result.last() is CatalogFavoritesUiState.Empty)

    job.cancel()
  }

  @Test
  fun `Should undo favorite item then emit empty ui state`() = runTest {
    coJustRun { localDataSource.deleteFavorite(any()) }
    coEvery { localDataSource.getFavorites() } returns flowOf(emptyList())

    repository = CatalogFavoritesRepository(localDataSource)
    repository.undoFavorite(1L)

    val result = mutableListOf<CatalogFavoritesUiState>()
    val job = launch(UnconfinedTestDispatcher()) {
      repository.favoritesList.toList(result)
    }

    advanceUntilIdle()

    assertTrue(result.isNotEmpty())
    assertTrue(result.last() is CatalogFavoritesUiState.Empty)

    job.cancel()
  }
}
