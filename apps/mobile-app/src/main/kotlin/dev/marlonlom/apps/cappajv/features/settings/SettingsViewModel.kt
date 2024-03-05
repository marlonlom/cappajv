/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.marlonlom.apps.cappajv.core.preferences.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted.Companion.Eagerly
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Catalog settings view model class.
 *
 * @author marlonlom
 *
 * @property repository User preferences repository.
 */
class SettingsViewModel(
  private val repository: UserPreferencesRepository
) : ViewModel() {

  /** User preferences ui state as Flow. */
  val uiState: StateFlow<SettingsUiState> = repository.userPreferencesFlow
    .map {
      SettingsUiState.Success(
        UserEditableSettings(
          useDarkTheme = it.useDarkTheme,
          useDynamicColor = it.useDynamicColor,
          isOnboarding = it.isOnboarding
        )
      )
    }.stateIn(
      scope = viewModelScope,
      started = Eagerly,
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

  companion object {

    /**
     * Provides a factory for creating an instance for view model.
     *
     * @param repository Catalog settings repository dependency.
     * @return
     */
    fun factory(
      repository: UserPreferencesRepository
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
      @Suppress("UNCHECKED_CAST")
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(repository) as T
      }
    }
  }
}

/**
 * User editable settings data class.
 *
 * @author marlonlom
 *
 * @property useDarkTheme True/False if using dark theme.
 * @property useDynamicColor True/False if using dynamic colors.
 */
data class UserEditableSettings(
  val useDarkTheme: Boolean,
  val useDynamicColor: Boolean,
  val isOnboarding: Boolean,
)

/**
 * Settings ui state sealed interface definition.
 *
 * @author marlonlom
 */
sealed interface SettingsUiState {

  /**
   * Loading phase of settings ui state.
   *
   * @author marlonlom
   */
  data object Loading : SettingsUiState

  /**
   * Loading phase of settings ui state.
   *
   * @author marlonlom
   *
   * @property settings User editable settings information.
   */
  data class Success(
    val settings: UserEditableSettings
  ) : SettingsUiState
}
