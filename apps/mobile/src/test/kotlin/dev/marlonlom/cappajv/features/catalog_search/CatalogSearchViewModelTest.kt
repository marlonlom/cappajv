/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.catalog_search

import dev.marlonlom.cappajv.core.catalog_source.CatalogDataService
import dev.marlonlom.cappajv.core.database.FakeLocalDataSource
import dev.marlonlom.cappajv.util.MainDispatcherRule
import dev.marlonlom.cappajv.util.RethrowingExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.util.Locale

@ExperimentalCoroutinesApi
internal class CatalogSearchViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  @get:Rule
  val throwRule = RethrowingExceptionHandler()

  private val fakeLocalDataSource = FakeLocalDataSource(
    CatalogDataService(Locale.getDefault().language)
  )

  private lateinit var viewModel: CatalogSearchViewModel

  @Before
  fun setUp() {
    MockitoAnnotations.openMocks(this)
    viewModel = CatalogSearchViewModel(CatalogSearchRepository(fakeLocalDataSource))
  }

  @Test
  fun `Should return default search ui state`() = runTest {
    viewModel.onQueryTextChanged()
    val uiState = viewModel.searchResult.value
    assertTrue(viewModel.queryText.value.isEmpty())
    assertEquals(CatalogSearchUiState.None, uiState)
  }

  @Test
  fun `Should success after searching product by title`() = runTest {
    val expectedTitle = "torta"
    viewModel.queryText.value = expectedTitle
    viewModel.onQueryTextChanged()
    val uiState = viewModel.searchResult.value
    assertNotNull(uiState)
    when (uiState) {
      is CatalogSearchUiState.Success -> {
        assertTrue(uiState.results.isNotEmpty())
        assertEquals(
          3,
          uiState.results.filter {
            it.title.lowercase().contains(expectedTitle)
          }.size
        )
      }

      else -> fail()
    }
  }

  @Test
  fun `Should fail after searching product by title`() = runTest {
    val expectedTitle = "chamfle"
    viewModel.queryText.value = expectedTitle
    viewModel.onQueryTextChanged()
    val uiState = viewModel.searchResult.value
    assertNotNull(uiState)
    assertTrue(uiState == CatalogSearchUiState.Empty)
  }
}
