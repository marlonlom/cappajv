/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.onboarding.slot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import dev.marlonlom.cappajv.tv.onboarding.R

/**
 * Internal composable function responsible for rendering the logo on the onboarding screen.
 *
 * @author marlonlom
 */
@Composable
internal fun OnboardingLogoSlot() = Column(
  modifier = Modifier.fillMaxWidth(0.5f),
  verticalArrangement = Arrangement.Center,
) {
  Image(
    modifier = Modifier
      .padding(10.dp)
      .size(180.dp),
    painter = painterResource(R.drawable.img_logo),
    contentDescription = stringResource(R.string.text_onboarding_logo_content_description),
    contentScale = ContentScale.Fit,
    colorFilter = ColorFilter.tint(
      MaterialTheme.colorScheme.onSurface,
    ),
  )
}
