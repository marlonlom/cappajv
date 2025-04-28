/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.component

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import dev.marlonlom.cappajv.core.database.entities.CatalogItem

/**
 * A Composable function that displays the photo or image associated with a catalog item.
 * It renders the image or photo from the given [CatalogItem].
 *
 * @author marlonlom
 *
 * @param catalogItem The [CatalogItem] containing the photo or image to be displayed.
 * @param imageLoader The [ImageLoader] used to fetch and display the image.
 */
@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
internal fun CatalogDetailPhoto(
  catalogItem: CatalogItem,
  imageLoader: ImageLoader = LocalContext.current.imageLoader,
) {
  val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
  val isCompactHeight = LocalConfiguration.current.screenHeightDp < 480
  val imageHeight = if (isCompactHeight.and(isLandscape)) 120.dp else 180.dp

  AsyncImage(
    imageLoader = imageLoader,
    model = ImageRequest.Builder(LocalContext.current).data(catalogItem.picture).crossfade(true)
      .placeholder(MaterialTheme.colorScheme.secondaryContainer.value.toInt()).build(),
    contentDescription = catalogItem.title,
    contentScale = ContentScale.Crop,
    modifier = Modifier
      .heightIn(max = imageHeight)
      .border(
        width = 1.dp,
        color = MaterialTheme.colorScheme.secondary,
        shape = MaterialTheme.shapes.small,
      )
      .aspectRatio(5f / 7f)
      .clip(MaterialTheme.shapes.small),
  )
}
