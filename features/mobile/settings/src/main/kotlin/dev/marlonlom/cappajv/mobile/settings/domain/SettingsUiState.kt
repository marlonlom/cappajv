/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.settings.domain

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
  data class Success(val settings: UserSettingConfig) : SettingsUiState
}
