/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation

/**
 * Displays the punctuation details for a catalog item.
 *
 * This internal composable function takes a [CatalogPunctuation] object
 * and renders the associated punctuation information.
 *
 * @author marlonlom
 *
 * @param punctuation The [CatalogPunctuation] object containing the punctuation details to display.
 */
@Composable
internal fun CatalogDetailPunctuationBox(punctuation: CatalogPunctuation) = Column(
  modifier = Modifier
    .fillMaxWidth(0.45f)
    .background(
      color = MaterialTheme.colorScheme.secondaryContainer,
      shape = MaterialTheme.shapes.medium,
    )
    .border(
      width = 1.dp,
      color = MaterialTheme.colorScheme.secondary,
      shape = MaterialTheme.shapes.medium,
    )
    .padding(all = 10.dp),
  horizontalAlignment = Alignment.CenterHorizontally,
  verticalArrangement = Arrangement.Center,
) {
  Text(
    text = punctuation.points.toString(),
    fontWeight = FontWeight.Bold,
    maxLines = 1,
    textAlign = TextAlign.Center,
    color = MaterialTheme.colorScheme.secondary,
    style = MaterialTheme.typography.headlineMedium,
  )
  val punctuationTitle = when {
    punctuation.label.indexOf("(") > 0 -> punctuation.label.substring(0, punctuation.label.indexOf("("))
    else -> punctuation.label
  }.trim()
  Text(
    text = punctuationTitle,
    textAlign = TextAlign.Center,
    color = MaterialTheme.colorScheme.secondary,
    style = MaterialTheme.typography.bodyMedium,
    maxLines = 2,
  )
}
