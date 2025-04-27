/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.favorites.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.mobile.catalog.favorites.R

/**
 * Composable function that displays a single item in a catalog grid.
 * It shows an image, title, and applies basic styling.
 *
 * @author marlonlom
 *
 * @param item A [CatalogItemTuple] containing the data to display for the item,
 * including the image URL and title.
 * @param onItemLongClicked A callback function that is invoked when this
 * catalog grid item is clicked.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun CatalogGridItem(
  item: CatalogItemTuple,
  onItemClicked: (Long) -> Unit,
  onItemLongClicked: (CatalogItemTuple) -> Unit,
) = Column(
  modifier = Modifier
    .testTag("catalog_favorite_cell_${item.id}")
    .combinedClickable(
      interactionSource = remember { MutableInteractionSource() },
      indication = null,
      onClick = { onItemClicked(item.id) },
      onLongClick = { onItemLongClicked(item) },
      onLongClickLabel = stringResource(R.string.text_undo_favorite_title),
    ),
) {
  AsyncImage(
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
