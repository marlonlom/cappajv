/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.onboarding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.mobile.onboarding.model.DotIndicatorConfig

/**
 * Onboarding dots indicator composable ui.
 *
 * @author marlonlom
 *
 * @param indicatorConfig Dots indicator configuration data.
 */
@Composable
internal fun DotsIndicator(indicatorConfig: DotIndicatorConfig) = LazyRow(
  verticalAlignment = Alignment.CenterVertically,
  modifier = Modifier
    .background(MaterialTheme.colorScheme.surface)
    .wrapContentWidth()
    .wrapContentHeight(),
) {
  with(indicatorConfig) {
    items(totalDots) { index ->
      val isSelected = index == selectedIndex
      val boxColor = if (isSelected) selectedColor else unSelectedColor
      val boxSize = if (isSelected) DpSize(20.dp, 10.dp) else DpSize(10.dp, 10.dp)

      Box(
        modifier = Modifier
          .testTag("ob_indicator_dot_${index + 1}")
          .size(boxSize)
          .clip(CircleShape)
          .background(boxColor),
      )

      if (index != totalDots - 1) {
        Spacer(Modifier.padding(horizontal = 4.dp))
      }
    }
  }
}
