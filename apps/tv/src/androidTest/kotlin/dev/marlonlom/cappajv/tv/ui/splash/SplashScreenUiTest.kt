/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.splash

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@MediumTest
internal class SplashScreenUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplaySplashScreen() {
    with(composeTestRule) {
      setContent {
        SplashScreen(onTimeout = {})
      }
      onNodeWithContentDescription("TV Splash screen image").isDisplayed()
    }
  }

  @Test
  fun shouldHandleSplashScreenTimeoutAfterDelay() {
    with(composeTestRule) {
      var timeoutCalled = false
      setContent {
        SplashScreen(onTimeout = { timeoutCalled = true })
      }
      mainClock.advanceTimeBy(2000)
      waitForIdle()
      onNodeWithContentDescription("TV Splash screen image").isDisplayed()
      Truth.assertThat(timeoutCalled).isTrue()
    }
  }
}
