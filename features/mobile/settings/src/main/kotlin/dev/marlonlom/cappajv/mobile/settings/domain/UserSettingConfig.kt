/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.settings.domain

/**
 * Data class representing the configuration settings for a user.
 *
 * @author marlonlom
 *
 * @property isOnboarding Indicates whether the onboarding flow has been completed by the user.
 * `true` if onboarding is complete, `false` otherwise.
 * @property useDynamicColor Indicates whether the application should use dynamic color theming
 * based on the user's wallpaper. `true` to use dynamic color, `false` otherwise.
 * @property useDarkTheme Indicates whether the application should use a dark theme.
 * `true` to use dark theme, `false` otherwise.
 * @property colorContrast Represents the color contrast setting chosen by the user.
 * (e.g., "standard", "high").
 */
data class UserSettingConfig(
  val isOnboarding: Boolean,
  val useDynamicColor: Boolean,
  val useDarkTheme: Boolean,
  val colorContrast: String,
)
