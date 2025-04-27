/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.favorites.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material3.CircularProgressIndicator
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
import dev.marlonlom.cappajv.mobile.catalog.favorites.R

/**
 * Provides a DSL for adding a full-width empty section to the LazyGrid.
 *
 * @author marlonlom
 */
internal fun LazyGridScope.empty() = item(
  span = { GridItemSpan(this.maxLineSpan) },
  content = {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Image(
        painter = painterResource(R.drawable.img_empty),
        contentDescription = null,
        modifier = Modifier
          .padding(vertical = 20.dp)
          .size(84.dp),
        contentScale = ContentScale.Crop,
      )

      val annotatedString = buildAnnotatedString {
        MaterialTheme.typography.titleLarge.also {
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
        append(stringResource(R.string.text_empty_detail))
      }
      Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = annotatedString,
      )
    }
  },
)

/**
 * Provides a DSL for adding a full-width item to the LazyGrid that displays a [CircularProgressIndicator].
 *
 * @author marlonlom
 */
internal fun LazyGridScope.loadingIndicator() = item(
  span = { GridItemSpan(this.maxLineSpan) },
  content = {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 64.dp),
      contentAlignment = Alignment.Center,
    ) {
      CircularProgressIndicator()
    }
  },
)

/**
 * Provides a DSL for adding a full-width header item to a [LazyGridScope].
 *
 * @author marlonlom
 *
 * @param content The composable lambda that provides the content to be displayed in the header.
 */
internal fun LazyGridScope.header(content: @Composable LazyGridItemScope.() -> Unit) =
  item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
