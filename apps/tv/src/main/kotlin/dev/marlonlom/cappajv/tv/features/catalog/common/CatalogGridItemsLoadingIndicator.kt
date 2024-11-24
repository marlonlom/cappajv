/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.features.catalog.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

/**
 * Catalog grid items loading indicator composable ui.
 *
 * @author marlonlom
 *
 * @param modifier The modifier for this composable.
 */
@Composable
fun CatalogGridItemsLoadingIndicator(
  modifier: Modifier = Modifier
) = Text(
  modifier = modifier
    .fillMaxWidth()
    .padding(20.dp),
  textAlign = TextAlign.Center,
  fontWeight = FontWeight.Bold,
  text = "Loading catalog items... ",
  style = MaterialTheme.typography.titleMedium,
  color = MaterialTheme.colorScheme.onSurface,
)
