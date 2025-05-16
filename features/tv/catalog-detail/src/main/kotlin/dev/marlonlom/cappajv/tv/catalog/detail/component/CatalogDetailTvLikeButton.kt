/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.catalog.detail.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material.icons.twotone.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButtonDefaults
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.tv.catalog.detail.R

/**
 * A composable Like button optimized for TV in the catalog item detail view.
 *
 * @author marlonlom
 *
 * @param item The catalog item associated with this button.
 * @param isFavorite Current favorite status of the item.
 * @param onFavoriteChanged Callback invoked when the favorite status changes,
 *        passing the item and the new status.
 */
@Composable
internal fun CatalogDetailTvLikeButton(
  item: CatalogItem,
  isFavorite: Boolean,
  onFavoriteChanged: (CatalogItem, Boolean) -> Unit
) = IconButton(
  modifier = Modifier.padding(top = 20.dp),
  onClick = {
    onFavoriteChanged(item, !isFavorite)
  },
  border = OutlinedButtonDefaults.border(
    border = Border(BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)),
    focusedBorder = Border(BorderStroke(1.dp, MaterialTheme.colorScheme.onTertiaryContainer)),
  ),
  colors = OutlinedButtonDefaults.colors(
    contentColor = MaterialTheme.colorScheme.onBackground,
    containerColor = MaterialTheme.colorScheme.background,
    focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
    focusedContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
  ),
  content = {
    (if (isFavorite) Icons.Rounded.ThumbUp else Icons.TwoTone.ThumbUp).also {
      Icon(
        imageVector = it,
        contentDescription = stringResource(R.string.text_detail_toggle_favorite_desc),
        modifier = Modifier.size(ButtonDefaults.IconSize),
      )
    }
  }
)

