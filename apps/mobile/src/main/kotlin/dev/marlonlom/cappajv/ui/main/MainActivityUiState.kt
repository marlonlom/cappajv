/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.ui.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import dev.marlonlom.cappajv.core.preferences.entities.UserSettings
import dev.marlonlom.cappajv.mobile.designsystem.theme.CappajvColorContrasts

/**
 * Represents the UI state for the MainActivity.
 *
 * This sealed interface is used to model the different states the UI can be in,
 * such as loading or successfully loaded with user data.
 *
 * @author marlonlom
 */
sealed interface MainActivityUiState {

  /**
   * Represents a loading state where user preferences are being fetched.
   *
   * @author marlonlom
   */
  data object Loading : MainActivityUiState

  /**
   * Represents a successful state where user preferences have been retrieved.
   *
   * @author marlonlom
   *
   * @property userData The retrieved [UserSettings] representing the current user preferences.
   */
  data class Success(val userData: UserSettings) : MainActivityUiState
}

/**
 * Determines whether dynamic color theming should be applied based on the [MainActivityUiState].
 *
 * Dynamic color is disabled during loading. If the state is successful, it reflects
 * the user's preference for dynamic theming.
 *
 * @return `true` if dynamic color should be used; `false` otherwise.
 */
@Composable
fun MainActivityUiState.shouldUseDynamicColor(): Boolean = when (this) {
  MainActivityUiState.Loading -> false
  is MainActivityUiState.Success -> this.userData.useDynamicColor
}

/**
 * Determines whether the dark theme should be applied based on the [MainActivityUiState].
 *
 * If the state is loading, it falls back to the system dark theme setting.
 * If the state is successful, it uses the user's theme preference, defaulting to
 * the system setting if the user has disabled the custom dark theme.
 *
 * @return `true` if dark theme should be used; `false` otherwise.
 */
@Composable
fun MainActivityUiState.shouldUseDarkTheme(): Boolean = when (this) {
  MainActivityUiState.Loading -> isSystemInDarkTheme()
  is MainActivityUiState.Success -> {
    val useDarkTheme = this.userData.useDarkTheme
    if (useDarkTheme.not()) isSystemInDarkTheme() else useDarkTheme
  }
}

/**
 * Determines the color contrast setting to be used based on the current [MainActivityUiState].
 *
 * If the UI is in a loading state, a standard color contrast is applied. If the UI is in a
 * success state, the user's personalized color contrast setting is used.
 *
 * @return The name of the color contrast setting to apply as a [String].
 */
@Composable
fun MainActivityUiState.shouldUseColorContrast(): String = when (this) {
  MainActivityUiState.Loading -> CappajvColorContrasts.STANDARD
  is MainActivityUiState.Success -> this.userData.colorContrast
}.name
