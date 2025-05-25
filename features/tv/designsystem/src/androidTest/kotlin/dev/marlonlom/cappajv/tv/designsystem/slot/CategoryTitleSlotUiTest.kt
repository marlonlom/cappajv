/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.designsystem.slot

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
internal class CategoryTitleSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplaySlotWithIcon1() {
    with(composeTestRule) {
      setContent { CategoryTitleSlot(title = "Cold drinks", index = 0) }
      onNodeWithText("Cold drinks").isDisplayed()
    }
  }

  @Test
  fun shouldDisplaySlotWithIcon2() {
    with(composeTestRule) {
      setContent { CategoryTitleSlot(title = "Hot drinks", index = 1) }
      onNodeWithText("Hot drinks").isDisplayed()
    }
  }

  @Test
  fun shouldDisplaySlotWithIcon3() {
    with(composeTestRule) {
      setContent { CategoryTitleSlot(title = "Pastry", index = 2) }
      onNodeWithText("Pastry").isDisplayed()
    }
  }
}
