/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.OutlinedButtonDefaults
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.tv.R

/**
 * Catalog detail heading slot composable ui.
 *
 * @author marlonlom
 *
 * @param foundDetail Catalog found item details.
 * @param onMarkedFavorite Action for item favorite state changed.
 * @param modifier The modifier for this composable.
 */
@Composable
internal fun CatalogDetailHeadingInfoSlot(
  foundDetail: CatalogDetail,
  onMarkedFavorite: (CatalogItem, Boolean) -> Unit,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier
      .fillMaxWidth(0.5f)
      .padding(vertical = 10.dp),
    horizontalArrangement = Arrangement.spacedBy(20.dp),
    verticalAlignment = Alignment.Bottom,
  ) {
    AsyncImage(
      model = ImageRequest.Builder(LocalContext.current).data(foundDetail.product.picture).crossfade(true).build(),
      contentDescription = "",
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .size(124.dp, 160.dp)
        .background(Color.White, RoundedCornerShape(20.dp))
        .aspectRatio(2f / 3F)
        .clip(RoundedCornerShape(20.dp))
        .clipToBounds(),
    )

    Column(
      modifier = modifier.fillMaxWidth(),
    ) {
      Text(
        modifier = modifier.padding(bottom = 5.dp),
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.titleLarge,
        text = foundDetail.product.title,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface,
      )
      Text(
        modifier = modifier.padding(bottom = 20.dp),
        text = foundDetail.product.category,
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurface,
      )
      OutlinedButton(
        onClick = {
          onMarkedFavorite(foundDetail.product, !foundDetail.isFavorite)
        },
        colors = OutlinedButtonDefaults.colors(
          contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
          containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
      ) {
        (if (foundDetail.isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder).also {
          Icon(
            imageVector = it,
            contentDescription = stringResource(R.string.text_detail_toggle_favorite_desc),
            modifier = Modifier.size(ButtonDefaults.IconSize),
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
          )
        }
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(
          text = stringResource(R.string.text_detail_label_like),
          color = MaterialTheme.colorScheme.onPrimaryContainer,
          style = MaterialTheme.typography.labelSmall,
        )
      }
    }
  }
}
