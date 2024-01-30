/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.marlonlom.apps.cappajv.features.catalog_list.CatalogListState.Loading
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn


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

  /** UI state object for view model */
  val uiState = repository.allProducts.stateIn(viewModelScope, SharingStarted.Eagerly, Loading)

  companion object {
    fun factory(
      repository: CatalogListRepository
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
      @Suppress("UNCHECKED_CAST")
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CatalogListViewModel(repository) as T
      }
    }
  }
}
