/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.designsystem.component

import android.graphics.drawable.ColorDrawable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.test.FakeImageLoaderEngine
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@OptIn(ExperimentalCoilApi::class)
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CatalogItemTvImageUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  private lateinit var testImageLoader: ImageLoader

  @Test
  fun shouldDisplayImage() {
    val engine = FakeImageLoaderEngine.Builder()
      .intercept("https://noimage.no.com/latte.png", ColorDrawable(Color.Red.toArgb()))
      .default(ColorDrawable(Color.Blue.toArgb()))
      .build()

    testImageLoader = ImageLoader.Builder(ApplicationProvider.getApplicationContext())
      .components { add(engine) }
      .build()

    with(composeTestRule) {
      setContent {
        CatalogItemTvImage(
          itemTitle = "Latte",
          itemPicture = "https://noimage.no.com/latte.png",
          imageLoader = testImageLoader,
        )
      }

      onNodeWithContentDescription("Latte").isDisplayed()
    }
  }
}
