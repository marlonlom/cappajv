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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

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
  val queryText = mutableStateOf("")

  private val _searchResult = MutableStateFlow<CatalogSearchUiState>(None)

  val searchResult = _searchResult.stateIn(
    scope = viewModelScope,
    started = SharingStarted.Eagerly,
    initialValue = None
  )

  fun onQueryTextChanged() {
    viewModelScope.launch {
      if (queryText.value.isNotEmpty()) {
        _searchResult.value = Searching
        delay(1_000)
        performSearch()
      } else {
        _searchResult.value = None
      }
    }
  }

  private suspend fun performSearch() {
    Timber.d("[CatalogSearchViewModel] performSearch(${queryText.value})")
    repository.performSearch(queryText.value).collect { list ->
      _searchResult.update {
        when {
          list.isEmpty() -> Empty
          else -> Success(list)
        }
      }
    }
  }

}
