/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.catalog.detail.slots

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Sell
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.R
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation
import dev.marlonlom.cappajv.ui.main.CappajvAppState

/**
 * Found catalog detail points slot composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param punctuations Catalog item points information
 * @param modifier Modifier for this composable.
 */
@ExperimentalFoundationApi
@Composable
fun FoundCatalogDetailPointsSlot(
  appState: CappajvAppState,
  punctuations: List<CatalogPunctuation>,
  modifier: Modifier = Modifier,
) {
  Text(
    modifier = modifier
      .background(MaterialTheme.colorScheme.surface)
      .fillMaxWidth()
      .paddingFromBaseline(top = 40.dp, bottom = 20.dp),
    text = stringResource(R.string.text_catalog_detail_points),
    fontWeight = FontWeight.Bold,
    color = MaterialTheme.colorScheme.secondary,
    textAlign = TextAlign.Center,
    style = MaterialTheme.typography.bodyMedium,
  )
  punctuations.forEach { catalogPunctuation ->
    CatalogPunctuationItemCard(
      appState = appState,
      punctuation = catalogPunctuation,
    )
  }
  Spacer(Modifier.height(20.dp))
}

/**
 * Catalog punctuation item card composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param punctuation Catalog item points information
 * @param modifier Modifier for this composable.
 */
@Composable
fun CatalogPunctuationItemCard(
  appState: CappajvAppState,
  punctuation: CatalogPunctuation,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(bottom = 10.dp, top = 4.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    Icon(
      imageVector = Icons.TwoTone.Sell,
      contentDescription = null,
      modifier = modifier.size(32.dp),
      tint = MaterialTheme.colorScheme.primary,
    )
    Column {
      Text(
        text = "${punctuation.points} pts",
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge,
      )
      Text(text = punctuation.label)
    }
  }
}
