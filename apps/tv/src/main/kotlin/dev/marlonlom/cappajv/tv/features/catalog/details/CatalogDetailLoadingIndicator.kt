/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.tv.ui.util.tvSafeContentPadding

/**
 * Catalog detail loading indicator screen composable ui.
 *
 * @author marlonlom
 *
 * @param modifier The modifier for this composable.
 */
@Composable
fun CatalogDetailLoadingIndicator(modifier: Modifier = Modifier) = Column(
  modifier = modifier
    .background(MaterialTheme.colorScheme.surface)
    .fillMaxSize()
    .tvSafeContentPadding(),
  verticalArrangement = Arrangement.Center,
  horizontalAlignment = Alignment.CenterHorizontally,
) {
  Text(
    modifier = modifier.paddingFromBaseline(40.dp, 20.dp),
    text = "Loading catalog item... ",
    textAlign = TextAlign.Center,
    fontWeight = FontWeight.Bold,
    color = MaterialTheme.colorScheme.onSurface,
    style = MaterialTheme.typography.titleMedium,
  )
}
