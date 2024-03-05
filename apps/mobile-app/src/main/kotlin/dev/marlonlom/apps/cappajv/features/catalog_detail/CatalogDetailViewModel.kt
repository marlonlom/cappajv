/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Catalog detail view model class.
 *
 * @author marlonlom
 *
 * @property repository catalog detail repository dependency
 */
class CatalogDetailViewModel(
  private val repository: CatalogDetailRepository
) : ViewModel() {

  var detail: MutableState<CatalogDetail?> = mutableStateOf(null)
    private set

  suspend fun find(itemId: Long) {
    viewModelScope.launch {
      repository.find(itemId).collect {
        detail.value = it
      }
    }
  }

}
