/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.home.slot

import androidx.compose.runtime.mutableStateOf
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
internal class CategoryTitleSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayCategoryTitleSlotThenClickIt() {
    with(composeTestRule) {
      val expanded = mutableStateOf(false)
      setContent {
        CategoryTitleSlot(
          title = "Pastry",
          index = 2,
          itemsCount = 0,
          isExpanded = expanded,
          onTitleClicked = {
            expanded.value = expanded.value.not()
          },
        )
      }
      onNodeWithText("Pastry (0)").assertIsDisplayed()
      onNodeWithTag("category_row_2").assertIsDisplayed().performClick()

      assertThat(expanded.value).isTrue()
    }
  }
}
