/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.settings.slot

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.marlonlom.cappajv.tv.settings.R
import dev.marlonlom.cappajv.tv.settings.component.HelpAndSupportSectionItem

/**
 * Internal Composable function that renders the help and support setting slot.
 *
 * @author marlonlom
 */
@Composable
internal fun HelpSupportSettingSlot() {
  Text(
    text = stringResource(R.string.text_help_support_title),
    style = MaterialTheme.typography.headlineSmall,
    color = MaterialTheme.colorScheme.onBackground,
  )

  HelpAndSupportSectionItem(R.string.text_help_support_section_faq)
  HelpAndSupportSectionItem(R.string.text_help_support_section_privacy_policy)
  HelpAndSupportSectionItem(R.string.text_help_support_section_feedback)
}
