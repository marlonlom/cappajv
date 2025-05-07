/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.onboarding.slot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.tv.onboarding.R

/**
 * Internal Composable slot for displaying onboarding information.
 *
 * @author marlonlom
 *
 * @param onOnboardingComplete Callback to be invoked when the onboarding process is complete.
 */
@Composable
internal fun OnboardingInfoSlot(onOnboardingComplete: () -> Unit) = Column {
  Text(
    modifier = Modifier
      .paddingFromBaseline(40.dp, 20.dp),
    text = stringResource(R.string.text_onboarding_title),
    color = MaterialTheme.colorScheme.onSurface,
    style = MaterialTheme.typography.displayMedium,
  )

  Text(
    modifier = Modifier.padding(vertical = 10.dp),
    text = stringResource(R.string.text_onboarding_detail),
    color = MaterialTheme.colorScheme.onSurface,
    style = MaterialTheme.typography.bodyMedium,
  )

  OutlinedButton(
    modifier = Modifier
      .testTag("onboarding_finish_btn")
      .padding(top = 30.dp),
    onClick = { onOnboardingComplete() },
  ) {
    Text(
      modifier = Modifier.padding(horizontal = 20.dp),
      text = stringResource(R.string.text_onboarding_button),
      style = MaterialTheme.typography.bodyMedium,
    )
  }
}
