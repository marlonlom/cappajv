/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.slot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CatalogDetailPunctuationsSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplaySlotWithPunctuations() {
    with(composeTestRule) {
      setContent {
        CatalogDetailPunctuationsSlot(
          punctuations = listOf(
            CatalogPunctuation(11L, 1L, "Small", 1200),
            CatalogPunctuation(12L, 1L, "Medium", 1500),
          ),
          contentPadding = contentPadding,
        )
      }

      onNodeWithText("Small").assertIsDisplayed()
      onNodeWithText("1200").assertIsDisplayed()
      onNodeWithText("Medium").assertIsDisplayed()
      onNodeWithText("1500").assertIsDisplayed()
    }
  }
}
