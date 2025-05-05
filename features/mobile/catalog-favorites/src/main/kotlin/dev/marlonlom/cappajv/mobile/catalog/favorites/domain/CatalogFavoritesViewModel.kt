/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.favorites.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.cappajv.domain.catalog.favorites.CatalogFavoritesRepository
import dev.marlonlom.cappajv.domain.catalog.favorites.CatalogFavoritesUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel for managing and exposing the state of favorite catalog items.
 *
 * @author marlonlom
 *
 * @param repository The repository handling favorite catalog data operations.
 */
class CatalogFavoritesViewModel(private val repository: CatalogFavoritesRepository) : ViewModel() {

  /** A state flow representing the current UI state of favorite catalog items. */
  val uiState = repository.favoritesList.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000L),
    initialValue = CatalogFavoritesUiState.Loading,
  )

  /**
   * Removes the given catalog item from the favorites list.
   *
   * @param catalogId The ID of the catalog item to remove.
   */
  fun undoFavorite(catalogId: Long) {
    viewModelScope.launch {
      repository.undoFavorite(catalogId)
    }
  }
}
