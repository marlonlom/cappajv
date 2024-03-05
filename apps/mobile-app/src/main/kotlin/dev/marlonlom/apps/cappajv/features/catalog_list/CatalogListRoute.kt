/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.marlonlom.apps.cappajv.R
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
fun CatalogListRoute(
  appState: CappajvAppState,
  viewModel: CatalogListViewModel = koinViewModel(),
) {

  val catalogListState: CatalogListState by viewModel.uiState.collectAsStateWithLifecycle()

  val contentHorizontalPadding = when {
    appState.isLandscape.not().and(appState.isMediumWidth) -> 40.dp
    appState.isLandscape.not().and(appState.isExpandedWidth) -> 80.dp
    else -> 20.dp
  }

  LazyColumn(
    modifier = Modifier
      .safeContentPadding()
      .padding(bottom = 0.dp),
  ) {
    stickyHeader {
      Text(
        modifier = Modifier
          .fillMaxWidth()
          .background(MaterialTheme.colorScheme.background)
          .paddingFromBaseline(top = 40.dp, bottom = 20.dp),
        text = "Consigue Nuestros premios",
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.headlineMedium
      )
    }

    when (catalogListState) {
      CatalogListState.Empty -> {
        item {
          Text(" :( ")
        }
      }

      CatalogListState.Loading -> {
        item { Text("...") }
      }

      is CatalogListState.Listing -> {
        val listingsData = catalogListState as CatalogListState.Listing

        listingsData.map.keys.sorted().forEach { category ->
          item {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 10.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = category,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
              )
              TextButton(onClick = {}) {
                Text(text = stringResource(R.string.text_catalog_list_see_all))
              }
            }
          }

          item {
            val tuples: List<CatalogItemTuple> = listingsData.map[category]
              .orEmpty().shuffled().subList(0, 5)
            LazyRow(
              horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
              items(tuples.size) { pos ->
                val tuple = tuples[pos]
                Card(modifier = Modifier
                  .width(160.dp),
                  shape = RoundedCornerShape(10.dp),
                  colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(
                      alpha = 0.25f
                    ),
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                  ),
                  onClick = {
                    Timber.d("[CatalogListRoute] tuple=${tuple.title}")
                  }) {
                  Column(
                    modifier = Modifier
                      .fillMaxWidth()
                      .padding(top = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                  ) {
                    AsyncImage(
                      model = ImageRequest.Builder(LocalContext.current)
                        .data(tuple.picture)
                        .crossfade(true)
                        .build(),
                      contentDescription = tuple.title,
                      contentScale = ContentScale.Crop,
                      modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(140.dp)
                        .background(Color.White),
                    )
                    Text(
                      modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                      text = tuple.title,
                      style = MaterialTheme.typography.bodyMedium,
                      minLines = 2,
                      overflow = TextOverflow.Ellipsis,
                      fontWeight = FontWeight.Bold,
                      textAlign = TextAlign.Center
                    )
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
