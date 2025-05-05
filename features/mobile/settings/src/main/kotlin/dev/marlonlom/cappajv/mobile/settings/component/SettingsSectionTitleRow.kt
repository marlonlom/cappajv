/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.settings.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * A Composable that displays a section title row in the settings screen.
 *
 * Typically used to label a group of related settings in the UI.
 *
 * @author marlonlom
 *
 * @param titleRes A string resource ID representing the section title to be displayed.
 */
@Composable
internal fun SettingsSectionTitleRow(@StringRes titleRes: Int) = Row(
  modifier = Modifier
    .padding(top = 10.dp)
    .padding(horizontal = 10.dp),
  verticalAlignment = Alignment.CenterVertically,
  horizontalArrangement = Arrangement.spacedBy(10.dp),
) {
  Text(
    text = stringResource(titleRes),
    fontWeight = FontWeight.Bold,
    style = MaterialTheme.typography.bodyMedium,
    color = MaterialTheme.colorScheme.onSurfaceVariant,
  )
}
