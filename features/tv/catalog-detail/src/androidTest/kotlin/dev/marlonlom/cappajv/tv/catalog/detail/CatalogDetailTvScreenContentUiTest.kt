/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.catalog.detail

import android.graphics.drawable.ColorDrawable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.test.FakeImageLoaderEngine
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation
import dev.marlonlom.cappajv.domain.catalog.detail.CatalogDetailItem
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@OptIn(ExperimentalCoilApi::class)
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CatalogDetailTvScreenContentUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  private lateinit var testImageLoader: ImageLoader

  @Before
  fun before() {
    val engine = FakeImageLoaderEngine.Builder()
      .intercept("https://notengo.com/latte.png", ColorDrawable(Color.Red.toArgb()))
      .default(ColorDrawable(Color.Blue.toArgb()))
      .build()

    testImageLoader = ImageLoader.Builder(ApplicationProvider.getApplicationContext())
      .components { add(engine) }
      .build()
  }

  @Test
  fun shouldDisplayContentThenClickLikeButton() {
    with(composeTestRule) {
      var favorite = false
      val detailItem = CatalogDetailItem(
        product = CatalogItem(
          id = 2L,
          title = "Latte",
          picture = "https://noimage.no.com/latte.png",
          category = "Category 1",
          slug = "latte",
          titleNormalized = "latte",
          detail = "Lorem ipsum",
          samplePunctuation = "",
          punctuationsCount = 0,
        ),
        isFavorite = favorite,
        points = listOf(
          CatalogPunctuation(
            id = 1L,
            catalogItemId = 1L,
            label = "Unit",
            points = 1200L,
          ),
        ),
      )
      setContent {
        CatalogDetailTvScreenContent(
          detailItem = detailItem,
          onFavoriteChanged = { _, flag ->
            favorite = flag
          },
          imageLoader = testImageLoader,
        )
      }

      onNodeWithText(detailItem.product.category).assertIsDisplayed()
      onNodeWithText(detailItem.product.title).assertIsDisplayed()
      onNodeWithText(detailItem.product.detail).assertIsDisplayed()

      onNodeWithText(detailItem.points.first().label).assertIsDisplayed()
      onNodeWithText(detailItem.points.first().points.toString()).assertIsDisplayed()

      onNodeWithTag("catalog_detail_tv_like_btn").assertIsDisplayed().performClick().performTouchInput {
        favorite = true
      }
      assertThat(favorite).isTrue()
    }
  }
}
