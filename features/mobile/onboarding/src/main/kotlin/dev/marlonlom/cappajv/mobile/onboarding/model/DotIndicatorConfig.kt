/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.onboarding.model

import androidx.compose.ui.graphics.Color

/**
 * Configuration data class for a dot indicator.
 *
 * @author marlonlom
 *
 * @property totalDots The total number of dots to display in the indicator.
 * @property selectedIndex The index of the currently selected dot (zero-based).
 * @property selectedColor The color to use for the selected dot.
 * @property unSelectedColor The color to use for the unselected dots.
 */
internal data class DotIndicatorConfig(
  val totalDots: Int,
  val selectedIndex: Int,
  val selectedColor: Color,
  val unSelectedColor: Color,
)
