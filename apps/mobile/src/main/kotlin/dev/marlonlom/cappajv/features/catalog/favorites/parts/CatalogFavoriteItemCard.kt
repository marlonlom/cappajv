/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.catalog.favorites.parts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.RemoveCircleOutline
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.marlonlom.cappajv.R
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple

/**
 * Catalog favorite item card composable ui.
 *
 * @author marlonlom
 *
 * @param row Catalog item tuple.
 * @param isRouting True/False if should navigate through routing.
 * @param onFavoriteItemClicked Action for favorite item clicked.
 * @param onFavoriteItemRemoved Action for favorite item removed.
 * @param modifier Modifier for this composable.
 */
@Composable
fun CatalogFavoriteItemCard(
  row: CatalogItemTuple,
  isRouting: Boolean,
  onFavoriteItemClicked: (Long, Boolean) -> Unit,
  onFavoriteItemRemoved: (Long) -> Unit,
  modifier: Modifier = Modifier,
) {
  OutlinedCard(
    onClick = {
      onFavoriteItemClicked(row.id, isRouting)
    },
    border = BorderStroke(0.dp, Color.Transparent),
    modifier = modifier.fillMaxWidth(),
    shape = CardDefaults.outlinedShape,
    colors = CardDefaults.outlinedCardColors(),
  ) {
    Row(
      modifier = modifier.padding(
        horizontal = 10.dp,
        vertical = 4.dp,
      ),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      CatalogFavoriteItemCardImage(row, modifier)
      CatalogFavoriteItemCardText(row)
      Spacer(Modifier.weight(1f))
      CatalogFavoriteItemCardRemoveIconButton(row, onFavoriteItemRemoved, modifier)
    }
  }
}

/**
 * Catalog favorite item card remove icon button composable.
 *
 * @author marlonlom
 *
 * @param tuple Catalog item tuple.
 * @param onFavoriteItemRemoved Action for favorite item removed.
 * @param modifier Modifier for this composable.
 */
@Composable
private fun CatalogFavoriteItemCardRemoveIconButton(
  tuple: CatalogItemTuple,
  onFavoriteItemRemoved: (Long) -> Unit,
  modifier: Modifier = Modifier,
) = IconButton(onClick = { onFavoriteItemRemoved(tuple.id) }) {
  Icon(
    imageVector = Icons.Rounded.RemoveCircleOutline,
    contentDescription = stringResource(R.string.text_catalog_favorites_success_remove_icon_hint),
    modifier = modifier.size(ButtonDefaults.IconSize),
    tint = MaterialTheme.colorScheme.secondary,
  )
}

/**
 * Catalog favorite item card text composable.
 *
 * @author marlonlom
 *
 * @param tuple Catalog item tuple.
 */
@Composable
private fun CatalogFavoriteItemCardText(tuple: CatalogItemTuple) = Column {
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

/**
 * Catalog favorite item card image composable.
 *
 * @author marlonlom
 *
 * @param tuple Catalog item tuple.
 * @param modifier Modifier for this composable.
 */
@Composable
private fun CatalogFavoriteItemCardImage(tuple: CatalogItemTuple, modifier: Modifier = Modifier) {
  val imageRequest = ImageRequest.Builder(LocalContext.current).data(tuple.picture).crossfade(true).build()

  val imageModifier = modifier
    .border(
      width = 2.dp,
      color = MaterialTheme.colorScheme.secondary,
      shape = MaterialTheme.shapes.medium,
    )
    .clip(MaterialTheme.shapes.medium)
    .size(64.dp)
    .background(Color.White)

  AsyncImage(
    model = imageRequest,
    contentDescription = tuple.title,
    contentScale = ContentScale.Crop,
    modifier = imageModifier,
  )
}
