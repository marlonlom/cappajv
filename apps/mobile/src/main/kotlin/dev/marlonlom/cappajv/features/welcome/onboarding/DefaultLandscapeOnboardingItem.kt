/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.welcome.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.R
import dev.marlonlom.cappajv.ui.theme.CappajvTheme


/**
 * Default landscape onboarding item composable ui.
 *
 * @author marlonlom
 *
 * @param item Onboarding item as triple.
 * @param modifier Modifier for this composable.
 */
@Composable
internal fun DefaultLandscapeOnboardingItem(
  item: Triple<Int, Int, Int>,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .heightIn(max = 180.dp)
      .background(MaterialTheme.colorScheme.surface)
      .padding(10.dp),
    horizontalArrangement = Arrangement.spacedBy(40.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {

    Image(
      painter = painterResource(id = item.first),
      contentDescription = "image",
      contentScale = ContentScale.Fit,
      modifier = Modifier
        .size(DpSize(120.dp, 160.dp))
        .clip(RoundedCornerShape(10.dp))
    )

    Column(modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
      Text(
        modifier = modifier.padding(bottom = 4.dp),
        text = stringResource(item.second),
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleLarge
      )

      Text(
        modifier = modifier.padding(bottom = 4.dp),
        text = stringResource(item.third),
        color = MaterialTheme.colorScheme.onSurface,
      )
    }

  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
  showBackground = true,
  locale = "es",
  device = Devices.AUTOMOTIVE_1024p,
  widthDp = 720
)
@Composable
private fun DefaultLandscapeOnboardingItemPreview() {
  CappajvTheme(dynamicColor = false) {
    DefaultLandscapeOnboardingItem(
      item = Triple(
        R.drawable.img_onboarding_01,
        R.string.text_onboarding_title_01,
        R.string.text_onboarding_detail_01
      )
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
  showBackground = true,
  device = Devices.AUTOMOTIVE_1024p,
  widthDp = 720
)
@Composable
private fun DefaultLandscapeOnboardingItem2Preview() {
  CappajvTheme(dynamicColor = false) {
    DefaultLandscapeOnboardingItem(
      item = Triple(
        R.drawable.img_onboarding_02,
        R.string.text_onboarding_title_02,
        R.string.text_onboarding_detail_02
      )
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
  showBackground = true,
  locale = "es",
  device = Devices.AUTOMOTIVE_1024p,
  widthDp = 720
)
@Composable
private fun DefaultLandscapeOnboardingItem3Preview() {
  CappajvTheme(darkTheme = true, dynamicColor = false) {
    DefaultLandscapeOnboardingItem(
      item = Triple(
        R.drawable.img_onboarding_03,
        R.string.text_onboarding_title_03,
        R.string.text_onboarding_detail_03
      )
    )
  }
}
