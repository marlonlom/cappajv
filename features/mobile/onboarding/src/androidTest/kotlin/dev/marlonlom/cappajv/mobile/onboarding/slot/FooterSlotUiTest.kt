/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.onboarding.slot

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marlonlom.cappajv.mobile.onboarding.model.DotIndicatorConfig
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class FooterSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayFooterSlot() {
    with(composeTestRule) {
      setContent {
        FooterSlot(
          config = DotIndicatorConfig(
            totalDots = 3,
            selectedIndex = 1,
            selectedColor = MaterialTheme.colorScheme.primary,
            unSelectedColor = MaterialTheme.colorScheme.inversePrimary,
          ),
          showOnboardedButton = true,
          onButtonClicked = {},
        )
      }

      onNodeWithTag("ob_indicator_dot_1").assertIsDisplayed().assertWidthIsEqualTo(10.dp)
      onNodeWithTag("ob_indicator_dot_2").assertIsDisplayed().assertWidthIsEqualTo(20.dp)
      onNodeWithTag("ob_indicator_dot_3").assertIsDisplayed().assertWidthIsEqualTo(10.dp)
      onNodeWithTag("onboarding_finish_btn").assertIsDisplayed()
    }
  }
}
