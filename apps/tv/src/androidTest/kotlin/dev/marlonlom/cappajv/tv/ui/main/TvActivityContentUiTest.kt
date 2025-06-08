/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.main

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.cappajv.core.preferences.entities.UserColorContrasts
import dev.marlonlom.cappajv.core.preferences.entities.UserSettings
import dev.marlonlom.cappajv.tv.designsystem.theme.CappajvTvTheme
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class TvActivityContentUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayEmptyScreenWhenUiStateIsLoading() {
    with(composeTestRule) {
      setContent {
        CappajvTvTheme {
          TvActivityContent(
            uiState = TvActivityUiState.Loading,
            onOnboarded = { },
            onStarted = { },
          )
        }
      }
      onNodeWithContentDescription("TV Splash screen image").isNotDisplayed()
    }
  }

  @Test
  fun shouldDisplaySplashScreenWhenUiStateIsStarting() {
    with(composeTestRule) {
      var splashFinished = false
      setContent {
        CappajvTvTheme {
          TvActivityContent(
            uiState = TvActivityUiState.Starting,
            onOnboarded = { },
            onStarted = { splashFinished = true },
          )
        }
      }
      mainClock.advanceTimeBy(2000)
      waitForIdle()
      onNodeWithContentDescription("TV Splash screen image").isDisplayed()
      assertThat(splashFinished).isTrue()
    }
  }

  @Test
  fun shouldDisplayOnboardingScreenWhenUiStateIsReady() {
    with(composeTestRule) {
      val expectedData = UserSettings(
        isOnboarding = true,
        useDarkTheme = false,
        useDynamicColor = false,
        colorContrast = UserColorContrasts.STANDARD,
      )
      var onboarded = false
      setContent {
        CappajvTvTheme {
          TvActivityContent(
            uiState = TvActivityUiState.Ready(userData = expectedData),
            onOnboarded = { onboarded = true },
            onStarted = { },
          )
        }
      }
      onNodeWithContentDescription("Onboarding logo image").isDisplayed()
      onNodeWithText("Welcome").isDisplayed()
      onNodeWithText("Unlock exclusive rewards!", substring = true).isDisplayed()
      onNodeWithTag("onboarding_finish_btn").performClick().performTouchInput {
        onboarded = true
      }
      assertThat(onboarded).isTrue()
    }
  }

  @Test
  fun shouldDisplayScaffoldScreenWhenUiStateIsReady() {
    with(composeTestRule) {
      val expectedData = UserSettings(
        isOnboarding = false,
        useDarkTheme = false,
        useDynamicColor = false,
        colorContrast = UserColorContrasts.STANDARD,
      )
      setContent {
        CappajvTvTheme {
          TvActivityContent(
            uiState = TvActivityUiState.Ready(userData = expectedData),
            onOnboarded = { },
            onStarted = { },
          )
        }
      }
      onNodeWithTag("tv_tabs_row").isDisplayed()
      onNodeWithText("CappaJV").isDisplayed()
    }
  }
}
