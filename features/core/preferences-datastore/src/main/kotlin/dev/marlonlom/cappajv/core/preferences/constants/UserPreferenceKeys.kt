/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.core.preferences.constants

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * User preference keys manager single object.
 *
 * @author marlonlom
 */
internal object UserPreferenceKeys {

  private val booleanKeys = arrayOf("dark_theme", "dynamic_colors", "is_onboarding")
  private val stringKeys = arrayOf("color_contrast")

  /** User boolean preference key that indicates if using dark theme. */
  val DARK_THEME = booleanPreferencesKey(booleanKeys[0])

  /** User boolean preference key that indicates if using dynamic colors. */
  val DYNAMIC_COLORS = booleanPreferencesKey(booleanKeys[1])

  /** User boolean preference key that indicates if is onboarding. */
  val IS_ONBOARDING = booleanPreferencesKey(booleanKeys[2])

  /** User string preference key for selected color contrast. */
  val COLOR_CONTRAST = stringPreferencesKey(stringKeys[0])

  /**
   * Returns a String preference for selected key.
   *
   * @param key Preference key text for search.
   */
  fun getStringPref(key: String) = when (stringKeys.indexOf(key)) {
    0 -> COLOR_CONTRAST
    else -> null
  }

  /**
   * Returns a Boolean preference for selected key.
   *
   * @param key Preference key text for search.
   */
  fun getBooleanPref(key: String) = when (booleanKeys.indexOf(key)) {
    0 -> DARK_THEME
    1 -> DYNAMIC_COLORS
    2 -> IS_ONBOARDING
    else -> null
  }
}
