/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.welcome.parts

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.marlonlom.apps.cappajv.R

@Composable
internal fun WelcomeButton(
  onContinueHomeButtonClicked: () -> Unit,
  modifier: Modifier = Modifier
) {
  Button(
    modifier = modifier,
    onClick = {
      onContinueHomeButtonClicked()
    },
    shape = MaterialTheme.shapes.small,
    colors = ButtonDefaults.buttonColors(
      containerColor = MaterialTheme.colorScheme.primary,
      contentColor = MaterialTheme.colorScheme.onPrimary,
    )
  ) {
    Text(
      text = stringResource(R.string.text_welcome_button),
      style = MaterialTheme.typography.bodyLarge,
    )
  }
}
