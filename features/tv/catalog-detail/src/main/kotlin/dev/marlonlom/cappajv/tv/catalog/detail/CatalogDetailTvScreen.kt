/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.catalog.detail

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.cappajv.domain.catalog.detail.CatalogDetailUiState
import dev.marlonlom.cappajv.tv.catalog.detail.domain.CatalogDetailViewModel
import dev.marlonlom.cappajv.tv.catalog.detail.slot.NotFoundDetailsSlot
import dev.marlonlom.cappajv.tv.designsystem.slot.LoadingSlot
import org.koin.androidx.compose.koinViewModel

/**
 * Displays the details for a specific catalog item on a TV screen.
 *
 * @author marlonlom
 *
 * @param detailId The unique identifier of the catalog item to display.
 * @param onNavigationBack Callback to be invoked when the user requests to navigate back.
 * @param viewModel The `CatalogDetailViewModel` responsible for providing the item details.
 */
@Composable
fun CatalogDetailTvScreen(
  detailId: Long,
  onNavigationBack: () -> Unit,
  viewModel: CatalogDetailViewModel = koinViewModel(),
) {
  BackHandler(onBack = {
    viewModel.unFind()
    onNavigationBack()
  })
  viewModel.find(detailId)
  val detailUiState = viewModel.uiState.collectAsStateWithLifecycle()

  when (detailUiState.value) {
    is CatalogDetailUiState.Found -> {
      val detailItem = (detailUiState.value as CatalogDetailUiState.Found).detail
      CatalogDetailTvScreenContent(detailItem)
    }

    CatalogDetailUiState.Loading -> {
      LoadingSlot()
    }

    CatalogDetailUiState.NotFound -> {
      NotFoundDetailsSlot()
    }
  }
}
