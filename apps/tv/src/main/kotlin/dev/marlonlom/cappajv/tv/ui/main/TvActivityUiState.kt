/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.main

import dev.marlonlom.cappajv.core.preferences.entities.UserSettings

/**
 * Represents the UI state for the TvActivity.
 *
 * This sealed interface is used to model the different states the UI can be in,
 * such as loading or successfully loaded with user data.
 *
 * @author marlonlom
 */
sealed interface TvActivityUiState {

  /**
   * Represents the initial state before any action has been taken.
   *
   * @author marlonlom
   */
  data object Starting : TvActivityUiState

  /**
   * Represents the state when data is being loaded.
   *
   * @author marlonlom
   */
  data object Loading : TvActivityUiState

  /**
   * Represents the state when the UI is ready to display data.
   *
   * @author marlonlom
   *
   * @property userData The user settings that are available when the UI is ready.
   */
  data class Ready(val userData: UserSettings) : TvActivityUiState

  /**
   * Represents the state when the UI failed to load the necessary data.
   *
   * @author marlonlom
   *
   * @property failure The error or exception that caused the failure.
   */
  data class Failed(val failure: Throwable) : TvActivityUiState
}
