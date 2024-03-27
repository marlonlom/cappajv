/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.apps.cappajv.features.catalog_list.CatalogListUiState.Loading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber


/**
 * Catalog list view model class.
 *
 * @author marlonlom
 *
 * @property repository catalog list repository dependency
 */
class CatalogListViewModel(
  private val repository: CatalogListRepository
) : ViewModel() {

  private val _selectedCatalogId = MutableStateFlow(0L)
  val selectedCatalogId: MutableStateFlow<Long> = _selectedCatalogId

  /** UI state object for view model */
  val uiState = repository.allProducts.stateIn(
    scope = viewModelScope,
    started = SharingStarted.Eagerly,
    initialValue = Loading
  )

  init {
    viewModelScope.launch {
      Timber.d("[CatalogListViewModel] launching fetchCatalogItems()")
      repository.fetchCatalogItems()
    }
  }

  fun selectCatalogItem(catalogId: Long) {
    _selectedCatalogId.value = catalogId
  }
}
