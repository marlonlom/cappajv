/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail.parts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItem

@Composable
internal fun CatalogDetailProductDescriptionText(product: CatalogItem) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 10.dp),
    textAlign = TextAlign.Center,
    text = product.detail
  )
}

@Composable
internal fun CatalogDetailProductCategoryText(product: CatalogItem) {
  Text(
    modifier = Modifier.fillMaxWidth(),
    textAlign = TextAlign.Center,
    text = product.category,
    style = MaterialTheme.typography.bodyMedium
  )
}

@Composable
internal fun CatalogDetailProductTitle(
  product: CatalogItem,
  topBaselinePadding: Dp = 60.dp,
  titleTextStyle: TextStyle = MaterialTheme.typography.headlineLarge
) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .paddingFromBaseline(topBaselinePadding, 10.dp),
    textAlign = TextAlign.Center,
    text = product.title,
    fontWeight = FontWeight.Bold,
    style = titleTextStyle
  )
}

