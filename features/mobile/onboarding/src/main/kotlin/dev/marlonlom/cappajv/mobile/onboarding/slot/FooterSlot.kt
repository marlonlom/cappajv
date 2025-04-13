/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.onboarding.slot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.mobile.onboarding.component.DotsIndicator
import dev.marlonlom.cappajv.mobile.onboarding.component.OnboardedButton
import dev.marlonlom.cappajv.mobile.onboarding.model.DotIndicatorConfig

/**
 * Onboarding footer slot composable.
 * @author marlonlom
 *
 * @param config Dots indicator config.
 * @param showOnboardedButton True/False if showing onboarded button
 * @param onButtonClicked Action for onboarded button clicked.
 */
@Composable
internal fun FooterSlot(config: DotIndicatorConfig, showOnboardedButton: Boolean, onButtonClicked: () -> Unit) = Row(
  modifier = Modifier
    .fillMaxWidth()
    .height(48.dp),
  verticalAlignment = Alignment.CenterVertically,
  horizontalArrangement = Arrangement.SpaceBetween,
) {
  DotsIndicator(indicatorConfig = config)
  if (showOnboardedButton) {
    OnboardedButton(onButtonClicked)
  }
}
