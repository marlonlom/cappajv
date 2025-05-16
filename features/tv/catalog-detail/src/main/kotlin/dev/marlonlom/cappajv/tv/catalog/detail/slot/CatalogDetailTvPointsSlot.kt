/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.catalog.detail.slot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation

/**
 * Displays a list of punctuation points in the catalog detail view for TV.
 *
 * @author marlonlom
 *
 * @param points A list of [CatalogPunctuation] representing the points to display.
 */
@Composable
internal fun CatalogDetailTvPointsSlot(points: List<CatalogPunctuation>) = Row(
  modifier = Modifier.padding(top = 10.dp),
  horizontalArrangement = Arrangement.spacedBy(10.dp)
) {
  points.forEach { punctuation ->
    Column(
      modifier = Modifier
        .width(84.dp)
        .padding(vertical = 10.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
        text = "${punctuation.points}",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground
      )
      when {
        punctuation.label.indexOfFirst { t -> t == '(' } >= 0 -> punctuation.label.substring(
          0,
          punctuation.label.indexOfFirst { t -> t == '(' },
        )

        else -> punctuation.label
      }.trim().also {
        Text(
          modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
          text = it,
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onBackground,
          textAlign = TextAlign.Center,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis
        )
      }
    }
  }
}
