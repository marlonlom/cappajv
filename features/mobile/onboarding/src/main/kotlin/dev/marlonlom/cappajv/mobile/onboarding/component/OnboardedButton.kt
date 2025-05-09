/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.onboarding.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import dev.marlonlom.cappajv.mobile.onboarding.R

/**
 * Onboarding complete button composable ui.
 *
 * @author marlonlom
 *
 * @param onButtonClicked Action for continue to home screen button clicked.
 */
@Composable
internal fun OnboardedButton(onButtonClicked: () -> Unit) = Button(
  modifier = Modifier.testTag("onboarding_finish_btn"),
  onClick = { onButtonClicked() },
  shape = MaterialTheme.shapes.small,
  colors = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = MaterialTheme.colorScheme.onPrimary,
  ),
) {
  Text(
    text = stringResource(R.string.text_onboarding_button),
    style = MaterialTheme.typography.bodyLarge,
  )
}
