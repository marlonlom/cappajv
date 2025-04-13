/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.mobile.onboarding.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marlonlom.cappajv.mobile.onboarding.model.DotIndicatorConfig
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class DotsIndicatorUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayDotsIndicator() {
    with(composeTestRule) {
      setContent {
        DotsIndicator(
          indicatorConfig = DotIndicatorConfig(
            totalDots = 3,
            selectedIndex = 1,
            selectedColor = MaterialTheme.colorScheme.primary,
            unSelectedColor = MaterialTheme.colorScheme.inversePrimary,
          )
        )
      }

      onNodeWithTag("ob_indicator_dot_1").assertIsDisplayed()
      onNodeWithTag("ob_indicator_dot_2").assertIsDisplayed()
      onNodeWithTag("ob_indicator_dot_3").assertIsDisplayed()
    }
  }
}
