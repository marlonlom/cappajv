/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.features.catalog.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

/**
 * Catalog grid category text composable ui.
 *
 * @author marlonlom
 *
 * @param categoryTitle Category title as string resource.
 * @param modifier The modifier for this composable.
 */
@Composable
fun CatalogGridCategoryText(
  @StringRes categoryTitle: Int,
  modifier: Modifier = Modifier
) = Text(
  modifier = modifier.fillMaxWidth(),
  text = stringResource(categoryTitle),
  style = MaterialTheme.typography.titleMedium,
  fontWeight = FontWeight.Bold,
  color = MaterialTheme.colorScheme.onSurface,
)
