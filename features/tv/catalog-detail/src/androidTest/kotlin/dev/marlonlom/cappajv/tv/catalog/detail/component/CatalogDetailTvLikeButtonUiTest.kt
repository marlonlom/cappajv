/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.catalog.detail.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CatalogDetailTvLikeButtonUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayButtonThenClick() {
    with(composeTestRule) {
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
      var favorite = false
      setContent {
        CatalogDetailTvLikeButton(
          item = catalogItem,
          isFavorite = favorite,
          onFavoriteChanged = { _, flag ->
            favorite = flag
          },
        )
      }
      onNodeWithTag("catalog_detail_tv_like_btn").assertIsDisplayed().performClick().performTouchInput {
        favorite = true
      }
      assertThat(favorite).isTrue()
    }
  }
}
