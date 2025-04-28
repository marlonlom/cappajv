/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CatalogDetailDescriptionTextUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayDescriptionText() {
    val catalogItem = CatalogItem(
      id = 2L,
      title = "Latte",
      picture = "https://noimage.no.com/latte.png",
      category = "Category 1",
      slug = "latte",
      titleNormalized = "latte",
      detail = "Lorem ipsum",
      samplePunctuation = "",
      punctuationsCount = 0,
    )

    with(composeTestRule) {
      setContent {
        CatalogDetailDescriptionText(
          catalogItem = catalogItem,
        )
      }

      onNodeWithText(text = catalogItem.detail, substring = true).assertIsDisplayed()
    }
  }
}
