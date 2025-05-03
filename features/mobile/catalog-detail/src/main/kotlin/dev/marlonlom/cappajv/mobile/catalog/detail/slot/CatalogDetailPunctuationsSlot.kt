/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.slot

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation
import dev.marlonlom.cappajv.mobile.catalog.detail.R
import dev.marlonlom.cappajv.mobile.catalog.detail.component.CatalogDetailPunctuationBox

/**
 * Composable function that displays a list of punctuations related to the catalog detail.
 *
 * @author marlonlom
 *
 * @param punctuations A list of [CatalogPunctuation] items to be displayed.
 * @param contentPadding Padding values to apply around the content of the screen.
 */
@Composable
internal fun CatalogDetailPunctuationsSlot(
  punctuations: List<CatalogPunctuation>,
  contentPadding: PaddingValues = PaddingValues(0.dp),
) {
  Log.d("CatalogDetailPunctuationsSlot", "contentPadding=$contentPadding")
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(contentPadding)
      .padding(top = 20.dp, bottom = 10.dp),
    color = MaterialTheme.colorScheme.secondary,
    fontWeight = FontWeight.Bold,
    maxLines = 1,
    style = MaterialTheme.typography.titleMedium,
    text = stringResource(R.string.text_points),
  )

  FlowRow(
    modifier = Modifier
      .padding(contentPadding),
    horizontalArrangement = Arrangement.spacedBy(20.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    punctuations.forEach { punctuation: CatalogPunctuation ->
      CatalogDetailPunctuationBox(punctuation)
    }
  }

  Spacer(Modifier.height(48.dp))
}
