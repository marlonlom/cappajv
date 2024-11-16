/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.welcome.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.R
import dev.marlonlom.cappajv.ui.theme.CappajvTheme

/**
 * Onboarding item composable ui.
 *
 * @author marlonlom
 *
 * @param item Onboarding item as triple.
 * @param testTag Test tag.
 * @param modifier Modifier for this composable.
 */
@Composable
internal fun DefaultOnboardingItem(
  item: Triple<Int, Int, Int>,
  testTag: String = "onboarding_item",
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .background(MaterialTheme.colorScheme.surface)
      .padding(4.dp)
      .testTag(testTag),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Image(
      painter = painterResource(id = item.first),
      contentDescription = "image",
      contentScale = ContentScale.Fit,
      modifier = Modifier
        .size(DpSize(200.dp, 160.dp))
        .clip(RoundedCornerShape(10.dp)),
    )

    Spacer(Modifier.height(24.dp))

    Text(
      modifier = modifier
        .widthIn(max = 600.dp)
        .padding(bottom = 4.dp),
      text = stringResource(item.second),
      fontWeight = FontWeight.Bold,
      maxLines = 1,
      color = MaterialTheme.colorScheme.onSurface,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.titleLarge,
    )

    Text(
      modifier = modifier
        .widthIn(max = 600.dp)
        .padding(bottom = 4.dp),
      text = stringResource(item.third),
      color = MaterialTheme.colorScheme.onSurface,
      textAlign = TextAlign.Center,
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, locale = "es")
@Composable
private fun OnboardingItemPreview() {
  CappajvTheme {
    DefaultOnboardingItem(
      item = Triple(
        R.drawable.img_onboarding_01,
        R.string.text_onboarding_title_01,
        R.string.text_onboarding_detail_01,
      ),
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, locale = "es")
@Composable
private fun OnboardingItemPreview2() {
  CappajvTheme {
    DefaultOnboardingItem(
      item = Triple(
        R.drawable.img_onboarding_02,
        R.string.text_onboarding_title_02,
        R.string.text_onboarding_detail_02,
      ),
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, locale = "es")
@Composable
private fun OnboardingItemPreview3() {
  CappajvTheme(dynamicColor = false) {
    DefaultOnboardingItem(
      item = Triple(
        R.drawable.img_onboarding_03,
        R.string.text_onboarding_title_03,
        R.string.text_onboarding_detail_03,
      ),
    )
  }
}
