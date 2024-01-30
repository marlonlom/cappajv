/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
fun CatalogListRoute(
  appState: CappajvAppState,
) {
  val contentHorizontalPadding = when {
    appState.isLandscapeOrientation.not().and(appState.is7InTabletWidth) -> 40.dp
    appState.isLandscapeOrientation.not().and(appState.is10InTabletWidth) -> 80.dp
    else -> 20.dp
  }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(contentHorizontalPadding)
  ) {
    when (appState.catalogListState) {
      CatalogListState.Empty -> {
        Text(" :( ")
      }

      is CatalogListState.Listing -> {
        Text(
          modifier = Modifier
            .fillMaxWidth()
            .paddingFromBaseline(top = 40.dp, bottom = 20.dp),
          text = "Consigue Nuestros premios",
          fontWeight = FontWeight.Bold,
          style = MaterialTheme.typography.headlineLarge
        )

        val categoriesList: List<String> = appState.catalogListState.list.keys.sorted()
        var selectedCategory by remember {
          mutableStateOf(categoriesList[0])
        }

        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
          items(categoriesList.toList()) { category ->
            FilterChip(
              selected = selectedCategory == category,
              onClick = {
                selectedCategory = category
              },
              label = {
                Text(text = category)
              },
            )
          }
        }

      }

      CatalogListState.Loading -> {
        Text("...")
      }

    }

  }
}
