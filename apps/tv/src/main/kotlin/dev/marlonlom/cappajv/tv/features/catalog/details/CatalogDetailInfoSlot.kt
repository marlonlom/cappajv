/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.features.catalog.details

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.tv.R
import dev.marlonlom.cappajv.tv.ui.util.tvSafeContentPadding

/**
 * Catalog detail information slot composable ui.
 * @author marlonlom
 *
 * @param foundDetail Catalog found item details.
 * @param onMarkedFavorite Action for item favorite state changed.
 * @param modifier The modifier for this composable.
 */
@Composable
internal fun CatalogDetailInfoSlot(
  foundDetail: CatalogDetail,
  onMarkedFavorite: (CatalogItem, Boolean) -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val detailBackgroundRes = findBackgroundForItemCategory(
    context = context,
    categoryText = foundDetail.product.category
  )

  Column(
    modifier = modifier
      .fillMaxSize()
      .paint(
        painter = painterResource(detailBackgroundRes),
        contentScale = ContentScale.FillBounds,
        colorFilter = ColorFilter.tint(
          color = MaterialTheme.colorScheme.background,
          blendMode = BlendMode.Softlight
        )
      )
      .tvSafeContentPadding(),
    horizontalAlignment = Alignment.Start,
  ) {

    Spacer(modifier = modifier.weight(1.0f))

    CatalogDetailHeadingInfoSlot(
      foundDetail = foundDetail,
      onMarkedFavorite = onMarkedFavorite
    )

    Text(
      modifier = Modifier
        .fillMaxWidth(0.5f)
        .padding(vertical = 30.dp),
      textAlign = TextAlign.Start,
      text = foundDetail.product.detail,
      color = MaterialTheme.colorScheme.onSurface,
    )

    CatalogDetailInfoPointsSlot(foundDetail.points)

  }

  Spacer(modifier.height(48.dp))
}

/**
 * Finds background drawable resource for selected item category.
 *
 * @author marlonlom
 *
 * @param context Application context
 * @param categoryText Item category name.
 * @return found drawable resource.
 */
fun findBackgroundForItemCategory(context: Context, categoryText: String): Int {
  val categoryIndex = arrayOf(
    R.string.text_category_hot,
    R.string.text_category_cold,
    R.string.text_category_pastry,
  ).map { context.getString(it) }.indexOf(categoryText)

  return when (categoryIndex) {
    1 -> R.drawable.img_details_background_cold_drinks
    2 -> R.drawable.img_details_background_pastry
    else -> R.drawable.img_details_background_hot_drinks
  }
}

