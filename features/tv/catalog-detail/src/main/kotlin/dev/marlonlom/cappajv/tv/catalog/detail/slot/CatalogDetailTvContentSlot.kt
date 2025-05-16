/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.catalog.detail.slot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.ImageLoader
import coil.imageLoader
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation
import dev.marlonlom.cappajv.tv.catalog.detail.component.CatalogDetailTvLikeButton
import dev.marlonlom.cappajv.tv.designsystem.component.CatalogItemTvImage

/**
 * Internal composable function to display the TV-specific content slot for a catalog item's detail screen.
 *
 * @param item The [CatalogItem] to display details for.
 * @param points A list of [CatalogPunctuation] associated with the item.
 * @param isFavorite Indicates if the item is currently marked as a favorite.
 * @param onFavoriteChanged Callback triggered when the favorite state of the item changes.
 * @param modifier Optional [Modifier] for styling and layout adjustments.
 * @param imageLoader Custom [ImageLoader] for loading the image.
 */
@Composable
internal fun CatalogDetailTvContentSlot(
  item: CatalogItem,
  points: List<CatalogPunctuation>,
  isFavorite: Boolean,
  onFavoriteChanged: (CatalogItem, Boolean) -> Unit,
  modifier: Modifier = Modifier,
  imageLoader: ImageLoader = LocalContext.current.imageLoader,
) {
  Column(modifier = modifier) {
    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
      CatalogItemTvImage(
        itemPicture = item.picture,
        itemTitle = item.title,
        imageWidth = 100.dp,
        imageLoader = imageLoader,
      )

      Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
      ) {
        Text(
          modifier = Modifier
            .background(Color.Transparent)
            .padding(vertical = 4.dp),
          text = item.category,
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        )
        Text(
          modifier = Modifier.padding(top = 4.dp),
          text = item.title,
          style = MaterialTheme.typography.headlineMedium,
          color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
          modifier = Modifier.padding(top = 10.dp),
          text = item.detail,
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onBackground,
        )

        CatalogDetailTvLikeButton(
          item = item,
          isFavorite = isFavorite,
          onFavoriteChanged = onFavoriteChanged,
        )
      }
    }

    CatalogDetailTvPointsSlot(
      points = points,
    )

    Spacer(Modifier.height(48.dp))
  }
}
