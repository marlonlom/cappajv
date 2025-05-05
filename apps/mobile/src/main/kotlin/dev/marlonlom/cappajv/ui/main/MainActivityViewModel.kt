/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.cappajv.core.preferences.repository.UserPreferencesRepository
import dev.marlonlom.cappajv.ui.main.MainActivityUiState.Loading
import dev.marlonlom.cappajv.ui.main.MainActivityUiState.Success
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel for the main activity of the application.
 *
 * This ViewModel provides a [StateFlow] of [MainActivityUiState] representing the user's preferences
 * and exposes methods to update those preferences. It uses [UserPreferencesRepository] as the data source.
 *
 * @author marlonlom
 *
 * @property repository The repository used to access and update user preferences.
 */
class MainActivityViewModel(private val repository: UserPreferencesRepository) : ViewModel() {

  /**
   * A [StateFlow] that emits the current UI state based on the user preferences.
   *
   * It starts eagerly with an initial value of [Loading] and maps the user preferences flow
   * into a [Success] state when data is available.
   */
  val uiState: StateFlow<MainActivityUiState> = repository
    .userPreferencesFlow
    .map { a -> Success(a) }
    .stateIn(
      scope = viewModelScope,
      initialValue = Loading,
      started = SharingStarted.Eagerly,
    )

  /**
   * Marks the onboarding process as complete by setting the "is_onboarding" preference to false.
   *
   * This function launches a coroutine in the [viewModelScope] to perform the update asynchronously.
   */
  fun setOnboardingComplete() {
    viewModelScope.launch {
      repository.toggleBooleanSetting("is_onboarding", false)
    }
  }
}
