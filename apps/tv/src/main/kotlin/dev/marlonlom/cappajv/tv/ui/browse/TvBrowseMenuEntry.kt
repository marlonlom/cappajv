/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.browse

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.Icon
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.Text

/**
 * Tv browse menu entry composable ui.
 *
 * @author marlonlom
 *
 * @param drawerValue Drawer value (open,closed).
 * @param itemIcon Menu entry icon.
 * @param itemText Menu entry title.
 * @param onSelected Action for selected menu entry.
 * @param modifier The modifier for this composable.
 */
@Composable
fun TvBrowseMenuEntry(
  drawerValue: DrawerValue,
  itemIcon: ImageVector,
  @StringRes itemText: Int,
  onSelected: () -> Unit,
  modifier: Modifier = Modifier,
) = OutlinedButton(
  modifier = modifier
    .padding(16.dp)
    .wrapContentWidth(),
  onClick = { onSelected() },
) {
  Icon(
    imageVector = itemIcon,
    contentDescription = null,
    modifier = modifier.padding(end = 4.dp),
    tint = Color.White,
  )
  AnimatedVisibility(visible = drawerValue == DrawerValue.Open) {
    Text(
      modifier = modifier.padding(end = 4.dp),
      text = stringResource(itemText),
      softWrap = false,
      textAlign = TextAlign.Start,
      color = Color.White,
    )
  }
}
