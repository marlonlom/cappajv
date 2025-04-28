/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.mobile.catalog.detail.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Shop
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.cappajv.mobile.catalog.detail.R
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CatalogDetailActionButtonUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayActionButtonThenClick() {
    var buttonClicked = false
    with(composeTestRule) {
      setContent {
        CatalogDetailActionButton(
          buttonText = R.string.text_shop,
          buttonIcon = Icons.TwoTone.Shop,
          buttonTestTag = "detail_shop_btn",
          onButtonClicked = {
            buttonClicked = true
          }
        )
      }

      onNodeWithTag("detail_shop_btn").assertIsDisplayed().performClick()
      assertThat(buttonClicked).isTrue()
    }
  }
}
