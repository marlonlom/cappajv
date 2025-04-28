/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.slot

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.imageLoader
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.mobile.catalog.detail.component.CatalogDetailHeaderText
import dev.marlonlom.cappajv.mobile.catalog.detail.component.CatalogDetailPhoto

/**
 * A Composable function that displays the headline for the catalog detail screen.
 *
 * @author marlonlom
 *
 * @param detail The [CatalogItem] that contains the headline or title to be displayed.
 * @param imageLoader The [ImageLoader] used to fetch and display the image.
 */
@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
internal fun CatalogDetailHeadlineSlot(
  detail: CatalogItem,
  imageLoader: ImageLoader = LocalContext.current.imageLoader,
) {
  val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
  val isCompactHeight = LocalConfiguration.current.screenHeightDp < 480
  if (isCompactHeight.and(isLandscape)) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp),
      horizontalArrangement = Arrangement.spacedBy(10.dp),
      verticalAlignment = Alignment.Bottom,
    ) {
      CatalogDetailPhoto(detail, imageLoader)
      CatalogDetailHeaderText(detail)
    }
  } else {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      CatalogDetailPhoto(detail, imageLoader)
      CatalogDetailHeaderText(detail, true)
    }
  }
}
