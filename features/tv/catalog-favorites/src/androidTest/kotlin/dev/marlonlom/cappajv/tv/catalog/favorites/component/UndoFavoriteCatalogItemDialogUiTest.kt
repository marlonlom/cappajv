/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.catalog.favorites.component

import android.graphics.drawable.ColorDrawable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.test.isDisplayed
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
import com.google.common.truth.Truth
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@OptIn(ExperimentalCoilApi::class)
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class UndoFavoriteCatalogItemDialogUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  private lateinit var testImageLoader: ImageLoader

  private val sampleCatalogItemTuple: () -> CatalogItemTuple = {
    CatalogItemTuple(
      id = 123L,
      title = "Macchiato",
      picture = "https://images.example.org/items/macchiato",
      category = "Hot drinks",
      samplePunctuation = "Unit: 1500",
      punctuationsCount = 1,
    )
  }

  @Before
  fun prepareImageLoader() {
    val engine = FakeImageLoaderEngine.Builder()
      .intercept("https://noimage.no.com/latte.png", ColorDrawable(Color.Red.toArgb()))
      .default(ColorDrawable(Color.Blue.toArgb()))
      .build()

    testImageLoader = ImageLoader.Builder(ApplicationProvider.getApplicationContext())
      .components { add(engine) }
      .build()
  }

  @Test
  fun shouldDisplayDialogThenPerformDismissal() {
    with(composeTestRule) {
      val tuple = sampleCatalogItemTuple()
      var dismissed = false
      setContent {
        UndoFavoriteCatalogItemDialog(
          item = tuple,
          onConfirm = {},
          onDismiss = { dismissed = true },
          imageLoader = testImageLoader,
        )
      }
      onNodeWithText("Remove from favorites").isDisplayed()
      onNodeWithText(tuple.title, substring = true).isDisplayed()
      onNodeWithText("Confirm").isDisplayed()
      onNodeWithText("Dismiss").isDisplayed()
      onNodeWithTag("undo_favorite_dialog_confirm_btn").isDisplayed()
      onNodeWithTag("undo_favorite_dialog_dismiss_btn").isDisplayed()
      onNodeWithTag("undo_favorite_dialog_dismiss_btn").performClick().performTouchInput {
        dismissed = true
      }
      Truth.assertThat(dismissed).isTrue()
    }
  }

  @Test
  fun shouldDisplayDialogThenPerformConfirmation() {
    with(composeTestRule) {
      val tuple = sampleCatalogItemTuple()
      var confirmed = false
      setContent {
        UndoFavoriteCatalogItemDialog(
          item = tuple,
          onConfirm = { confirmed = true },
          onDismiss = { },
          imageLoader = testImageLoader,
        )
      }
      onNodeWithText("Remove from favorites").isDisplayed()
      onNodeWithText(tuple.title, substring = true).isDisplayed()
      onNodeWithText("Confirm").isDisplayed()
      onNodeWithText("Dismiss").isDisplayed()
      onNodeWithTag("undo_favorite_dialog_dismiss_btn").isDisplayed()
      onNodeWithTag("undo_favorite_dialog_confirm_btn").isDisplayed()
      onNodeWithTag("undo_favorite_dialog_confirm_btn").performClick().performTouchInput {
        confirmed = true
      }
      Truth.assertThat(confirmed).isTrue()
    }
  }
}
