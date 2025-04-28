/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.domain

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
  data class Found(val detail: CatalogDetailItem) : CatalogDetailUiState()
}
