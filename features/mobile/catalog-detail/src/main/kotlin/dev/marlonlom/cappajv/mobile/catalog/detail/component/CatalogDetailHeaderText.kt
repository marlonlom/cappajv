/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.core.database.entities.CatalogItem

/**
 * A Composable function that displays the header text for the catalog detail screen.
 *
 * @author marlonlom
 *
 * @param catalogItem The [CatalogItem] containing the information to display in the header.
 * @param isCenterAligned A Boolean flag that determines whether the text should be center-aligned.
 */
@Composable
internal fun CatalogDetailHeaderText(catalogItem: CatalogItem, isCenterAligned: Boolean = false) {
  buildAnnotatedString {
    withStyle(
      SpanStyle(
        fontWeight = FontWeight.Normal,
        fontSize = MaterialTheme.typography.titleSmall.fontSize,
      ),
    ) {
      append(catalogItem.category)
    }
    appendLine()
    withStyle(
      SpanStyle(
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleLarge.fontSize,
      ),
    ) {
      append(catalogItem.title)
    }
  }.also {
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp, bottom = if (isCenterAligned) 20.dp else 10.dp),
      color = MaterialTheme.colorScheme.secondary,
      textAlign = if (isCenterAligned) TextAlign.Center else TextAlign.Start,
      text = it,
    )
  }
}
