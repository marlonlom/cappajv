/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.catalog.favorites.slot

import androidx.compose.ui.focus.FocusRequester
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
internal class CatalogEmptyFavoritesSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplaySlotContents() {
    with(composeTestRule) {
      setContent { CatalogEmptyFavoritesSlot(FocusRequester()) }
      onNodeWithText("No favorites yet!").isDisplayed()
      onNodeWithText("Tap the heart icon on any catalog item to add it to your favorites.").isDisplayed()
    }
  }
}
