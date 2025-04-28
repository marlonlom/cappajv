/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.component

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
internal class CatalogDetailPunctuationBoxUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplaySinglePunctuation() {
    with(composeTestRule) {
      setContent {
        CatalogDetailPunctuationBox(
          punctuation = CatalogPunctuation(
            id = 1L,
            catalogItemId = 1L,
            label = "Unit",
            points = 1234,
          ),
        )
      }

      onNodeWithText("Unit").assertIsDisplayed()
      onNodeWithText("1234").assertIsDisplayed()
    }
  }
}
