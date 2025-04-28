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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.mobile.catalog.detail.R

/**
 * A Composable function that displays the description text for the catalog detail screen.
 *
 * @author marlonlom
 *
 * @param catalogItem The [CatalogItem] containing the information to display in the header.
 */
@Composable
internal fun CatalogDetailDescriptionText(catalogItem: CatalogItem) {
  buildAnnotatedString {
    withStyle(
      SpanStyle(
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleMedium.fontSize,
      ),
    ) {
      append(stringResource(R.string.text_description))
    }
    appendLine()
    withStyle(
      SpanStyle(
        fontWeight = FontWeight.Normal,
        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
      ),
    ) {
      append(catalogItem.detail)
    }
  }.also {
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 20.dp),
      color = MaterialTheme.colorScheme.secondary,
      text = it,
    )
  }
}
