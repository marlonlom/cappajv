/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Catalog home view model class.
 *
 * @author marlonlom
 *
 * @property repository catalog list repository dependency
 */
class CatalogHomeViewModel(
  private val repository: CatalogHomeRepository,
) : ViewModel() {

  /** UI state object for view model */
  val uiState = repository.allProducts.stateIn(
    scope = viewModelScope,
    started = SharingStarted.Eagerly,
    initialValue = CatalogHomeUiState.Loading,
  )

  init {
    viewModelScope.launch {
      Timber.d("[CatalogListViewModel] launching fetchCatalogItems()")
      repository.fetchCatalogItems()
    }
  }

}
