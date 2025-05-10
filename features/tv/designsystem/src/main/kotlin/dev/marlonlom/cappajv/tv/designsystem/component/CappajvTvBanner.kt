/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.designsystem.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

/**
 * Displays a TV-style banner with the application's name and brand image.
 *
 * @author marlonlom
 *
 * @param appName Resource ID for the application's name string.
 * @param brandImg Resource ID for the brand image drawable.
 */
@Composable
fun CappajvTvBanner(@StringRes appName: Int, @DrawableRes brandImg: Int) = Row(
  modifier = Modifier
    .padding(horizontal = 16.dp, vertical = 6.dp)
    .height(42.dp),
  verticalAlignment = Alignment.CenterVertically,
  horizontalArrangement = Arrangement.spacedBy(20.dp),
) {
  Image(
    painter = painterResource(brandImg),
    contentDescription = null,
    modifier = Modifier.size(36.dp),
    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
  )
  Text(
    text = stringResource(appName).uppercase(),
    style = MaterialTheme.typography.titleMedium,
    color = MaterialTheme.colorScheme.primary,
  )
}
