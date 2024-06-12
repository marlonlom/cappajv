/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.settings

import android.os.Build
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.R
import dev.marlonlom.cappajv.ui.main.CappajvAppState

/**
 * Text links row contents composable ui.
 *
 * @author marlonlom
 *
 * @param openExternalUrl Action for opening oss licences information window.
 * @param openOssLicencesInfo Action for opening oss licences information window.
 */
@ExperimentalLayoutApi
@Composable
internal fun LinksPanelContent(
  openExternalUrl: (String) -> Unit,
  openOssLicencesInfo: () -> Unit,
) {
  FlowRow(
    horizontalArrangement = Arrangement.spacedBy(
      space = 16.dp,
      alignment = Alignment.CenterHorizontally,
    ),
    modifier = Modifier.fillMaxWidth(),
  ) {

    val httpLinks = arrayOf(
      Pair(R.string.text_settings_pdtp, stringResource(R.string.url_settings_pdtp)),
      Pair(R.string.text_settings_privacy_policy, stringResource(R.string.url_settings_privacy_policy)),
      Pair(R.string.text_settings_terms_conditions, stringResource(R.string.url_settings_terms_conditions)),
      Pair(R.string.text_settings_oss_licences, "")
    )

    httpLinks.forEach { item ->
      TextButton(
        colors = ButtonDefaults.textButtonColors(
          contentColor = MaterialTheme.colorScheme.onSurface
        ),
        onClick = {
          if (item.second.isEmpty()) {
            openOssLicencesInfo()
          } else {
            openExternalUrl(item.second)
          }
        }) {
        Text(text = stringResource(item.first))
      }
    }

  }
}

/**
 * Settings section divider composable ui.
 */
@Composable
fun SectionDivider() {
  HorizontalDivider(Modifier.padding(top = 8.dp))
}

/**
 * Boolean settings content composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param editableSettings Editable settings information.
 * @param onBooleanSettingUpdated Action for boolean setting updated.
 */
@Composable
internal fun BooleanSettingsContent(
  appState: CappajvAppState,
  editableSettings: UserEditableSettings,
  onBooleanSettingUpdated: (String, Boolean) -> Unit
) {
  if (appState.isMediumWidth) {
    Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(modifier = Modifier.fillMaxWidth(0.5f)) {
        BooleanSettingSwitchRow(
          titleTextRes = R.string.text_settings_dynamic_colors,
          booleanSettingKey = "dynamic_colors",
          booleanSettingValue = editableSettings.useDynamicColor,
          onBooleanSettingUpdated = onBooleanSettingUpdated,
          switchEnabled = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
        )
      }
      Column {
        BooleanSettingSwitchRow(
          titleTextRes = R.string.text_settings_dark_theme,
          booleanSettingKey = "dark_theme",
          booleanSettingValue = editableSettings.useDarkTheme,
          onBooleanSettingUpdated = onBooleanSettingUpdated,
          switchEnabled = !isSystemInDarkTheme()
        )
      }
    }
  } else {
    Column {
      BooleanSettingSwitchRow(
        titleTextRes = R.string.text_settings_dynamic_colors,
        booleanSettingKey = "dynamic_colors",
        booleanSettingValue = editableSettings.useDynamicColor,
        onBooleanSettingUpdated = onBooleanSettingUpdated,
        switchEnabled = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
      )
      BooleanSettingSwitchRow(
        titleTextRes = R.string.text_settings_dark_theme,
        booleanSettingKey = "dark_theme",
        booleanSettingValue = editableSettings.useDarkTheme,
        onBooleanSettingUpdated = onBooleanSettingUpdated,
        switchEnabled = !isSystemInDarkTheme()
      )
    }
  }
}

/**
 * Boolean switch row content composable ui.
 *
 * @author marlonlom
 *
 * @param titleTextRes Title text as string resource.
 * @param booleanSettingKey Boolean setting entry key.
 * @param booleanSettingValue Boolean setting entry value.
 * @param onBooleanSettingUpdated Action for boolean setting value updated.
 * @param switchEnabled True/False is switch is enabled.
 */
@Composable
private fun BooleanSettingSwitchRow(
  @StringRes titleTextRes: Int,
  booleanSettingKey: String,
  booleanSettingValue: Boolean,
  onBooleanSettingUpdated: (String, Boolean) -> Unit,
  switchEnabled: Boolean = true
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 10.dp, vertical = 4.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      text = stringResource(titleTextRes),
      style = MaterialTheme.typography.titleMedium,
    )
    Switch(
      enabled = switchEnabled,
      checked = booleanSettingValue,
      onCheckedChange = { checked ->
        onBooleanSettingUpdated(booleanSettingKey, checked)
      },
    )
  }
}
