/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.ui.main

import dev.marlonlom.cappajv.core.preferences.entities.UserSettings

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
