/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.home.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple

/**
 * Composable function that displays a single item in a catalog grid.
 * It shows an image, title, and applies basic styling.
 *
 * @author marlonlom
 *
 * @param item A [CatalogItemTuple] containing the data to display for the item,
 * including the image URL and title.
 * @param onCatalogGridItemClicked A callback function that is invoked when this
 * catalog grid item is clicked.
 * @param imageLoader The [ImageLoader] used to fetch and display the image.
 */
@Composable
internal fun CatalogGridItem(
  item: CatalogItemTuple,
  onCatalogGridItemClicked: (Long) -> Unit,
  imageLoader: ImageLoader = LocalContext.current.imageLoader,
) = Column(
  modifier = Modifier
    .testTag("catalog_home_cell_${item.id}")
    .clickable(
      interactionSource = remember { MutableInteractionSource() },
      indication = null,
      onClick = { onCatalogGridItemClicked(item.id) },
    ),
) {
  AsyncImage(
    imageLoader = imageLoader,
    model = ImageRequest.Builder(LocalContext.current)
      .data(item.picture)
      .crossfade(true)
      .placeholder(MaterialTheme.colorScheme.secondaryContainer.value.toInt())
      .build(),
    contentDescription = item.title,
    contentScale = ContentScale.Crop,
    modifier = Modifier
      .fillMaxWidth()
      .border(
        width = 1.dp,
        color = MaterialTheme.colorScheme.secondary,
        shape = MaterialTheme.shapes.small,
      )
      .aspectRatio(5f / 7f)
      .clip(MaterialTheme.shapes.small),
  )

  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 10.dp),
    text = item.title,
    fontWeight = FontWeight.Bold,
    color = MaterialTheme.colorScheme.secondary,
    maxLines = 1,
    overflow = TextOverflow.Ellipsis,
  )
}
