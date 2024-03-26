/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_search.parts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItemTuple

/**
 * Catalog searched item card composable ui.
 *
 * @author marlonlom
 *
 * @param tuple Catalog item tuple.
 * @param isRouting True/False if should navigate through routing.
 * @param onSearchedItemClicked Action for searched item clicked.
 * @param modifier Modifier for this composable.
 */
@Composable
fun CatalogSearchedItemCard(
  tuple: CatalogItemTuple,
  isRouting: Boolean,
  onSearchedItemClicked: (Long, Boolean) -> Unit,
  modifier: Modifier = Modifier,
) {
  OutlinedCard(
    onClick = {
      onSearchedItemClicked(tuple.id, isRouting)
    },
    border = BorderStroke(0.dp, Color.Transparent),
    modifier = modifier.fillMaxWidth(),
    shape = CardDefaults.outlinedShape,
    colors = CardDefaults.outlinedCardColors(),
  ) {
    Row(
      modifier = modifier.padding(
        horizontal = 10.dp, vertical = 4.dp
      ),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(tuple.picture).crossfade(true).build()

      AsyncImage(
        model = imageRequest,
        contentDescription = tuple.title,
        contentScale = ContentScale.Crop,
        modifier = modifier
          .border(
            width = 2.dp,
            color = MaterialTheme.colorScheme.secondary,
            shape = MaterialTheme.shapes.medium,
          )
          .clip(MaterialTheme.shapes.medium)
          .size(64.dp)
          .background(Color.White),
      )

      Column {
        Text(
          text = tuple.title,
          style = MaterialTheme.typography.titleLarge,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          fontWeight = FontWeight.SemiBold,
        )
        Text(
          text = tuple.category,
          style = MaterialTheme.typography.bodyLarge,
        )
      }
    }
  }
}
