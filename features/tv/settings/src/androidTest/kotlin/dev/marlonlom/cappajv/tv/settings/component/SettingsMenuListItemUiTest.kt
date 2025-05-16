/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.settings.component

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.cappajv.tv.settings.domain.SettingsMenu
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class SettingsMenuListItemUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayAboutListItemThenClick() {
    with(composeTestRule) {
      var clicked = false
      setContent {
        SettingsMenuListItem(
          item = SettingsMenu.ABOUT,
          selected = clicked,
          onClick = { clicked = true },
        )
      }

      onNodeWithText("About").isDisplayed()
      onNodeWithTag("settings_menu_list_btn_0").isDisplayed()
      onNodeWithTag("settings_menu_list_btn_0").performClick().performTouchInput { clicked = true }

      assertThat(clicked).isTrue()
    }
  }

  @Test
  fun shouldDisplayHelpSupportListItemThenClick() {
    with(composeTestRule) {
      var clicked = false
      setContent {
        SettingsMenuListItem(
          item = SettingsMenu.HELP_SUPPORT,
          selected = clicked,
          onClick = { clicked = true },
        )
      }

      onNodeWithText("Help and support").isDisplayed()
      onNodeWithTag("settings_menu_list_btn_1").isDisplayed()
      onNodeWithTag("settings_menu_list_btn_1").performClick().performTouchInput { clicked = true }

      assertThat(clicked).isTrue()
    }
  }
}
