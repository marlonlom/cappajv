/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.cappajv.mobile.settings.component.DarkThemeSwitch
import dev.marlonlom.cappajv.mobile.settings.component.DynamicColorsSwitch
import dev.marlonlom.cappajv.mobile.settings.component.SettingsSectionTitleRow
import dev.marlonlom.cappajv.mobile.settings.domain.SettingsUiState
import dev.marlonlom.cappajv.mobile.settings.domain.SettingsViewModel
import dev.marlonlom.cappajv.mobile.settings.slot.CatalogSettingsHeadlineSlot
import dev.marlonlom.cappajv.mobile.settings.slot.SettingsContentSlot
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(openExternalUrl: (String) -> Unit, viewModel: SettingsViewModel = koinViewModel()) {
  val context = LocalContext.current
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  LazyColumn {
    stickyHeader {
      CatalogSettingsHeadlineSlot()
    }

    when (uiState) {
      SettingsUiState.Loading -> {
        item {
          Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
          ) {
            CircularProgressIndicator()
          }
        }
      }

      is SettingsUiState.Success -> {
        val settings = (uiState as SettingsUiState.Success).settings

        item {
          SettingsContentSlot(
            title = {
              SettingsSectionTitleRow(R.string.text_appearance)
            },
            content = {
              DynamicColorsSwitch(
                isDarkTheme = { settings.useDynamicColor },
                onCheckedChange = { checked ->
                  viewModel.updateBooleanInfo("dynamic_colors", checked)
                },
              )

              DarkThemeSwitch(
                isDarkTheme = { settings.useDarkTheme },
                onCheckedChange = { checked ->
                  viewModel.updateBooleanInfo("dark_theme", checked)
                },
              )
            },
          )
        }

        item {
          SettingsContentSlot(
            title = {
              SettingsSectionTitleRow(R.string.text_legal)
            },
            content = {
              val urls = context.resources.getStringArray(R.array.array_legal_urls)
              val labels = context.resources.getStringArray(R.array.array_legal_labels)
              for (position in 0..2) {
                TextButton(
                  modifier = Modifier.fillMaxWidth(),
                  shape = MaterialTheme.shapes.extraLarge,
                  colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface,
                  ),
                  onClick = {
                    openExternalUrl(urls[position])
                  },
                  content = {
                    Text(
                      modifier = Modifier.fillMaxWidth(),
                      textAlign = TextAlign.Start,
                      text = labels[position],
                    )
                  },
                )
              }
            },
          )
        }

        item {
          SettingsContentSlot(
            title = {
              SettingsSectionTitleRow(R.string.text_information)
            },
            content = {
            },
          )
        }
      }
    }
  }
}
