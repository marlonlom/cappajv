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
import java.util.Locale

internal class CatalogListViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var viewModel: CatalogListViewModel

  @Test
  fun `Should check that Ui state value is not empty list`() = runTest {
    viewModel = CatalogListViewModel(
      CatalogListRepository(
        localDataSource = FakeLocalDataSource(
          CatalogDataService(Locale.getDefault().language)
        ),
        catalogDataService = CatalogDataService(Locale.getDefault().language)
      )
    )
    val uiState = viewModel.uiState
    Assert.assertNotNull(uiState)
    when (uiState.value) {
      is CatalogListUiState.Listing -> {
        val home = uiState.value as CatalogListUiState.Listing
        Assert.assertTrue(home.catalogMap.isNotEmpty())
      }

      else -> Assert.fail()
    }
  }
}
