/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.core.text.trimmedLength
import coil.compose.AsyncImage
import coil.request.ImageRequest
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
@ExperimentalLayoutApi
@Composable
internal fun CatalogTupleRow(
  appState: CappajvAppState,
  tuple: CatalogItemTuple,
  onCatalogItemTupleClicked: (Long) -> Unit,
  modifier: Modifier = Modifier,
) {
  Card(
    modifier = modifier.fillMaxWidth(),
    shape = CardDefaults.outlinedShape,
    colors = CardDefaults.outlinedCardColors(),
    onClick = {
      onCatalogItemTupleClicked(tuple.id)
    },
  ) {
    Row(
      modifier = modifier.padding(vertical = 10.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      CatalogTuplePosterImage(tuple, appState)
      Column(
        modifier = modifier
          .fillMaxWidth()
          .padding(end = 10.dp)
      ) {
        CatalogTupleTitle(tuple, appState)
        CatalogTupleSamplePunctuationText(tuple)
      }
    }
  }
}

/**
 * Catalog tuple sample punctuation text composable ui.
 *
 * @author marlonlom
 *
 * @param tuple Catalog item.
 */
@Composable
private fun CatalogTupleSamplePunctuationText(
  tuple: CatalogItemTuple
) {
  val samplePunctuationTxt = buildAnnotatedString {
    val textParts = tuple.samplePunctuation.split(":")
    withStyle(
      SpanStyle(
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.secondary,
      )
    ) {
      val punctuationTitle = when {
        textParts[0].trimmedLength() > 10 -> textParts[0].split(" ")[0].plus(" ...")
        else -> textParts[0]
      }
      append(punctuationTitle)
    }
    append(": ")
    append(textParts[1].trim())
    if (tuple.punctuationsCount > 1) {
      append(" ")
      withStyle(
        SpanStyle(
          fontWeight = FontWeight.SemiBold,
          color = MaterialTheme.colorScheme.secondary,
        )
      ) {
        append("(+ ${tuple.punctuationsCount - 1})")
      }
    }
  }
  Text(
    text = samplePunctuationTxt,
    style = MaterialTheme.typography.labelMedium,
  )
}

/**
 * Catalog tuple item title composable text.
 *
 * @author marlonlom
 *
 * @param tuple Catalog item tuple data.
 * @param appState Application ui state.
 */
@Composable
private fun CatalogTupleTitle(
  tuple: CatalogItemTuple, appState: CappajvAppState
) {
  Text(
    text = tuple.title,
    style = getCatalogTupleTitleStyle(appState),
    color = MaterialTheme.colorScheme.secondary,
    maxLines = 1,
    overflow = TextOverflow.Ellipsis,
    fontWeight = FontWeight.SemiBold
  )
}

/**
 * Catalog tuple item poster image composable.
 *
 * @author marlonlom
 *
 * @param tuple Catalog item tuple data.
 * @param appState Application ui state.
 */
@Composable
private fun CatalogTuplePosterImage(
  tuple: CatalogItemTuple, appState: CappajvAppState
) {
  val imageRequest = ImageRequest.Builder(LocalContext.current).data(tuple.picture).crossfade(true).build()

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
      .size(getCatalogTupleImageSizeDp(appState))
      .background(Color.White),
  )
}

@Composable
private fun getCatalogTupleImageSizeDp(appState: CappajvAppState): DpSize = when {
  appState.isCompactWidth.and(appState.isLandscape.not())
    .and(appState.devicePosture is DevicePosture.Separating.TableTop) -> DpSize(48.dp, 56.dp)

  else -> DpSize(56.dp, 64.dp)
}


@Composable
private fun getCatalogTupleTitleStyle(appState: CappajvAppState) = when {
  appState.isCompactWidth.and(appState.isLandscape.not())
    .and(appState.devicePosture is DevicePosture.Separating.TableTop) -> MaterialTheme.typography.bodyMedium

  else -> MaterialTheme.typography.bodyLarge
}
