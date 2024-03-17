/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_search.slots

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.R


@Composable
fun CatalogSearchWelcomeSlot() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(20.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      painter = painterResource(id = R.drawable.img_catalog_search_welcome),
      contentDescription = null,
      modifier = Modifier
        .size(120.dp),
      contentScale = ContentScale.FillBounds
    )

    val annotatedString = buildAnnotatedString {
      withStyle(
        SpanStyle(
          fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
          fontWeight = FontWeight.Bold,
        )
      ) {
        append(stringResource(R.string.text_catalog_search_default_subtitle))
      }
      append("\n")
      append(stringResource(R.string.text_catalog_search_default_detail))
    }
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
      text = annotatedString,
      textAlign = TextAlign.Center,
    )
  }
}
