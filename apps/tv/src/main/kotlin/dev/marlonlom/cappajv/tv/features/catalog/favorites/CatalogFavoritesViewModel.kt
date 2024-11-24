/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Catalog favorites view model class.
 *
 * @author marlonlom
 *
 * @property repository catalog favorites repository dependency.
 */
class CatalogFavoritesViewModel(
  private val repository: CatalogFavoritesRepository,
) : ViewModel() {

  /** UI state object for view model */
  val uiState = repository.allFavorites.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000L),
    initialValue = CatalogFavoritesUiState.Fetching,
  )

  fun deleteFavorite(catalogId: Long) {
    viewModelScope.launch {
      repository.deleteFavorite(catalogId)
    }
  }

}
