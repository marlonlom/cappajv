/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.marlonlom.apps.cappajv.R
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.apps.cappajv.ui.layout.DevicePosture
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * Catalog tuple row composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param tuple Catalog item.
 * @param onCatalogItemTupleClicked Action for catalog item selected.
 * @param modifier Modifier for this composable.
 */
@Composable
internal fun CatalogTupleRow(
  appState: CappajvAppState,
  tuple: CatalogItemTuple,
  onCatalogItemTupleClicked: (Long) -> Unit,
  modifier: Modifier = Modifier,
) {

  val imageSizeDp = getCatalogTupleImageSizeDp(appState)

  Card(
    modifier = modifier.fillMaxWidth(),
    shape = CardDefaults.outlinedShape,
    colors = CardDefaults.outlinedCardColors(),
    onClick = {
      onCatalogItemTupleClicked(tuple.id)
    },
  ) {
    Row(
      modifier.padding(10.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
      val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(tuple.picture)
        .crossfade(true)
        .build()

      AsyncImage(
        model = imageRequest,
        contentDescription = tuple.title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.medium,
            )
            .clip(MaterialTheme.shapes.medium)
            .size(imageSizeDp)
            .background(Color.White),
      )

      Column {
        Text(
          text = tuple.title,
          style = getCatalogTupleTitleStyle(appState),
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          fontWeight = FontWeight.SemiBold
        )
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          SuggestionChip(
            onClick = { },
            colors = SuggestionChipDefaults.suggestionChipColors(
              containerColor = MaterialTheme.colorScheme.tertiaryContainer,
              labelColor = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            label = {
              val samplePunctuationTxt = buildAnnotatedString {
                val textParts = tuple.samplePunctuation.split(":")
                withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                  append(textParts[0])
                }
                append(": ")
                append(textParts[1].trim())
              }
              Text(text = samplePunctuationTxt)
            },
            shape = MaterialTheme.shapes.small,
          )

          if (tuple.punctuationsCount > 1) {
            Text(
              text = gePunctuationCountText(
                appState = appState,
                punctuationsCount = tuple.punctuationsCount - 1
              )
            )
          }
        }
      }
    }
  }
}

@Composable
private fun gePunctuationCountText(
  appState: CappajvAppState,
  punctuationsCount: Int,
) = when {
  appState.isCompactWidth.and(appState.isLandscape.not())
    .and(appState.devicePosture is DevicePosture.Separating.TableTop) -> "+$punctuationsCount"

  else -> stringResource(
    R.string.text_catalog_hint_more_punctuations,
    punctuationsCount
  )
}

@Composable
private fun getCatalogTupleImageSizeDp(appState: CappajvAppState) = when {
  appState.isCompactWidth.and(appState.isLandscape.not())
    .and(appState.devicePosture is DevicePosture.Separating.TableTop) -> 56.dp

  else -> 100.dp
}


@Composable
private fun getCatalogTupleTitleStyle(appState: CappajvAppState) = when {
  appState.isCompactWidth.and(appState.isLandscape.not())
    .and(appState.devicePosture is DevicePosture.Separating.TableTop) -> MaterialTheme.typography.titleMedium

  else -> MaterialTheme.typography.titleLarge
}
