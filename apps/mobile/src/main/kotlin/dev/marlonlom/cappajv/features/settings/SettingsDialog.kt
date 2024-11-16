/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.cappajv.R
import dev.marlonlom.cappajv.ui.main.CappajvAppState
import org.koin.androidx.compose.koinViewModel

/**
 * Settings dialog route composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param viewModel Settings viewmodel.
 * @param onDialogDismissed Action for dialog closed.
 * @param openOssLicencesInfo Action for opening oss licences information window.
 * @param openExternalUrl Action for opening oss licences information window.
 */
@ExperimentalLayoutApi
@Composable
fun SettingsDialog(
  appState: CappajvAppState,
  onDialogDismissed: () -> Unit,
  openOssLicencesInfo: () -> Unit,
  openExternalUrl: (String) -> Unit,
  viewModel: SettingsViewModel = koinViewModel(),
) {
  val settingsUiState by viewModel.uiState.collectAsStateWithLifecycle(
    lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current,
  )
  when (settingsUiState) {
    is SettingsUiState.Success -> {
      SettingsDialogContent(
        appState = appState,
        editableSettings = (settingsUiState as SettingsUiState.Success).settings,
        onDialogDismissed = onDialogDismissed,
        onBooleanSettingUpdated = viewModel::updateBooleanInfo,
        openOssLicencesInfo = openOssLicencesInfo,
        openExternalUrl = openExternalUrl,
      )
    }

    else -> Unit
  }
}

/**
 * Settings dialog content composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param editableSettings Editable settings information.
 * @param onDialogDismissed Action for dialog closed.
 * @param onBooleanSettingUpdated Action for boolean setting updated.
 * @param openOssLicencesInfo Action for opening oss licences information window.
 * @param openExternalUrl Action for opening oss licences information window.
 */
@ExperimentalLayoutApi
@Composable
internal fun SettingsDialogContent(
  appState: CappajvAppState,
  editableSettings: UserEditableSettings,
  onDialogDismissed: () -> Unit,
  onBooleanSettingUpdated: (String, Boolean) -> Unit,
  openOssLicencesInfo: () -> Unit,
  openExternalUrl: (String) -> Unit,
) {
  val configuration = LocalConfiguration.current
  AlertDialog(
    shape = MaterialTheme.shapes.large,
    modifier = Modifier.widthIn(max = configuration.screenWidthDp.dp - 80.dp),
    properties = DialogProperties(
      usePlatformDefaultWidth = false,
    ),
    onDismissRequest = {
      onDialogDismissed()
    },
    title = {
      Text(
        text = stringResource(id = R.string.destination_settings),
        fontWeight = FontWeight.SemiBold,
        style = MaterialTheme.typography.titleLarge,
      )
    },
    text = {
      Column {
        HorizontalDivider()
        BooleanSettingsContent(
          appState,
          editableSettings,
          onBooleanSettingUpdated,
        )
        SectionDivider()
        LinksPanelContent(openExternalUrl, openOssLicencesInfo)
        HorizontalDivider()
      }
    },
    confirmButton = {
      Text(
        text = stringResource(R.string.text_settings_dismiss),
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
          .padding(horizontal = 8.dp)
          .clickable {
            onDialogDismissed()
          },
      )
    },
  )
}
