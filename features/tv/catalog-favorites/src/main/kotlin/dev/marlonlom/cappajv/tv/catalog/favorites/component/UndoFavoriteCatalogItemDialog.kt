/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.catalog.favorites.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import androidx.tv.material3.WideButton
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.tv.catalog.favorites.R

/**
 * Displays a confirmation dialog to remove the catalog item from the favorite list screen.
 *
 * @author marlonlom
 *
 *
 * @param item The [CatalogItemTuple] representing the item to undo favoriting for.
 * @param onConfirm Callback to be invoked when the user confirms the undo action.
 * @param onDismiss Callback to be invoked when the user dismisses the dialog.
 */
@Composable
internal fun UndoFavoriteCatalogItemDialog(
  item: CatalogItemTuple,
  onConfirm: () -> Unit,
  onDismiss: () -> Unit,
  imageLoader: ImageLoader = LocalContext.current.imageLoader,
) = Row(
  modifier = Modifier
    .fillMaxSize()
    .background(MaterialTheme.colorScheme.background)
    .padding(32.dp),
  verticalAlignment = Alignment.CenterVertically,
  horizontalArrangement = Arrangement.SpaceBetween,
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
  ) {
    AsyncImage(
      imageLoader = imageLoader,
      model = ImageRequest.Builder(LocalContext.current).data(item.picture).crossfade(true)
        .error(MaterialTheme.colorScheme.errorContainer.value.toInt())
        .placeholder(MaterialTheme.colorScheme.secondaryContainer.value.toInt()).build(),
      contentDescription = item.title,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .width(124.dp)
        .aspectRatio(3f / 4)
        .clip(MaterialTheme.shapes.small)
        .border(1.dp, MaterialTheme.colorScheme.secondary, MaterialTheme.shapes.small),
    )
    Spacer(modifier = Modifier.width(20.dp))
    Column(Modifier.padding(vertical = 20.dp)) {
      Text(
        text = stringResource(R.string.text_undo_favorite_title),
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge,
      )
      Text(
        modifier = Modifier.padding(top = 10.dp),
        text = stringResource(R.string.text_undo_favorite_detail, item.title),
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Normal,
        style = MaterialTheme.typography.bodySmall,
      )
    }
    Spacer(modifier = Modifier.weight(1.0f))
    Column(
      modifier = Modifier
        .padding(20.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      WideButton(
        onClick = { onConfirm() },
      ) {
        Text(
          text = stringResource(R.string.text_undo_favorite_confirm),
          style = MaterialTheme.typography.bodySmall,
        )
      }
      WideButton(onClick = { onDismiss() }) {
        Text(
          text = stringResource(R.string.text_undo_favorite_dismiss),
          style = MaterialTheme.typography.bodySmall,
        )
      }
    }
  }
}
