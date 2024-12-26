/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.categories

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.requestFocus
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class CategoriesTabsRowUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldClickCategoryTab1() {
    with(composeTestRule) {
      val tabIndex = mutableIntStateOf(0)
      setContent {
        CategoriesTabsRow(
          selectedTabIndex = tabIndex.intValue,
          onTabSelected = { index ->
            println("onTabSelected($index)")
            tabIndex.intValue = index
          },
        )
      }
      val categoryButtonNode = onNodeWithTag("category_tab_0")
      categoryButtonNode.isDisplayed()
      categoryButtonNode.requestFocus()
        .performClick()
        .performTouchInput {
          tabIndex.intValue = 0
        }
      Truth.assertThat(tabIndex.intValue).isEqualTo(0)
    }
  }

  @Test
  fun shouldClickCategoryTab2() {
    with(composeTestRule) {
      val tabIndex = mutableIntStateOf(0)
      setContent {
        CategoriesTabsRow(
          selectedTabIndex = tabIndex.intValue,
          onTabSelected = { index ->
            tabIndex.intValue = index
          },
        )
      }
      val categoryButtonNode = onNodeWithTag("category_tab_1")
      categoryButtonNode.isDisplayed()
      categoryButtonNode.requestFocus()
        .performClick()
        .performTouchInput {
          tabIndex.intValue = 1
        }
      Truth.assertThat(tabIndex.intValue).isEqualTo(1)
    }
  }

  @Test
  fun shouldClickCategoryTab3() {
    with(composeTestRule) {
      val tabIndex = mutableIntStateOf(0)
      setContent {
        CategoriesTabsRow(
          selectedTabIndex = tabIndex.intValue,
          onTabSelected = { index ->
            tabIndex.intValue = index
          },
        )
      }
      val categoryButtonNode = onNodeWithTag("category_tab_2")
      categoryButtonNode.isDisplayed()
      categoryButtonNode.requestFocus()
        .performClick()
        .performTouchInput {
          tabIndex.intValue = 2
        }
      Truth.assertThat(tabIndex.intValue).isEqualTo(2)
    }
  }
}
