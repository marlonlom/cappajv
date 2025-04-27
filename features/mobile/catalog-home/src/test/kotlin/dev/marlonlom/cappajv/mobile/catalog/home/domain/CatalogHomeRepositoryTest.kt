/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.home.domain

import dev.marlonlom.cappajv.core.catalog.CatalogDataService
import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.mobile.catalog.home.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
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
internal class CatalogHomeRepositoryTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val testDispatcher = StandardTestDispatcher()
  private val localDataSource = mockk<LocalDataSource>()
  private val catalogDataService = mockk<CatalogDataService>()
  private lateinit var repository: CatalogHomeRepository

  @Test
  fun `Should emit success ui state with grouped catalog products`() = runTest {
    val productList = listOf(
      CatalogItemTuple(1, "Product A", "url1", "Category 1", "", 0),
      CatalogItemTuple(2, "Product B", "url2", "Category 2", "", 0),
      CatalogItemTuple(3, "Product C", "url3", "Category 1", "", 0),
    )
    coEvery { localDataSource.getAllProducts() } returns flowOf(productList)

    repository = CatalogHomeRepository(
      localDataSource,
      catalogDataService,
      testDispatcher,
    )

    val result = mutableListOf<CatalogHomeUiState>()
    val job = launch(UnconfinedTestDispatcher()) {
      repository.allProducts.toList(result)
    }

    advanceUntilIdle()

    assertTrue(result.isNotEmpty())
    val lastState = result.last()
    assertTrue(lastState is CatalogHomeUiState.Success)
    val successState = lastState as CatalogHomeUiState.Success
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
    coEvery { localDataSource.getAllProducts() } returns flowOf(emptyList())

    repository = CatalogHomeRepository(
      localDataSource,
      catalogDataService,
      testDispatcher,
    )

    val result = mutableListOf<CatalogHomeUiState>()
    val job = launch(UnconfinedTestDispatcher()) {
      repository.allProducts.toList(result)
    }

    advanceUntilIdle()

    assertTrue(result.isNotEmpty())
    assertTrue(result.last() is CatalogHomeUiState.Empty)

    job.cancel()
  }
}
