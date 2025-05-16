/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.catalog.detail

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.domain.catalog.detail.CatalogDetailItem
import dev.marlonlom.cappajv.tv.catalog.detail.slot.CatalogDetailTvContentSlot

/**
 * Composable function that renders the content for the catalog item details on a TV screen.
 *
 * @author marlonlom
 *
 * @param detailItem The [CatalogDetailItem] containing the information to display.
 */
@Composable
internal fun CatalogDetailTvScreenContent(
  detailItem: CatalogDetailItem,
  onFavoriteChanged: (CatalogItem, Boolean) -> Unit,
) {
  val (product, isFavorite, points) = detailItem
  val context = LocalContext.current

  val backgroundImage: (Context, String) -> Int = { ctx, category ->
    when (ctx.resources.getStringArray(R.array.array_categories).indexOf(category)) {
      1 -> R.drawable.img_details_background_cold_drinks
      2 -> R.drawable.img_details_background_pastry
      else -> R.drawable.img_details_background_hot_drinks
    }
  }

  Box(
    modifier = Modifier
      .fillMaxSize(),
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .aspectRatio(16f / 9)
        .paint(
          painter = painterResource(backgroundImage(context, product.category)),
          contentScale = ContentScale.FillBounds,
        ),
      content = {},
    )

    Image(
      painter = painterResource(id = R.drawable.scrim),
      contentDescription = null,
      modifier = Modifier.fillMaxSize(),
      alignment = Alignment.BottomStart,
      contentScale = ContentScale.FillWidth,
    )

    CatalogDetailTvContentSlot(
      item = product,
      points = points,
      isFavorite = isFavorite,
      onFavoriteChanged = onFavoriteChanged,
      modifier = Modifier
        .align(Alignment.BottomStart)
        .padding(start = 60.dp, end = 30.dp)
        .fillMaxWidth(0.6f)
    )

  }
}
