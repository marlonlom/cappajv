/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.designsystem.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.text.TextLayoutResult
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class CappajvTvThemeTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldApplyCorrectPrimaryColorInsideTheme() {
    with(composeTestRule) {
      val testTag = "primaryColorText"
      setContent {
        CappajvTvTheme(isInDarkTheme = true) {
          Text(
            "Dark Primary",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.testTag(testTag)
          )
        }
      }
      onNodeWithTag(testTag).assertTextColor(CappajvColorSchemes.STANDARD.dark.primary)
    }
  }
}

internal fun SemanticsNodeInteraction.assertTextColor(
  color: Color
): SemanticsNodeInteraction = assert(
  SemanticsMatcher(
    "${SemanticsProperties.Text.name} is of color '$color'"
  ) {
    val textLayoutResults = mutableListOf<TextLayoutResult>()
    it.config.getOrNull(SemanticsActions.GetTextLayoutResult)
      ?.action
      ?.invoke(textLayoutResults)
    return@SemanticsMatcher if (textLayoutResults.isEmpty()) {
      false
    } else {
      textLayoutResults.first().layoutInput.style.color == color
    }
  }
)
