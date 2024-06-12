/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.catalog_detail.slots

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.features.catalog_detail.parts.CatalogDetailButtonsBar
import dev.marlonlom.cappajv.features.catalog_detail.parts.CatalogDetailImage
import dev.marlonlom.cappajv.features.catalog_detail.parts.CatalogDetailProductCategoryText
import dev.marlonlom.cappajv.features.catalog_detail.parts.CatalogDetailProductTitle
import dev.marlonlom.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.cappajv.ui.main.CappajvAppState

/**
 * CompactTabletopCatalogDetailHeadingSlot
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 * @param product Catalog detailed information.
 * @param isFavorite True/False if catalog detail item is favorite.
 * @param onCatalogItemFavoriteChanged Action for catalog detail item favorite state changed.
 * @param hingeRatio Hinge ratio for column height separation.
 * @param contentHorizontalPadding Content horizontal padding.
 * @param modifier Modifier for this composable.
 */
@Composable
internal fun CompactTableTopFoundCatalogDetailHeadingSlot(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  product: CatalogItem,
  isFavorite: Boolean,
  onCatalogItemFavoriteChanged: (CatalogItem, Boolean) -> Unit,
  hingeRatio: Float,
  contentHorizontalPadding: Dp,
  modifier: Modifier = Modifier
) = Column(
  modifier = modifier
    .fillMaxHeight(hingeRatio)
    .padding(horizontal = contentHorizontalPadding),
  verticalArrangement = Arrangement.spacedBy(10.dp),
  horizontalAlignment = Alignment.CenterHorizontally,
) {
  CatalogDetailImage(appState, product.title, product.picture)
  CatalogDetailProductTitle(product, 30.dp, MaterialTheme.typography.titleLarge)
  CatalogDetailProductCategoryText(product)
  CatalogDetailButtonsBar(
    appState = appState,
    appContentCallbacks = appContentCallbacks,
    product = product,
    isFavorite = isFavorite,
    onCatalogItemFavoriteChanged = onCatalogItemFavoriteChanged,
  )
}
