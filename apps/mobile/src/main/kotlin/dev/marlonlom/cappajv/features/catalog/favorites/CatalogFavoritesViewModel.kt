/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.catalog.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.cappajv.features.catalog.favorites.CatalogFavoritesUiState.Empty
import dev.marlonlom.cappajv.features.catalog.favorites.CatalogFavoritesUiState.Fetching
import dev.marlonlom.cappajv.features.catalog.favorites.CatalogFavoritesUiState.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatalogFavoritesViewModel(
  private val repository: CatalogFavoritesRepository,
) : ViewModel() {

  private val _favoritesListState = MutableStateFlow<CatalogFavoritesUiState>(Empty)
  val favoritesListState = _favoritesListState.stateIn(
    scope = viewModelScope,
    started = SharingStarted.Eagerly,
    initialValue = Empty,
  )

  private val _selectedCatalogId = MutableStateFlow(0L)
  val selectedCatalogId: MutableStateFlow<Long> = _selectedCatalogId

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

  fun selectCatalogItem(catalogId: Long) {
    _selectedCatalogId.value = catalogId
  }
}
