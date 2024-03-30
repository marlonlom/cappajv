/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_detail.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.marlonlom.apps.cappajv.ui.layout.DevicePosture
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import dev.marlonlom.apps.cappajv.ui.main.scaffold.ScaffoldContentType

/**
 * Catalog favorite item card image composable.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param title Title for content description.
 * @param picture Image picture url.
 * @param modifier Modifier for this composable.
 */
@Composable
fun CatalogDetailImage(
  appState: CappajvAppState,
  title: String,
  picture: String,
  modifier: Modifier = Modifier,
) {
  val imageRequest = ImageRequest.Builder(LocalContext.current).data(picture).crossfade(true).build()

  val imageModifier = modifier
    .border(
      width = 2.dp,
      color = MaterialTheme.colorScheme.secondary,
      shape = MaterialTheme.shapes.medium,
    )
    .clip(MaterialTheme.shapes.medium)
    .size(catalogDetailImageDpSize(appState))
    .background(Color.White)

  AsyncImage(
    model = imageRequest,
    contentDescription = title,
    contentScale = ContentScale.Crop,
    modifier = imageModifier,
  )
}

@Composable
private fun catalogDetailImageDpSize(
  appState: CappajvAppState
) = when {
  appState.isCompactWidth
    .and(appState.scaffoldContentType == ScaffoldContentType.SinglePane)
    .and(appState.isLandscape.not()).and(
      appState.devicePosture is DevicePosture.Separating.TableTop
    ) -> DpSize(112.dp, 160.dp)

  else -> DpSize(184.dp, 240.dp)
}
