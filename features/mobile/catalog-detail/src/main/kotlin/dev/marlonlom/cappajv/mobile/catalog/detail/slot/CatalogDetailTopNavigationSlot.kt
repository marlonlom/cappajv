/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.slot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.mobile.catalog.detail.R

/**
 * Composable ui for the top navigation slot for the catalog detail screen.
 *
 * @author marlonlom
 *
 * @param showBackButton A lambda function that returns a [Boolean] indicating whether the back button should be displayed.
 * @param onNavigateBack A lambda function that is called when the back button is clicked to trigger the navigation action.
 */
@Composable
internal fun CatalogDetailTopNavigationSlot(showBackButton: () -> Boolean, onNavigateBack: () -> Unit) = Row(
  modifier = Modifier
    .fillMaxWidth()
    .background(MaterialTheme.colorScheme.background)
    .heightIn(min = 48.dp),
  verticalAlignment = Alignment.CenterVertically,
  horizontalArrangement = Arrangement.spacedBy(4.dp),
) {
  if (showBackButton()) {
    IconButton(
      modifier = Modifier.testTag("detail_back_navigation_btn"),
      onClick = { onNavigateBack() },
    ) {
      Icon(
        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
        contentDescription = stringResource(R.string.text_navigate_back_icon),
        tint = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.sizeIn(minHeight = 36.dp, minWidth = 36.dp),
      )
    }
  }
  Spacer(Modifier.weight(1f))
}
