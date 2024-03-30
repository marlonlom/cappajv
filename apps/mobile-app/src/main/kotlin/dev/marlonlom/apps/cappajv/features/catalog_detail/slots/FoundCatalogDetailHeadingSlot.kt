/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail.slots

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.apps.cappajv.features.catalog_detail.parts.CatalogDetailButtonsBar
import dev.marlonlom.apps.cappajv.features.catalog_detail.parts.CatalogDetailImage
import dev.marlonlom.apps.cappajv.features.catalog_detail.parts.CatalogDetailProductCategoryText
import dev.marlonlom.apps.cappajv.features.catalog_detail.parts.CatalogDetailProductDescriptionText
import dev.marlonlom.apps.cappajv.features.catalog_detail.parts.CatalogDetailProductTitle
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * Found catalog detail heading slot composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 * @param product Catalog detailed information.
 * @param isFavorite True/False if catalog detail item is favorite.
 * @param onCatalogItemFavoriteChanged Action for catalog detail item favorite state changed.
 */
@Composable
fun FoundCatalogDetailHeadingSlot(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  product: CatalogItem,
  isFavorite: Boolean,
  onCatalogItemFavoriteChanged: (CatalogItem, Boolean) -> Unit,
) = Column(
  horizontalAlignment = Alignment.CenterHorizontally,
) {
  CatalogDetailImage(
    title = product.title,
    picture = product.picture
  )
  CatalogDetailProductTitle(product)
  CatalogDetailProductCategoryText(product)
  CatalogDetailButtonsBar(
    appState = appState,
    appContentCallbacks = appContentCallbacks,
    product = product,
    isFavorite = isFavorite,
    onCatalogItemFavoriteChanged = onCatalogItemFavoriteChanged,
  )
  CatalogDetailProductDescriptionText(product)
}
