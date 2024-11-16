/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.welcome.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.ui.theme.CappajvTheme

/**
 * Onboarding dots indicator composable ui.
 *
 * @author marlonlom
 *
 * @param totalDots Total dots to display.
 * @param selectedIndex Selected index.
 * @param selectedColor Selected color.
 * @param unSelectedColor Unselected color.
 * @param modifier Modifier for this composable.
 */
@Composable
internal fun OnboardingDotsIndicator(
  totalDots: Int,
  selectedIndex: Int,
  selectedColor: Color,
  unSelectedColor: Color,
  modifier: Modifier = Modifier,
) {
  LazyRow(
    modifier = modifier
      .background(MaterialTheme.colorScheme.surface)
      .wrapContentWidth()
      .wrapContentHeight(),
  ) {
    items(totalDots) { index ->
      val boxColor = if (index == selectedIndex) selectedColor else unSelectedColor
      Box(
        modifier = Modifier
          .size(10.dp)
          .clip(CircleShape)
          .background(boxColor),
      )

      if (index != totalDots - 1) {
        Spacer(modifier = modifier.padding(horizontal = 8.dp))
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@PreviewLightDark
@Composable
private fun OnboardingDotsIndicatorPreview() {
  CappajvTheme(dynamicColor = false) {
    OnboardingDotsIndicator(
      totalDots = 3,
      selectedIndex = 1,
      selectedColor = MaterialTheme.colorScheme.onPrimaryContainer,
      unSelectedColor = MaterialTheme.colorScheme.primaryContainer,
    )
  }
}
