/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Provides a DSL for adding a full-width item to the LazyGrid that displays a [CircularProgressIndicator].
 *
 * @author marlonlom
 */
internal fun LazyGridScope.loadingIndicator() = item(
  span = { GridItemSpan(this.maxLineSpan) },
  content = {
    Box(
      modifier = Modifier.fillMaxSize(),
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
