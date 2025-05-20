/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.navigation

import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.requestFocus
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class TvScaffoldTabsUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldNotDisplayTabRowButBanner() {
    with(composeTestRule) {
      val tabIndex = 0
      val focusedTab = FocusRequester()
      setContent { TvScaffoldTabs(tabIndex, {}, { false }, focusedTab) }
      onNodeWithTag("tv_tabs_row").isNotDisplayed()
      onNodeWithText("CappaJV").isDisplayed()
    }
  }

  @Test
  fun shouldDisplayTabRowThenClickHomeTab() {
    with(composeTestRule) {
      var tabIndex = 0
      val focusedTab = FocusRequester()
      setContent { TvScaffoldTabs(tabIndex, { tabIndex = it }, { true }, focusedTab) }
      onNodeWithTag("tv_tabs_row").isDisplayed()
      onNodeWithText("Home").isDisplayed()
      onNodeWithText("CappaJV").isDisplayed()
      onNodeWithTag("tv_tab_row_0").isDisplayed()
      onNodeWithTag("tv_tab_row_0").requestFocus().performTouchInput { tabIndex = 0 }

      Truth.assertThat(tabIndex).isEqualTo(0)
    }
  }

  @Test
  fun shouldDisplayTabRowThenClickFavoritesTab() {
    with(composeTestRule) {
      var tabIndex = 0
      val focusedTab = FocusRequester()
      setContent { TvScaffoldTabs(tabIndex, { tabIndex = it }, { true }, focusedTab) }
      onNodeWithTag("tv_tabs_row").isDisplayed()
      onNodeWithText("Favorites").isDisplayed()
      onNodeWithText("CappaJV").isDisplayed()
      onNodeWithTag("tv_tab_row_1").isDisplayed()
      onNodeWithTag("tv_tab_row_1").requestFocus().performTouchInput { tabIndex = 1 }

      Truth.assertThat(tabIndex).isEqualTo(1)
    }
  }

  @Test
  fun shouldDisplayTabRowThenClickSettingsTab() {
    with(composeTestRule) {
      var tabIndex = 0
      val focusedTab = FocusRequester()
      setContent { TvScaffoldTabs(tabIndex, { tabIndex = it }, { true }, focusedTab) }
      onNodeWithTag("tv_tabs_row").isDisplayed()
      onNodeWithText("Settings").isDisplayed()
      onNodeWithText("CappaJV").isDisplayed()
      onNodeWithTag("tv_tab_row_2").isDisplayed()
      onNodeWithTag("tv_tab_row_2").requestFocus().performTouchInput { tabIndex = 2 }

      Truth.assertThat(tabIndex).isEqualTo(2)
    }
  }
}
