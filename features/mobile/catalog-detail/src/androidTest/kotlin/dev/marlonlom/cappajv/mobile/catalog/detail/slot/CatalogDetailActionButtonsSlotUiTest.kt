/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.mobile.catalog.detail.slot

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.FavoriteBorder
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
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
internal class CatalogDetailActionButtonsSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayButtonsRowThenClickLikeButton() {
    var buttonClicked = false
    with(composeTestRule) {
      setContent {
        CatalogDetailActionButtonsSlot(
          likeButtonIcon = if (buttonClicked) Icons.TwoTone.Favorite else Icons.TwoTone.FavoriteBorder,
          onLikeButtonClicked = { buttonClicked = true },
          onShopButtonClicked = {},
          onShareButtonClicked = {}
        )
      }

      onNodeWithTag("detail_shop_btn").assertIsDisplayed()
      onNodeWithTag("detail_share_btn").assertIsDisplayed()
      onNodeWithTag("detail_like_btn").assertIsDisplayed().performClick()
      assertThat(buttonClicked).isTrue()
    }
  }

  @Test
  fun shouldDisplayButtonsRowThenClickShareButton() {
    var buttonClicked = false
    with(composeTestRule) {
      setContent {
        CatalogDetailActionButtonsSlot(
          likeButtonIcon = Icons.TwoTone.FavoriteBorder,
          onLikeButtonClicked = { },
          onShopButtonClicked = {},
          onShareButtonClicked = { buttonClicked = true }
        )
      }

      onNodeWithTag("detail_shop_btn").assertIsDisplayed()
      onNodeWithTag("detail_like_btn").assertIsDisplayed()
      onNodeWithTag("detail_share_btn").assertIsDisplayed().performClick()
      assertThat(buttonClicked).isTrue()
    }
  }

  @Test
  fun shouldDisplayButtonsRowThenClickShopButton() {
    var buttonClicked = false
    with(composeTestRule) {
      setContent {
        CatalogDetailActionButtonsSlot(
          likeButtonIcon = Icons.TwoTone.Favorite,
          onLikeButtonClicked = { },
          onShopButtonClicked = { buttonClicked = true },
          onShareButtonClicked = {}
        )
      }

      onNodeWithTag("detail_like_btn").assertIsDisplayed()
      onNodeWithTag("detail_share_btn").assertIsDisplayed()
      onNodeWithTag("detail_shop_btn").assertIsDisplayed().performClick()
      assertThat(buttonClicked).isTrue()
    }
  }
}
