/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.onboarding.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * Onboarding item composable ui.
 *
 * @author marlonlom
 *
 * @param item Onboarding item as triple.
 * @param testTag Test tag.
 */
@Composable
internal fun OnboardingItem(item: Triple<Int, Int, Int>, testTag: String) {
  val isLandScape = LocalConfiguration.current.let {
    (it.orientation == Configuration.ORIENTATION_LANDSCAPE).and(it.screenHeightDp <= 480)
  }

  val imageSize = if (isLandScape) DpSize(120.dp, 160.dp) else DpSize(200.dp, 160.dp)

  val itemImage: @Composable (Int, DpSize) -> Unit = { imageRes, dp ->
    Image(
      painter = painterResource(id = imageRes),
      contentDescription = null,
      contentScale = ContentScale.Fit,
      modifier = Modifier
        .size(dp)
        .clip(RoundedCornerShape(10.dp)),
    )
  }

  val itemTitle: @Composable (Int, TextAlign, Modifier) -> Unit = { titleRes, ta, m ->
    Text(
      text = stringResource(titleRes),
      modifier = m,
      color = MaterialTheme.colorScheme.onSurface,
      fontWeight = FontWeight.Bold,
      maxLines = 1,
      textAlign = ta,
      style = MaterialTheme.typography.titleLarge,
    )
  }

  val itemSubtitle: @Composable (Int, TextAlign, Modifier) -> Unit = { subtitleRes, ta, m ->
    Text(
      text = stringResource(subtitleRes),
      modifier = m,
      color = MaterialTheme.colorScheme.onSurface,
      textAlign = ta,
    )
  }

  if (isLandScape) {
    Row(
      modifier = Modifier
        .heightIn(max = 180.dp)
        .background(MaterialTheme.colorScheme.surface)
        .padding(10.dp)
        .testTag(testTag),
      horizontalArrangement = Arrangement.spacedBy(40.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      itemImage(item.first, imageSize)
      Column(
        modifier = Modifier
          .fillMaxHeight()
          .widthIn(max = 360.dp),
        verticalArrangement = Arrangement.Center,
      ) {
        val paddingModifier = Modifier.padding(bottom = 4.dp)
        itemTitle(
          item.second,
          TextAlign.Unspecified,
          paddingModifier,
        )
        itemSubtitle(
          item.third,
          TextAlign.Unspecified,
          paddingModifier,
        )
      }
    }
  } else {
    Column(
      modifier = Modifier
        .fillMaxWidth().widthIn(max = 600.dp)
        .background(MaterialTheme.colorScheme.surface)
        .padding(4.dp)
        .testTag(testTag),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      itemImage(item.first, imageSize)
      Spacer(Modifier.height(24.dp))
      val paddingModifier = Modifier
        .widthIn(max = 600.dp)
        .padding(bottom = 4.dp)
      itemTitle(
        item.second,
        TextAlign.Center,
        paddingModifier,
      )
      itemSubtitle(
        item.third,
        TextAlign.Center,
        paddingModifier,
      )
    }
  }
}
