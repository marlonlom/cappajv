/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.home.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel responsible for managing the UI state of the Catalog Home screen.
 *
 * @author marlonlom
 *
 * @property repository The repository providing access to catalog data.
 */
class CatalogHomeViewModel(private val repository: CatalogHomeRepository) : ViewModel() {

  /** UI state object for view model */
  val uiState = repository.allProducts
    .onStart {
      repository.fetchCatalogItems()
    }.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000L),
      initialValue = CatalogHomeUiState.Loading,
    )
}
