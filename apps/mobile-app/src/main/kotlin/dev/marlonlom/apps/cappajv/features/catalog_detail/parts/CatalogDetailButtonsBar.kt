/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail.parts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
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
import dev.marlonlom.apps.cappajv.R
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

@Composable
fun CatalogDetailButtonsBar(
  appState: CappajvAppState,
  product: CatalogItem,
  appContentCallbacks: AppContentCallbacks,
) {
  Row(
    modifier = Modifier
      .padding(vertical = 10.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    OutlinedButton(
      onClick = { /*TODO*/ },
      shape = MaterialTheme.shapes.small,
    ) {
      Icon(imageVector = Icons.Rounded.FavoriteBorder, contentDescription = null)
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
        appContentCallbacks.onShareIconClicked(currentContext,shareMessage)
      },
      shape = MaterialTheme.shapes.small,
    ) {
      Icon(imageVector = Icons.Rounded.Share, contentDescription = null)
      Spacer(modifier = Modifier.width(10.dp))
      Text(text = stringResource(id = R.string.text_catalog_detail_button_share))
    }
  }
}


