/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailUiState.Found
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailUiState.NotFound
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

/**
 * Catalog detail view model class.
 *
 * @author marlonlom
 *
 * @property repository catalog detail repository dependency
 * @property savedStateHandle Saved state handle for the viewmodel.
 */
@ExperimentalCoroutinesApi
class CatalogDetailViewModel(
  private val repository: CatalogDetailRepository,
  private val savedStateHandle: SavedStateHandle = SavedStateHandle()
) : ViewModel() {

  /** Catalog detailed item value as state flow. */
  val detail = savedStateHandle.getStateFlow(CATALOG_DETAIL_ID_KEY, NO_CATALOG_ID)
    .flatMapLatest { catalogId ->
      repository.find(catalogId)
    }.mapLatest { detail ->
      if (detail == null) NotFound else Found(detail)
    }.stateIn(
      scope = viewModelScope,
      started = SharingStarted.Eagerly,
      initialValue = NotFound
    )

  /**
   * Perform detailed search using catalog selected id.
   *
   * @param itemId Catalog item id value.
   */
  fun find(itemId: Long) {
    savedStateHandle[CATALOG_DETAIL_ID_KEY] = itemId
  }

  companion object {

    private const val NO_CATALOG_ID = 0L

    private const val CATALOG_DETAIL_ID_KEY = "CATALOG_DETAIL_ID"

  }

}

/**
 * Catalog details ui state sealed class.
 *
 * @author marlonlom
 */
sealed class CatalogDetailUiState {

  /**
   * Not found phase for catalog details ui state.
   *
   * @author marlonlom
   */
  data object NotFound : CatalogDetailUiState()

  /**
   * Success phase for catalog details ui state.
   *
   * @author marlonlom
   *
   * @property detail Catalog item detailed information.
   */
  data class Found(
    val detail: CatalogDetail
  ) : CatalogDetailUiState()
}
