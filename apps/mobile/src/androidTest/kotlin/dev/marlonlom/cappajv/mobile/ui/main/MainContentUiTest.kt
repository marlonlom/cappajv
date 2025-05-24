/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.ui.main

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.cappajv.core.preferences.entities.UserColorContrasts
import dev.marlonlom.cappajv.core.preferences.entities.UserSettings
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class MainContentUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayEmptyScreenWhenUiStateIsLoading() {
    with(composeTestRule) {
      setContent {
        MainContent(MainActivityUiState.Loading) { }
      }
    }
  }

  @Test
  fun shouldDisplayOnboardingScreenWhenUiStateIsSuccess() {
    with(composeTestRule) {
      var userData = UserSettings(
        isOnboarding = true,
        useDarkTheme = false,
        useDynamicColor = false,
        colorContrast = UserColorContrasts.MEDIUM,
      )
      setContent {
        MainContent(
          uiState = MainActivityUiState.Success(userData),
          onOnboardingComplete = {
            userData = userData.copy(isOnboarding = false)
          },
        )
      }
      onNodeWithText("Welcome").isDisplayed()
      onNodeWithText(
        "Explore our exclusive points catalog, each product has its own points value waiting to be discovered.",
      ).isDisplayed()
    }
  }

  @Test
  fun shouldDisplayOnboardingScreenWhenUiStateIsSuccessThenClickButtonToFinish() {
    with(composeTestRule) {
      var userData = UserSettings(
        isOnboarding = true,
        useDarkTheme = false,
        useDynamicColor = false,
        colorContrast = UserColorContrasts.MEDIUM,
      )
      setContent {
        MainContent(
          uiState = MainActivityUiState.Success(userData),
          onOnboardingComplete = {
            userData = userData.copy(isOnboarding = false)
          },
        )
      }
      onNodeWithTag("onboarding_item_0").onParent().performScrollToIndex(1)
      onNodeWithTag("onboarding_item_1").onParent().performScrollToIndex(2)
      onNodeWithText("Ready, redeem!").isDisplayed()
      onNodeWithText(
        "Once you\'ve chosen your favorites, simply check back through the catalog to redeem " +
          "them every time you visit your nearest Juan Valdez store.",
      ).isDisplayed()
      onNodeWithTag("onboarding_finish_btn").performClick()

      assertThat(userData).isNotNull()
      assertThat(userData.isOnboarding).isFalse()
    }
  }

  @Test
  fun shouldDisplayScaffoldScreenWhenUiStateIsSuccess() {
    with(composeTestRule) {
      val userData = UserSettings(
        isOnboarding = false,
        useDarkTheme = false,
        useDynamicColor = false,
        colorContrast = UserColorContrasts.MEDIUM,
      )
      setContent {
        MainContent(
          uiState = MainActivityUiState.Success(userData),
          onOnboardingComplete = {},
        )
      }
    }
  }
}
