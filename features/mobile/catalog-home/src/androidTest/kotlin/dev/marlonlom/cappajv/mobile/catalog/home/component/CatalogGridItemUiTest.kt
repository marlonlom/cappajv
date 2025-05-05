/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.home.component

import android.graphics.drawable.ColorDrawable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.test.FakeImageLoaderEngine
import com.google.common.truth.Truth.assertThat
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
internal class CatalogGridItemUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  private lateinit var testImageLoader: ImageLoader

  @Before
  fun before() {
    val engine = FakeImageLoaderEngine.Builder()
      .intercept("https://notengo.com/chocolate.png", ColorDrawable(Color.Red.toArgb()))
      .default(ColorDrawable(Color.Blue.toArgb()))
      .build()

    testImageLoader = ImageLoader.Builder(ApplicationProvider.getApplicationContext())
      .components { add(engine) }
      .build()
  }

  @Test
  fun shouldDisplayCatalogGridItemThenSelectsIt() {
    with(composeTestRule) {
      var selectedItem = 0L
      val tuple = CatalogItemTuple(
        id = 12345L,
        title = "Chocolate",
        picture = "https://notengo.com/chocolate.png",
        category = "Hot drinks",
        samplePunctuation = "Unit: 1200",
        punctuationsCount = 1,
      )
      setContent {
        CatalogGridItem(
          item = tuple,
          onCatalogGridItemClicked = { selectedItem = it },
          imageLoader = testImageLoader,
        )
      }
      onNodeWithText(tuple.title).assertIsDisplayed()
      onNodeWithTag("catalog_home_cell_${tuple.id}").assertIsDisplayed().performClick()
      assertThat(selectedItem).isEqualTo(tuple.id)
    }
  }
}
