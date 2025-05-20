/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.cappajv.core.preferences.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the main activity of the application.
 *
 * This ViewModel provides a [StateFlow] of [TvActivityUiState] representing the user's preferences
 * and exposes methods to update those preferences. It uses [UserPreferencesRepository] as the data source.
 *
 * @author marlonlom
 *
 * @property repository The repository used to access and update user preferences.
 */
class TvActivityViewModel(private val repository: UserPreferencesRepository) : ViewModel() {

  private val _uiState = MutableStateFlow<TvActivityUiState>(TvActivityUiState.Starting)

  /** Exposes the current UI state to observers. */
  val uiState: StateFlow<TvActivityUiState> = _uiState

  /**
   * Marks the user as onboarded by updating the onboarding preference.
   */
  fun onOnboarded() {
    viewModelScope.launch {
      repository.toggleBooleanSetting("is_onboarding", false)
    }
  }

  /**
   * Initiates the loading of user preferences and updates the UI state accordingly.
   */
  fun onStarted() {
    _uiState.update { TvActivityUiState.Loading }
    fetchUserPreferences()
  }

  /**
   * Fetches user preferences from the repository and updates the UI state.
   * Handles exceptions by setting the UI state to [TvActivityUiState.Failed].
   */
  private fun fetchUserPreferences() {
    viewModelScope.launch {
      try {
        repository.userPreferencesFlow
          .onEach { userData -> _uiState.update { TvActivityUiState.Ready(userData) } }
          .collect()
      } catch (throwable: Throwable) {
        _uiState.update { TvActivityUiState.Failed(throwable) }
      }
    }
  }
}
