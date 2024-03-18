package dev.marlonlom.apps.cappajv.features.catalog_search.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.R
import dev.marlonlom.apps.cappajv.ui.layout.DevicePosture
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState

/**
 * Catalog search headline composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param modifier Modifier for this composable.
 */
@Composable
fun CatalogSearchHeadline(
  appState: CappajvAppState,
  modifier: Modifier = Modifier,
) {

  val rowPaddingValues = when {
    appState.isCompactHeight -> PaddingValues(vertical = 20.dp)
    else -> PaddingValues(top = 40.dp, bottom = 20.dp)
  }
  val titleTextStyle = when {
    appState.isCompactHeight -> MaterialTheme.typography.headlineSmall
    else -> MaterialTheme.typography.headlineMedium
  }

  val maxTitleLines = when {
    appState.isLandscape.and(appState.devicePosture == DevicePosture.Normal) -> 2
    appState.isLandscape.and(appState.devicePosture is DevicePosture.Separating.Book) -> 2
    else -> 1
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
      text = stringResource(R.string.text_catalog_search_title),
      style = titleTextStyle,
      fontWeight = FontWeight.Bold,
      maxLines = maxTitleLines
    )
  }
}
