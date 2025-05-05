/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.settings.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class DarkThemeSwitchUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldToggleSwitch() {
    with(composeTestRule) {
      var changed = false
      setContent {
        DarkThemeSwitch(
          isDarkTheme = { changed },
          onCheckedChange = { changed = it },
        )
      }

      onNodeWithText(text = "Dark mode").assertIsDisplayed()
      onNodeWithTag(testTag = "dark_theme_switch").assertIsDisplayed().performClick()

      assertThat(changed).isTrue()
    }
  }
}
