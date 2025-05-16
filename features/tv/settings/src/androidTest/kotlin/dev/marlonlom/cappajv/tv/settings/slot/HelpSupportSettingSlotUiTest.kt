/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.settings.slot

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class HelpSupportSettingSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayHelpSupportSettingContent() {
    with(composeTestRule) {
      setContent {
        HelpSupportSettingSlot()
      }
      onNodeWithText("Help and support").isDisplayed()
      onNodeWithText("FAQ's").isDisplayed()
      onNodeWithText("Privacy policy").isDisplayed()
      onNodeWithText("Feedback").isDisplayed()
    }
  }
}
