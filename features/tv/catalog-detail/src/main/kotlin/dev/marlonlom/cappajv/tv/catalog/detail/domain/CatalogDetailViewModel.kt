/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.catalog.detail.domain

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.cappajv.core.database.entities.CatalogFavoriteItem
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.domain.catalog.detail.CatalogDetailRepository
import dev.marlonlom.cappajv.domain.catalog.detail.CatalogDetailUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * A [ViewModel] responsible for fetching and managing the details of a catalog item.
 * It observes a catalog item ID from [SavedStateHandle] and retrieves the corresponding
 * details from the [CatalogDetailRepository]. It also handles toggling the favorite status
 * of a catalog item.
 *
 * @author marlonlom
 *
 * @property repository The [CatalogDetailRepository] used to access catalog item data.
 * @property savedStateHandle The [SavedStateHandle] used to retrieve and store state,
 * such as the current catalog item ID.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class CatalogDetailViewModel(
  private val repository: CatalogDetailRepository,
  private val savedStateHandle: SavedStateHandle = SavedStateHandle(),
) : ViewModel() {

  /**
   * A StateFlow representing the current UI state for the catalog item details.
   * It emits [CatalogDetailUiState] values based on the result of fetching the
   * catalog item with the ID obtained from the [SavedStateHandle].
   *
   * It starts with `CatalogDetailUiState.Loading`, transitions to
   * `CatalogDetailUiState.Found` with the `CatalogDetailItem` if successful,
   * or to `CatalogDetailUiState.NotFound` if the item is not found.
   */
  val uiState = savedStateHandle.getStateFlow(CATALOG_DETAIL_ID_KEY, NO_CATALOG_ID)
    .flatMapLatest { catalogId ->
      repository.find(catalogId)
    }.mapLatest { detail ->
      if (detail == null) CatalogDetailUiState.NotFound else CatalogDetailUiState.Found(detail)
    }.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000L),
      initialValue = CatalogDetailUiState.Loading,
    )

  /**
   * Sets the ID of the catalog item to be retrieved. This ID is stored in the [SavedStateHandle]
   * and will trigger an update to the [uiState] Flow.
   *
   * @param itemId The ID of the catalog item to find.
   */
  fun find(itemId: Long) {
    savedStateHandle[CATALOG_DETAIL_ID_KEY] = itemId
  }

  /**
   * Clears the currently selected catalog item ID, effectively navigating away from the details screen.
   */
  fun unFind() {
    savedStateHandle[CATALOG_DETAIL_ID_KEY] = LOADING_CATALOG_ID
  }

  /**
   * Toggles the favorite status of a given [CatalogItem].
   *
   * @param product The [CatalogItem] to mark or unmark as a favorite.
   * @param isFavorite `true` if the item should be marked as a favorite, `false` otherwise.
   */
  fun toggleFavorite(product: CatalogItem, isFavorite: Boolean) {
    viewModelScope.launch {
      if (isFavorite) {
        val favoriteItem = product.let {
          CatalogFavoriteItem(
            it.id,
            it.title,
            it.picture,
            it.category,
            it.samplePunctuation,
            it.punctuationsCount,
          )
        }
        repository.saveFavorite(favoriteItem)
      } else {
        repository.undoFavorite(product.id)
      }
    }
  }

  companion object {

    /** Constant for default catalog item id. */
    private const val NO_CATALOG_ID = 0L

    /** Constant for catalog item id to be loading. */
    private const val LOADING_CATALOG_ID = -1L

    /** Constant for default catalog item id key. */
    private const val CATALOG_DETAIL_ID_KEY = "CATALOG_DETAIL_ID"
  }
}
