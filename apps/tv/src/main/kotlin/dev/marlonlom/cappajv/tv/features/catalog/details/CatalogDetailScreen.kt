/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.details

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.koinViewModel

/**
 * Catalog detail screen route composable ui.
 *
 * @author marlonlom
 *
 * @param detailId Catalog item id.
 * @param onNavigationBack Action for back navigation
 * @param modifier The modifier for this composable.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun CatalogDetailScreen(
  detailId: String,
  onNavigationBack: () -> Unit,
  viewModel: CatalogDetailViewModel = koinViewModel(),
  modifier: Modifier = Modifier,
) {
  BackHandler(onBack = onNavigationBack)
  viewModel.find(detailId.toLong())
  val detailUiState = viewModel.detail.collectAsStateWithLifecycle(
    lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current,
  )

  when (detailUiState.value) {
    is CatalogDetailUiState.Found -> {
      val foundDetail = detailUiState.value as CatalogDetailUiState.Found
      CatalogDetailInfoSlot(
        foundDetail = foundDetail.detail,
        onMarkedFavorite = viewModel::toggleFavorite,
      )
    }

    CatalogDetailUiState.NotFound -> {
      CatalogDetailNotFoundSlot(modifier)
    }
  }
}
