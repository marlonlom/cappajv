/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.core.preferences.entities

/**
 * User settings data class.
 *
 * @author marlonlom
 *
 * @property isOnboarding True/False if current screen is onboarding.
 * @property useDarkTheme True/False if dark theme is set.
 * @property useDynamicColor True/False if dynamic colors are set.
 * @property colorContrast Color contrast selected by the user.
 */
data class UserSettings(
  val isOnboarding: Boolean,
  val useDarkTheme: Boolean,
  val useDynamicColor: Boolean,
  val colorContrast: UserColorContrasts,
)
