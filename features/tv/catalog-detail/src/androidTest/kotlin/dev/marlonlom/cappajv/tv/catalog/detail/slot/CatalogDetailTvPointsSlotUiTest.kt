/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.catalog.detail.slot

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
internal class CatalogDetailTvPointsSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayRowWithOnePunctuation() {
    with(composeTestRule) {
      val punctuation = CatalogPunctuation(
        id = 1L,
        catalogItemId = 1L,
        label = "Unit",
        points = 1200L,
      )
      setContent {
        CatalogDetailTvPointsSlot(listOf(punctuation))
      }
      onNodeWithText(punctuation.label).assertIsDisplayed()
      onNodeWithText(punctuation.points.toString()).assertIsDisplayed()
    }
  }
}
