/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.welcome.onboarding

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class DefaultOnboardingScreenUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayOnboardingItem1() {
    with(composeTestRule) {
      setContent {
        DefaultOnboardingScreen(onOnboardingFinished = {})
      }
      onNodeWithText("Welcome").isDisplayed()
      onNodeWithText(
        "Explore our exclusive points catalog, each product has its own points value " +
          "waiting to be discovered.",
      ).isDisplayed()
    }
  }

  @Test
  fun shouldTransitionToOnboardingItem2() {
    with(composeTestRule) {
      setContent {
        DefaultOnboardingScreen(onOnboardingFinished = {})
      }
      onNodeWithTag("onboarding_item_0").onParent().performScrollToIndex(1)
      onNodeWithText("Make it yours!").isDisplayed()
      onNodeWithText(
        "Our catalog allows you to mark your favorite products as favorites, making your " +
          "path to redeem points on your favorite coffee or snack easy and seamless.",
      ).isDisplayed()
    }
  }

  @Test
  fun shouldTransitionToOnboardingItem2ThenBackToItem1() {
    with(composeTestRule) {
      setContent {
        DefaultOnboardingScreen(onOnboardingFinished = {})
      }
      onNodeWithTag("onboarding_item_0").onParent().performScrollToIndex(1)
      onNodeWithText("Make it yours!").isDisplayed()
      onNodeWithText(
        "Our catalog allows you to mark your favorite products as favorites, making your " +
          "path to redeem points on your favorite coffee or snack easy and seamless.",
      ).isDisplayed()
      onNodeWithTag("onboarding_item_1").onParent().performScrollToIndex(0)
      onNodeWithText("Welcome").isDisplayed()
      onNodeWithText(
        "Explore our exclusive points catalog, each product has its own points value " +
          "waiting to be discovered.",
      ).isDisplayed()
    }
  }

  @Test
  fun shouldTransitionToOnboardingItem3AndClickButton() {
    with(composeTestRule) {
      var clicked = false
      setContent {
        DefaultOnboardingScreen(onOnboardingFinished = {
          clicked = true
        })
      }
      onNodeWithTag("onboarding_item_0").onParent().performScrollToIndex(1)
      onNodeWithTag("onboarding_item_1").onParent().performScrollToIndex(2)
      onNodeWithText("Ready, redeem!").isDisplayed()
      onNodeWithText(
        "Once you\'ve chosen your favorites, simply check back through the catalog to " +
          "redeem them every time you visit your nearest Juan Valdez store.",
      ).isDisplayed()
      onNodeWithTag("onboarding_finish_btn").performClick()
      Truth.assertThat(clicked).isTrue()
    }
  }
}
