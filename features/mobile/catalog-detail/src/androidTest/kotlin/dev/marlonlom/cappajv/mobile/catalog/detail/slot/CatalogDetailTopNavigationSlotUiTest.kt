/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.slot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
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
internal class CatalogDetailTopNavigationSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayTopBarWithBackIconButton() {
    with(composeTestRule) {
      var buttonClicked = false
      setContent {
        CatalogDetailTopNavigationSlot(
          showBackButton = { true },
          onNavigateBack = { buttonClicked = true },
        )
      }

      onNodeWithTag("detail_back_navigation_btn").assertIsDisplayed().performClick()
      assertThat(buttonClicked).isTrue()
    }
  }

  @Test
  fun shouldDisplayTopBarWithoutBackIconButton() {
    with(composeTestRule) {
      setContent {
        CatalogDetailTopNavigationSlot(
          showBackButton = { false },
          onNavigateBack = { },
        )
      }

      onNodeWithContentDescription("Icon for back navigation").assertIsNotDisplayed()
    }
  }
}
