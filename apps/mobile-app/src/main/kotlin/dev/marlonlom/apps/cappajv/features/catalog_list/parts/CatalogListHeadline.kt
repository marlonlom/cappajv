/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.R
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * Catalog listing headline composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param modifier Modifier for this composable.
 */
@Composable
fun CatalogListHeadline(
  appState: CappajvAppState,
  modifier: Modifier = Modifier,
) {

  val rowPaddingValues = when {
    appState.isCompactHeight -> PaddingValues(vertical = 20.dp)
    else -> PaddingValues(top = 40.dp, bottom = 20.dp)
  }

  Row(
    modifier = modifier
        .background(MaterialTheme.colorScheme.surface)
        .fillMaxWidth()
        .padding(rowPaddingValues),
    verticalAlignment = Alignment.Bottom,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      modifier = Modifier.fillMaxWidth(0.75f),
      text = stringResource(R.string.text_catalog_list_title),
      style = MaterialTheme.typography.headlineLarge,
      fontWeight = FontWeight.Bold,
      maxLines = 2
    )
    Image(
      painter = painterResource(R.drawable.img_juan_valdez_logo),
      contentDescription = null,
      contentScale = ContentScale.FillBounds,
      modifier = Modifier.size(if (appState.isLandscape) 36.dp else 64.dp)
    )
  }
}
