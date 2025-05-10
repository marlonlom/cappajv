/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest

/**
 * Displays a TV-style catalog item card with an image and title.
 *
 * @param itemId Unique identifier for the catalog item.
 * @param itemTitle Title of the catalog item.
 * @param itemPhoto URL or resource path of the item's image.
 * @param onItemClicked Callback invoked when the item is clicked, providing the item ID.
 * @param imageLoader Optional custom image loader; defaults to the local context's image loader.
 */
@Composable
fun CatalogItemTvCard(
  itemId: Long,
  itemTitle: String,
  itemPhoto: String,
  onItemClicked: (catalogId: Long) -> Unit,
  onItemLongClicked: (() -> Unit)? = null,
  imageLoader: ImageLoader = LocalContext.current.imageLoader,
) = Card(
  onClick = { onItemClicked(itemId) },
  onLongClick = onItemLongClicked,
  modifier = Modifier
    .testTag("catalog_card_$itemId")
    .width(124.dp),
  border = CardDefaults.border(
    focusedBorder = Border(
      border = BorderStroke(
        width = 1.dp,
        color = MaterialTheme.colorScheme.onSurface,
      ),
      shape = MaterialTheme.shapes.small,
    ),
  ),
  scale = CardDefaults.scale(
    scale = 0.9f,
    focusedScale = 1.0f,
  ),
) {
  Column {
    AsyncImage(
      imageLoader = imageLoader,
      model = ImageRequest.Builder(LocalContext.current).data(itemPhoto).crossfade(true)
        .error(MaterialTheme.colorScheme.errorContainer.value.toInt())
        .placeholder(MaterialTheme.colorScheme.secondaryContainer.value.toInt()).build(),
      contentDescription = itemTitle,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(3f / 4)
        .clip(MaterialTheme.shapes.small.copy(bottomEnd = CornerSize(0.dp), bottomStart = CornerSize(0.dp))),
    )

    Text(
      modifier = Modifier.padding(10.dp),
      text = itemTitle,
      color = MaterialTheme.colorScheme.onSurface,
      minLines = 2,
      maxLines = 2,
      style = MaterialTheme.typography.bodySmall,
      overflow = TextOverflow.Ellipsis,
    )
  }
}
