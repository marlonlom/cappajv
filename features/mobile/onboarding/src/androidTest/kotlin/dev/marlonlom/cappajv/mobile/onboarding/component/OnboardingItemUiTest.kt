/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.onboarding.component

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marlonlom.cappajv.mobile.onboarding.R
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class OnboardingItemUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayOnboardingItem01() {
    with(composeTestRule) {
      setContent {
        OnboardingItem(
          testTag = "onboarding_item_0",
          item = Triple(
            R.drawable.img_onboarding_01,
            R.string.text_onboarding_title_01,
            R.string.text_onboarding_detail_01,
          ),
        )
      }

      onNodeWithText("Welcome").isDisplayed()
      onNodeWithText(
        "Explore our exclusive points catalog, each product has its own points value waiting to " +
          "be discovered.",
      ).isDisplayed()
    }
  }

  @Test
  fun shouldDisplayOnboardingItem02() {
    with(composeTestRule) {
      setContent {
        OnboardingItem(
          testTag = "onboarding_item_1",
          item = Triple(
            R.drawable.img_onboarding_02,
            R.string.text_onboarding_title_02,
            R.string.text_onboarding_detail_02,
          ),
        )
      }

      onNodeWithText("Make it yours!").isDisplayed()
      onNodeWithText(
        "Our catalog allows you to mark your favorite products as favorites, making your path " +
          "to redeem points on your favorite coffee or snack easy and seamless.",
      ).isDisplayed()
    }
  }

  @Test
  fun shouldDisplayOnboardingItem03() {
    with(composeTestRule) {
      setContent {
        OnboardingItem(
          testTag = "onboarding_item_2",
          item = Triple(
            R.drawable.img_onboarding_03,
            R.string.text_onboarding_title_03,
            R.string.text_onboarding_detail_03,
          ),
        )
      }

      onNodeWithText("Ready, redeem!").isDisplayed()
      onNodeWithText(
        "Once you\'ve chosen your favorites, simply check back through the catalog to redeem " +
          "them every time you visit your nearest Juan Valdez store.",
      ).isDisplayed()
    }
  }
}
