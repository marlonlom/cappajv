/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.catalog.home.domain

import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.domain.catalog.home.CatalogHomeRepository
import dev.marlonlom.cappajv.domain.catalog.home.CatalogHomeUiState
import dev.marlonlom.cappajv.tv.catalog.home.util.MainDispatcherRule
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
internal class CatalogHomeViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val localDataSource = mockk<LocalDataSource>()
  private val repository = mockk<CatalogHomeRepository>()
  private lateinit var viewModel: CatalogHomeViewModel

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
    coEvery { repository.allProducts } returns flowOf(
      CatalogHomeUiState.Success(
        hashMapOf(
          "Category 1" to productList,
        ),
      ),
    )
    coJustRun { repository.fetchCatalogItems() }

    viewModel = CatalogHomeViewModel(repository)

    val result = mutableListOf<CatalogHomeUiState>()
    val job = launch {
      viewModel.uiState.toList(result)
    }
    advanceUntilIdle()

    assertNotNull(result.last())
    assertTrue(result.last() is CatalogHomeUiState.Success)
    val successState = result.last() as CatalogHomeUiState.Success
    assertTrue(successState.catalogMap.isNotEmpty())
    assertTrue(successState.catalogMap.values.flatten().contains(catalogItem))

    job.cancel()
  }

  @Test
  fun `Should check that Ui state value is empty`() = runTest {
    coEvery { localDataSource.getAllProducts() } returns flowOf(emptyList())
    coEvery { repository.allProducts } returns flowOf(CatalogHomeUiState.Empty)
    coJustRun { repository.fetchCatalogItems() }

    viewModel = CatalogHomeViewModel(repository)

    val result = mutableListOf<CatalogHomeUiState>()
    val job = launch {
      viewModel.uiState.toList(result)
    }
    advanceUntilIdle()

    assertNotNull(result.last())
    assertTrue(result.last() is CatalogHomeUiState.Empty)

    job.cancel()
  }
}
