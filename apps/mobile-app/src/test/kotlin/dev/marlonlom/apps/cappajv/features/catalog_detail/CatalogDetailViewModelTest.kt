/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail

import dev.marlonlom.apps.cappajv.core.catalog_source.CatalogDataService
import dev.marlonlom.apps.cappajv.core.database.FakeLocalDataSource
import dev.marlonlom.apps.cappajv.util.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Locale

internal class CatalogDetailViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var viewModel: CatalogDetailViewModel

  @Before
  fun setUp() {
    viewModel = CatalogDetailViewModel(
      CatalogDetailRepository(
        FakeLocalDataSource(CatalogDataService(Locale.getDefault().language))
      )
    )
  }

  @Test
  fun `Should find catalog details by id`() = runTest {
    val expectedItemId = 15396L
    viewModel.find(expectedItemId)
    val catalogDetail = viewModel.detail.value
    when {
      catalogDetail != null -> {
        Assert.assertNotNull(catalogDetail.product)
        Assert.assertEquals(expectedItemId, catalogDetail.product.id)
        Assert.assertTrue(catalogDetail.points.isNotEmpty())
      }

      else ->
        Assert.fail("Not found")
    }
  }

  @Test
  fun `Should not find catalog details by id`() = runTest {
    val expectedItemId = 9999L
    viewModel.find(expectedItemId)
    val catalogDetail = viewModel.detail.value
    Assert.assertNull(catalogDetail)
  }
}
