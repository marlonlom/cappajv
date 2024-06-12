/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.catalog_detail.parts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.R
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.cappajv.ui.main.CappajvAppState

/**
 * Catalog details buttons bar composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 * @param product Catalog detailed information.
 * @param isFavorite True/False if catalog detail item is favorite.
 * @param onCatalogItemFavoriteChanged Action for catalog detail item favorite state changed.
 */
@Composable
fun CatalogDetailButtonsBar(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  product: CatalogItem,
  isFavorite: Boolean,
  onCatalogItemFavoriteChanged: (CatalogItem, Boolean) -> Unit,
) {
  Row(
    modifier = Modifier
      .padding(vertical = 5.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    OutlinedButton(
      onClick = { onCatalogItemFavoriteChanged(product, !isFavorite) },
      shape = MaterialTheme.shapes.small,
    ) {
      Icon(
        imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
        contentDescription = null
      )
      Spacer(modifier = Modifier.width(10.dp))
      Text(text = stringResource(id = R.string.text_catalog_detail_button_favorite))
    }
    val shareMessage = stringResource(
      R.string.text_catalog_detail_sharing,
      product.title,
    )
    val currentContext = LocalContext.current
    OutlinedButton(
      onClick = {
        appContentCallbacks.onShareIconClicked(currentContext, shareMessage)
      },
      shape = MaterialTheme.shapes.small,
    ) {
      Icon(imageVector = Icons.Rounded.Share, contentDescription = null)
      Spacer(modifier = Modifier.width(10.dp))
      Text(text = stringResource(id = R.string.text_catalog_detail_button_share))
    }
  }
}


