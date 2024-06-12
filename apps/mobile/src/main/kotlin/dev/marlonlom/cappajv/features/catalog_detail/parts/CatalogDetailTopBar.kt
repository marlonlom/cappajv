/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.catalog_detail.parts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.R
import dev.marlonlom.cappajv.ui.main.CappajvAppState
import dev.marlonlom.cappajv.ui.navigation.NavigationType

/**
 * Catalog detail top bar composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param isRouting True/False if should navigate through routing.
 */
@Composable
fun CatalogDetailTopBar(
  appState: CappajvAppState,
  isRouting: Boolean
) {
  val topBarHeight = calculateTopBarHeight(appState)

  Row(
    Modifier
      .fillMaxWidth()
      .height(topBarHeight),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    if (isRouting) {
      IconButton(onClick = { appState.navController.navigateUp() }) {
        Icon(
          imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
          contentDescription = stringResource(R.string.text_catalog_detail_navigate_back_icon_hint),
          tint = MaterialTheme.colorScheme.secondary
        )
      }
    }
    Spacer(Modifier.weight(1f))
  }

}

fun calculateTopBarHeight(appState: CappajvAppState): Dp = when {
  appState.isLandscape.and(appState.navigationType == NavigationType.EXPANDED_NAV) -> 152.dp
  else -> 64.dp
}
