/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.catalog.favorites.slot

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.tv.catalog.favorites.R

/**
 * Displays a placeholder UI when the favorites catalog is empty.
 *
 * This Composable is intended for internal use and provides a visual
 * indication to the user that there are no favorite items currently added.
 *
 * @author marlonlom
 */
@Composable
internal fun CatalogEmptyFavoritesSlot() = Row(
  modifier = Modifier
    .fillMaxWidth()
    .height(160.dp)
    .border(1.dp, MaterialTheme.colorScheme.surfaceTint, MaterialTheme.shapes.large)
    .focusable(),
  verticalAlignment = Alignment.CenterVertically,
  horizontalArrangement = Arrangement.spacedBy(20.dp),
) {
  Spacer(Modifier.width(40.dp))
  Image(
    painter = painterResource(R.drawable.img_empty),
    contentDescription = null,
    modifier = Modifier
      .padding(20.dp)
      .width(84.dp),
    contentScale = ContentScale.Crop,
  )
  val annotatedString = buildAnnotatedString {
    MaterialTheme.typography.titleMedium.also {
      withStyle(
        SpanStyle(
          color = it.color,
          fontSize = it.fontSize,
          fontWeight = FontWeight.Bold,
          fontStyle = it.fontStyle,
          fontFamily = it.fontFamily,
        ),
      ) {
        append(stringResource(R.string.text_empty_title))
      }
    }
    appendLine()
    MaterialTheme.typography.bodySmall.also {
      withStyle(
        SpanStyle(
          color = it.color,
          fontSize = it.fontSize,
          fontStyle = it.fontStyle,
          fontFamily = it.fontFamily,
        ),
      ) {
        append(stringResource(R.string.text_empty_detail))
      }
    }
  }
  Text(
    modifier = Modifier.fillMaxWidth(),
    text = annotatedString,
    color = MaterialTheme.colorScheme.onBackground,
  )
  Spacer(Modifier.width(40.dp))
}
