/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.catalog.home.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.cappajv.domain.catalog.home.CatalogHomeRepository
import dev.marlonlom.cappajv.domain.catalog.home.CatalogHomeUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel for managing the UI-related data and business logic of the catalog home screen.
 *
 * This ViewModel interacts with the [CatalogHomeRepository] to load and expose catalog data
 * to the UI layer. It survives configuration changes and helps maintain a clean separation
 * between UI and data sources.
 *
 * @author marlonlom
 *
 * @property repository The data repository responsible for providing catalog data.
 */
class CatalogHomeViewModel(private val repository: CatalogHomeRepository) : ViewModel() {

  /**
   * UI state object representing the current state of the catalog screen.
   *
   * This state is a StateFlow that emits updates from the repository.allProducts flow.
   * It triggers a catalog fetch when collection starts and uses [SharingStarted.WhileSubscribed]
   * to manage subscription behavior efficiently.
   *
   * - Emits [CatalogHomeUiState.Loading] initially.
   * - Updates when catalog data is fetched or changed.
   */
  val uiState = repository.allProducts
    .onStart {
      repository.fetchCatalogItems()
    }.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000L),
      initialValue = CatalogHomeUiState.Loading,
    )
}
