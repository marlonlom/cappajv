/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import dev.marlonlom.cappajv.tv.onboarding.slot.OnboardingInfoSlot
import dev.marlonlom.cappajv.tv.onboarding.slot.OnboardingLogoSlot

/**
 * Displays the onboarding screen for the Android TV application.
 *
 * @author marlonlom
 *
 * @param brandImage The resource ID of the drawable to display as the brand image.
 *
 * @param onOnboardingComplete A callback function that is invoked when the user
 * completes the onboarding process. This could trigger
 * navigation to the main application content.
 */
@Composable
fun OnboardingScreen(@DrawableRes brandImage: Int, onOnboardingComplete: () -> Unit) = Box(
  modifier = Modifier
    .fillMaxSize()
    .paint(
      painterResource(R.drawable.img_onboarding_bg),
      contentScale = ContentScale.Crop,
    )
    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.75f))
    .padding(horizontal = 48.dp),
  contentAlignment = Alignment.Center,
) {
  Row(
    modifier = Modifier.fillMaxWidth(0.75f),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    OnboardingLogoSlot(brandImage)
    OnboardingInfoSlot(onOnboardingComplete)
  }
}
