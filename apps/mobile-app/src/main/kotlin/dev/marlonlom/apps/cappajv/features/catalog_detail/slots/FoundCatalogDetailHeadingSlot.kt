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


@Composable
fun FoundCatalogDetailHeadingSlot(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  product: CatalogItem,
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
    product = product,
    appContentCallbacks = appContentCallbacks,
  )
  CatalogDetailProductDescriptionText(product)
}
