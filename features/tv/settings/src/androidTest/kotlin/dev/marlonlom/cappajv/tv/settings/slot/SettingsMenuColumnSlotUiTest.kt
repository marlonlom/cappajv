/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.settings.slot

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class SettingsMenuColumnSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayAboutListItemThenClick() {
    with(composeTestRule) {
      val itemPosition = 0
      var leftFocused = false
      var itemClicked = false
      setContent {
        SettingsMenuColumnSlot(
          onLeftColumnFocused = { b -> leftFocused = b },
          isListItemSelected = { index -> index == itemPosition },
          onListItemSelected = { itemClicked = true }
        )
      }

      onNodeWithText("About").isDisplayed()
      onNodeWithTag("settings_menu_list_btn_0").isDisplayed()
      onNodeWithTag("settings_menu_list_btn_0").performClick().performTouchInput { itemClicked = true }

      assertThat(leftFocused).isFalse()
      assertThat(itemClicked).isTrue()
    }
  }

  @Test
  fun shouldDisplayHelpSupportListItemThenClick() {
    with(composeTestRule) {
      val itemPosition = 1
      var leftFocused = false
      var itemClicked = false
      setContent {
        SettingsMenuColumnSlot(
          onLeftColumnFocused = { b -> leftFocused = b },
          isListItemSelected = { index -> index == itemPosition },
          onListItemSelected = { itemClicked = true }
        )
      }

      onNodeWithText("Help and support").isDisplayed()
      onNodeWithTag("settings_menu_list_btn_1").isDisplayed()
      onNodeWithTag("settings_menu_list_btn_1").performClick().performTouchInput { itemClicked = true }

      assertThat(leftFocused).isFalse()
      assertThat(itemClicked).isTrue()
    }
  }
}
