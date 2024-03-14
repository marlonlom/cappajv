/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_search.slots

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.R

/**
 * Catalog empty result slot composable ui.
 *
 * @author marlonlom
 *
 * @param modifier Modifier for this composable.
 *
 */
@Composable
fun CatalogEmptyResultsSlot(
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(20.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      painter = painterResource(R.drawable.img_catalog_search_empty),
      contentDescription = null,
      modifier = modifier.size(120.dp),
      contentScale = ContentScale.FillBounds
    )
    Text(
      modifier = modifier
        .fillMaxWidth()
        .padding(20.dp),
      text = stringResource(R.string.text_catalog_search_empty_subtitle),
      textAlign = TextAlign.Center,
    )
  }
}
