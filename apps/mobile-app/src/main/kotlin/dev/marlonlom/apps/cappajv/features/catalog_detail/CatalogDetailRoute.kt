/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.apps.cappajv.features.catalog_detail.screens.CatalogDetailRouteScreen
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.koinViewModel

/**
 * Catalog detail route composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param catalogId Selected catalog item id.
 * @param viewModel Catalog detail viewmodel.
 */
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@Composable
fun CatalogDetailRoute(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  isRouting: Boolean,
  catalogId: Long,
  viewModel: CatalogDetailViewModel = koinViewModel(),
) {

  if (isRouting) {
    BackHandler {
      appState.navController.navigateUp()
    }
  }

  viewModel.find(catalogId)
  val detailUiState by viewModel.detail.collectAsStateWithLifecycle()

  CatalogDetailRouteScreen(
    appState = appState,
    appContentCallbacks = appContentCallbacks,
    isRouting = isRouting,
    detailUiState = detailUiState,
  )
}
