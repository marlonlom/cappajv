/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CompactCard
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple

/**
 * Catalog grid item common compact card composable ui.
 *
 * @author marlonlom
 *
 * @param catalogItem Grid catalog item.
 * @param onCatalogItemClicked Action for Grid catalog item selected.
 * @param modifier The modifier for this composable.
 */
@Composable
fun CatalogGridItemCompactCard(
  catalogItem: CatalogItemTuple,
  onCatalogItemClicked: (CatalogItemTuple) -> Unit,
  modifier: Modifier = Modifier,
) = CompactCard(
  modifier = modifier
    .background(
      MaterialTheme.colorScheme.background,
    ),
  image = {
    AsyncImage(
      model = ImageRequest.Builder(LocalContext.current)
        .data(catalogItem.picture)
        .crossfade(true)
        .build(),
      contentDescription = catalogItem.title,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .aspectRatio(2 / 3f)
        .padding(4.dp)
        .padding(horizontal = 1.dp)
        .padding(bottom = 40.dp)
        .clip(RoundedCornerShape(8.dp)),
    )
  },
  title = {
    Text(
      text = catalogItem.title,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.titleMedium,
      modifier = modifier
        .fillMaxWidth()
        .padding(10.dp),
    )
  },
  onClick = {
    onCatalogItemClicked(catalogItem)
  },
)
