/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.onboarding.slot

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class OnboardingLogoSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayOnboardingLogoSlot() {
    val instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
    val imageResourceId = instrumentationContext.resources.getIdentifier(
      "img_logo",
      "drawable",
      instrumentationContext.packageName
    )
    with(composeTestRule) {
      setContent { OnboardingLogoSlot(imageResourceId) }
      onNodeWithContentDescription("Onboarding logo image").isDisplayed()
    }
  }
}
