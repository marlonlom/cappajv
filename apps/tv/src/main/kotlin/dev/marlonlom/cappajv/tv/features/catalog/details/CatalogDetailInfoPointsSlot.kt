/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CompactCard
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation
import dev.marlonlom.cappajv.tv.R

/**
 * Catalog detail information points slot composable ui.
 *
 * @author marlonlom
 *
 * @param points List of catalog points for an item.
 */
@Composable
internal fun CatalogDetailInfoPointsSlot(points: List<CatalogPunctuation>) = Column(
  modifier = Modifier
    .fillMaxWidth()
    .padding(bottom = 40.dp),
  horizontalAlignment = Alignment.Start,
) {
  Text(
    modifier = Modifier
      .padding(vertical = 10.dp),
    textAlign = TextAlign.Start,
    style = MaterialTheme.typography.titleMedium,
    text = stringResource(R.string.text_detail_subtitle_points),
    color = MaterialTheme.colorScheme.onSurface,
    fontWeight = FontWeight.Bold,
  )
  LazyRow(
    modifier = Modifier.align(Alignment.Start),
  ) {
    items(
      items = points,
      key = { pt -> pt.id },
    ) { pt ->
      CompactCard(
        modifier = Modifier
          .width(160.dp)
          .padding(horizontal = 10.dp),
        onClick = {},
        title = {
          Text(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 20.dp)
              .padding(top = 10.dp, bottom = 0.dp),
            text = "${pt.points}",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
          )
        },
        subtitle = {
          val subtitleText = when {
            pt.label.indexOfFirst { t -> t == '(' } >= 0 -> pt.label.substring(
              0,
              pt.label.indexOfFirst { t -> t == '(' },
            )

            else -> pt.label
          }.trim()

          Text(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 20.dp)
              .padding(bottom = 10.dp),
            text = subtitleText,
            maxLines = 1,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
          )
        },
        image = {},
      )
    }
  }
}
