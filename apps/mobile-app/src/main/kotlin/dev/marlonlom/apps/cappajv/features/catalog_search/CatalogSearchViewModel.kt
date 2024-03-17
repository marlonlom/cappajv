/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.apps.cappajv.features.catalog_search.CatalogSearchUiState.Empty
import dev.marlonlom.apps.cappajv.features.catalog_search.CatalogSearchUiState.None
import dev.marlonlom.apps.cappajv.features.catalog_search.CatalogSearchUiState.Searching
import dev.marlonlom.apps.cappajv.features.catalog_search.CatalogSearchUiState.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Catalog search viewmodel.
 *
 * @author marlonlom
 *
 * @property repository Catalog search repository.
 */
class CatalogSearchViewModel(
  private val repository: CatalogSearchRepository
) : ViewModel() {
  var queryText = mutableStateOf("")
    private set

  private val _searchResult = MutableStateFlow<CatalogSearchUiState>(None)

  val searchResult = _searchResult.stateIn(
    scope = viewModelScope,
    started = SharingStarted.Eagerly,
    initialValue = None
  )

  /** Handles query text value change. */
  fun onQueryTextChanged() {
    _searchResult.value = if (queryText.value.isNotEmpty()) Searching else None
    if (_searchResult.value is None) return
    viewModelScope.launch {
      _searchResult.update {
        val searchResults = repository.performSearch(queryText.value).first()
        if (searchResults.isEmpty()) Empty else Success(searchResults)
      }
    }
  }

}
