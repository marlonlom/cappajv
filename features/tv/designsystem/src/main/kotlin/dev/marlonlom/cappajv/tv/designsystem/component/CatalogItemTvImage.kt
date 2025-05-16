/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest

/**
 * Displays a TV catalog item image with a specified title and styling.
 *
 * @author marlonlom
 *
 * @param itemPicture URL or resource identifier of the item's image.
 * @param itemTitle Title of the catalog item, used for content description.
 * @param imageWidth Width of the image (default is 124.dp).
 * @param aspectRatio Aspect ratio of the image (default is 5:7).
 * @param imageLoader Custom [ImageLoader] for loading the image.
 */
@Composable
fun CatalogItemTvImage(
  itemPicture: String,
  itemTitle: String,
  imageWidth: Dp = 124.dp,
  aspectRatio: Float = 5f / 7,
  imageLoader: ImageLoader = LocalContext.current.imageLoader,
) = AsyncImage(
  imageLoader = imageLoader,
  model = ImageRequest.Builder(LocalContext.current).data(itemPicture).crossfade(true)
    .placeholder(MaterialTheme.colorScheme.secondaryContainer.value.toInt()).build(),
  contentDescription = itemTitle,
  contentScale = ContentScale.Crop,
  modifier = Modifier
    .width(imageWidth)
    .border(
      width = 1.dp,
      color = MaterialTheme.colorScheme.secondary,
      shape = MaterialTheme.shapes.small,
    )
    .aspectRatio(aspectRatio)
    .clip(MaterialTheme.shapes.small),
)
