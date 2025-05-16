/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.designsystem.slot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.tv.designsystem.R
import dev.marlonlom.cappajv.tv.designsystem.component.CircularProgressIndicator

/**
 * A composable that displays a loading indicator with an optional message.
 *
 * @param message The message to display below the loading indicator. Defaults to "Loading".
 * @param contentAlignment The alignment of the loading indicator and message within the slot. Defaults to [Alignment.Center].
 * @param style The text style to apply to the message. Defaults to [MaterialTheme.typography.displaySmall].
 */
@Composable
fun LoadingSlot(
  message: String = stringResource(id = R.string.text_loading),
  contentAlignment: Alignment = Alignment.Center,
  style: TextStyle = MaterialTheme.typography.displaySmall,
) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    contentAlignment = contentAlignment,
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
      CircularProgressIndicator()
      Text(text = message, style = style)
    }
  }
}
