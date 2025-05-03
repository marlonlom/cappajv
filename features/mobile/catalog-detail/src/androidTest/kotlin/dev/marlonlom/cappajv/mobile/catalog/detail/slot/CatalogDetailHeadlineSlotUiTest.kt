/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.slot

import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.test.FakeImageLoaderEngine
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@OptIn(ExperimentalCoilApi::class)
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CatalogDetailHeadlineSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  private lateinit var testImageLoader: ImageLoader

  @Before
  fun before() {
    val engine = FakeImageLoaderEngine.Builder()
      .intercept("https://noimage.no.com/latte.png", ColorDrawable(Color.Red.toArgb()))
      .default(ColorDrawable(Color.Blue.toArgb()))
      .build()

    testImageLoader = ImageLoader.Builder(ApplicationProvider.getApplicationContext())
      .components { add(engine) }
      .build()
  }

  @Test
  fun shouldDisplaySlotWithPunctuations() {
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
        CatalogDetailHeadlineSlot(
          catalogItem,
          testImageLoader,
          PaddingValues(0.dp),
        )
      }

      onNodeWithContentDescription(label = catalogItem.title, substring = true).assertIsDisplayed()
      onNodeWithText(text = catalogItem.category, substring = true).assertIsDisplayed()
      onNodeWithText(text = catalogItem.title, substring = true).assertIsDisplayed()
    }
  }
}
