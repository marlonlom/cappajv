/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

/**
 * A composable function that displays a single action button with an icon and text.
 *
 * @author marlonlom
 *
 * @param buttonText The string resource ID for the button label.
 * @param buttonIcon The icon to display alongside the button text.
 * @param buttonTestTag A tag used for identifying this button in UI tests.
 * @param onButtonClicked Lambda function invoked when the button is clicked.
 */
@Composable
internal fun CatalogDetailActionButton(
  @StringRes buttonText: Int,
  buttonIcon: ImageVector,
  buttonTestTag: String,
  onButtonClicked: () -> Unit,
) = OutlinedButton(
  modifier = Modifier.testTag(buttonTestTag),
  onClick = { onButtonClicked() },
  border = ButtonDefaults.outlinedButtonBorder(),
  colors = ButtonDefaults.outlinedButtonColors(
    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
  ),
  shape = MaterialTheme.shapes.small,
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp),
  ) {
    Icon(buttonIcon, null)
    Text(stringResource(buttonText))
  }
}
