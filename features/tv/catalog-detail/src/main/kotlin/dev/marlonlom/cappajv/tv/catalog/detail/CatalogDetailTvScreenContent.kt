/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.catalog.detail

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.domain.catalog.detail.CatalogDetailItem

/**
 * Composable function that renders the content for the catalog item details on a TV screen.
 *
 * @author marlonlom
 *
 * @param detailItem The [CatalogDetailItem] containing the information to display.
 */
@Composable
internal fun CatalogDetailTvScreenContent(detailItem: CatalogDetailItem) {
  val (product) = detailItem
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
      .fillMaxSize()
      .paint(
        painter = painterResource(backgroundImage(context, product.category)),
        contentScale = ContentScale.FillBounds,
        colorFilter = ColorFilter.tint(
          color = MaterialTheme.colorScheme.background,
          blendMode = BlendMode.Softlight,
        ),
      ),
    contentAlignment = Alignment.Center,
  ) {
    Text(
      text = "Reviewing catalog item: ${product.title}",
      color = MaterialTheme.colorScheme.onBackground,
      style = MaterialTheme.typography.titleLarge,
    )
  }
}
