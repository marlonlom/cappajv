/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.onboarding

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class OnboardingScreenUiTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayScreenContents() {
    with(composeTestRule) {
      setContent { OnboardingScreen(onOnboardingComplete = {}) }
      onNodeWithContentDescription("Onboarding logo image").isDisplayed()
      onNodeWithText("Welcome").isDisplayed()
      onNodeWithText("Unlock exclusive rewards!", substring = true).isDisplayed()
    }
  }

  @Test
  fun shouldClickOnboardingFinishedButton() {
    var clicked = false
    with(composeTestRule) {
      setContent { OnboardingScreen(onOnboardingComplete = { clicked = true }) }
      onNodeWithTag("onboarding_finish_btn").isDisplayed()
      onNodeWithTag("onboarding_finish_btn").performClick().performTouchInput {
        clicked = true
      }
      Truth.assertThat(clicked).isTrue()
    }
  }
}
