/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail

import androidx.lifecycle.SavedStateHandle
import dev.marlonlom.apps.cappajv.core.catalog_source.CatalogDataService
import dev.marlonlom.apps.cappajv.core.database.FakeLocalDataSource
import dev.marlonlom.apps.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailUiState.Found
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailUiState.NotFound
import dev.marlonlom.apps.cappajv.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import java.util.Locale

@ExperimentalCoroutinesApi
internal class CatalogDetailViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val fakeLocalDataSource = FakeLocalDataSource(
    CatalogDataService(Locale.getDefault().language)
  )

  private val mockLocalDataSource = mock<LocalDataSource> { mock ->
    on(mock.findProduct(anyLong())).thenAnswer {
      fakeLocalDataSource.findProduct(it.getArgument(0))
    }
    on(mock.getPunctuations(anyLong())).thenAnswer {
      fakeLocalDataSource.getPunctuations(it.getArgument(0))
    }
  }

  private lateinit var viewModel: CatalogDetailViewModel

  @Before
  fun setUp() {
    MockitoAnnotations.openMocks(this)
    viewModel = CatalogDetailViewModel(
      repository = CatalogDetailRepository(
        localDataSource = mockLocalDataSource,
        coroutineDispatcher = UnconfinedTestDispatcher()
      ),
      savedStateHandle = SavedStateHandle()
    )
  }

  @Test
  fun `Should find catalog details by id`() = runTest {
    val expectedItemId = 15396L
    viewModel.find(expectedItemId)
    when (val detailState = viewModel.detail.value) {
      is Found -> {
        assertNotNull(detailState.detail.product)
        assertEquals(expectedItemId, detailState.detail.product.id)
        assertTrue(detailState.detail.points.isNotEmpty())
      }

      NotFound -> fail("Not found")
    }
  }

  @Test
  fun `Should not find catalog details by id`() = runTest {
    val expectedItemId = 9999L
    viewModel.find(expectedItemId)
    val detailState = viewModel.detail.value
    assertNotNull(detailState)
    assertTrue(detailState == NotFound)
  }
}
