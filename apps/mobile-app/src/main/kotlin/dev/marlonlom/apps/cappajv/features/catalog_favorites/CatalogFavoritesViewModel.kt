/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.apps.cappajv.features.catalog_favorites.CatalogFavoritesUiState.Empty
import dev.marlonlom.apps.cappajv.features.catalog_favorites.CatalogFavoritesUiState.Fetching
import dev.marlonlom.apps.cappajv.features.catalog_favorites.CatalogFavoritesUiState.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatalogFavoritesViewModel(
  private val repository: CatalogFavoritesRepository
) : ViewModel() {

  private val _favoritesListState = MutableStateFlow<CatalogFavoritesUiState>(Empty)
  val favoritesListState = _favoritesListState.stateIn(
    scope = viewModelScope,
    started = SharingStarted.Eagerly,
    initialValue = Empty
  )

  init {
    this.fetchAllFavorites()
  }

  private fun fetchAllFavorites() {
    viewModelScope.launch {
      _favoritesListState.update { Fetching }
      repository.favoritesListFlow.collect { list ->
        _favoritesListState.update { if (list.isEmpty()) Empty else Success(list) }
      }
    }
  }

  fun deleteFavorite(catalogId: Long) {
    viewModelScope.launch {
      repository.deleteFavorite(catalogId)
    }
  }
}
