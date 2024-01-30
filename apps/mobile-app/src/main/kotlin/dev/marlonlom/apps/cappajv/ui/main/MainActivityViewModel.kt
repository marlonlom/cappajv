/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.marlonlom.apps.cappajv.core.preferences.UserPreferencesRepository
import dev.marlonlom.apps.cappajv.core.preferences.UserSettings
import dev.marlonlom.apps.cappajv.ui.main.MainActivityUiState.Loading
import dev.marlonlom.apps.cappajv.ui.main.MainActivityUiState.Success
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Main activity viewmodel.
 *
 * @author marlonlom
 *
 * @property repository User preferences repository.
 */
class MainActivityViewModel(
  private val repository: UserPreferencesRepository
) : ViewModel() {

  val uiState: StateFlow<MainActivityUiState> = repository
    .userPreferencesFlow
    .map { a -> Success(a) }
    .stateIn(
      scope = viewModelScope,
      initialValue = Loading,
      started = SharingStarted.Eagerly
    )

  /** Sets onboarding setting to false to indicate it as completed. */
  fun setOnboardingComplete() {
    viewModelScope.launch {
      repository.toggleBooleanSetting("is_onboarding", false)
    }
  }

  companion object {

    /**
     * Returns the factory for main activity viewmodel.
     *
     * @param repository
     */
    fun factory(repository: UserPreferencesRepository) = object : ViewModelProvider.Factory {

      @Suppress("UNCHECKED_CAST")
      override fun <T : ViewModel> create(modelClass: Class<T>): T = MainActivityViewModel(repository) as T

    }
  }
}

/**
 * Main activity ui state sealed interface.
 *
 * @author marlonlom
 */
sealed interface MainActivityUiState {

  /**
   * Loading phase for main activity ui state.
   *
   * @author marlonlom
   */
  data object Loading : MainActivityUiState

  /**
   * Success phase for main activity ui state.
   *
   * @author marlonlom
   *
   * @property userData User settings data.
   */
  data class Success(
    val userData: UserSettings
  ) : MainActivityUiState
}
