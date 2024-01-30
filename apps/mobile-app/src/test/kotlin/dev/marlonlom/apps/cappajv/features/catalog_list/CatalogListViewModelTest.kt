/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list

import dev.marlonlom.apps.cappajv.core.catalog_source.CatalogDataService
import dev.marlonlom.apps.cappajv.core.database.FakeLocalDataSource
import dev.marlonlom.apps.cappajv.util.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

internal class CatalogListViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var viewModel: CatalogListViewModel

  @Test
  fun `Should check that Ui state value is not empty list`() = runTest {
    viewModel = CatalogListViewModel(
      CatalogListRepository(
        localDataSource = FakeLocalDataSource(
          CatalogDataService()
        ),
        catalogDataService = CatalogDataService()
      )
    )
    val uiState = viewModel.uiState
    Assert.assertNotNull(uiState)
    when (uiState.value) {
      is CatalogListState.Listing -> {
        val home = uiState.value as CatalogListState.Listing
        Assert.assertTrue(home.list.isNotEmpty())
      }

      else -> Assert.fail()
    }
  }
}
