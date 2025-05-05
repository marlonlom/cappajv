/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.settings.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.cappajv.core.preferences.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * [ViewModel] responsible for managing and providing user settings data to the UI.
 *
 * @author marlonlom
 *
 * @property repository The [UserPreferencesRepository] instance used to access and modify
 * user preferences.
 */
class SettingsViewModel(private val repository: UserPreferencesRepository) : ViewModel() {

  /** User preferences ui state as Flow. */
  val uiState: StateFlow<SettingsUiState> = repository.userPreferencesFlow
    .map {
      SettingsUiState.Success(
        UserSettingConfig(
          useDarkTheme = it.useDarkTheme,
          useDynamicColor = it.useDynamicColor,
          isOnboarding = it.isOnboarding,
          colorContrast = it.colorContrast.name,
        ),
      )
    }.stateIn(
      scope = viewModelScope,
      started = WhileSubscribed(5_000),
      initialValue = SettingsUiState.Loading,
    )

  /**
   * Toggles the boolean value for selected setting key.
   *
   * @param key Boolean preference key.
   * @param booleanValue Boolean preference value for update.
   */
  fun updateBooleanInfo(key: String, booleanValue: Boolean) {
    viewModelScope.launch {
      repository.toggleBooleanSetting(key, booleanValue)
    }
  }

  /**
   * Updates the string value for selected setting key.
   *
   * @param key Boolean preference key.
   * @param stringValue String preference value for update.
   */
  fun updateStringInfo(key: String, stringValue: String) {
    viewModelScope.launch {
      repository.updateStringSetting(key, stringValue)
    }
  }
}
