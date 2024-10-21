/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.welcome.onboarding

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class OnboardingFinishedButtonUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldClickButton() {
    with(composeTestRule) {
      var clicked = false
      setContent {
        OnboardingFinishedButton(onButtonClicked = {
          clicked = true
        })
      }
      onNodeWithText("Get started").isDisplayed()
      onNodeWithTag("onboarding_finish_btn").performClick()
      Truth.assertThat(clicked).isTrue()
    }
  }
}
