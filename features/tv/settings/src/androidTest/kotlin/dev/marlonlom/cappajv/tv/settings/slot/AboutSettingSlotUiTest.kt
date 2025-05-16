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
internal class AboutSettingSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayAboutSettingsContent() {
    with(composeTestRule) {
      setContent {
        AboutSettingSlot(appVersionNumber = "1.0.0")
      }

      onNodeWithText("About CappaJV").isDisplayed()
      onNodeWithText(
        text = "Sample Android application that showcases the product catalog",
        substring = true,
      ).isDisplayed()
      onNodeWithText("Application version").isDisplayed()
      onNodeWithText("1.0.0").isDisplayed()
    }
  }
}
