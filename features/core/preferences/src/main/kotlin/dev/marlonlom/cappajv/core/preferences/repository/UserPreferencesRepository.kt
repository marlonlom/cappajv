/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.core.preferences.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import dev.marlonlom.cappajv.core.preferences.constants.UserPreferenceKeys
import dev.marlonlom.cappajv.core.preferences.entities.UserColorContrasts
import dev.marlonlom.cappajv.core.preferences.entities.UserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * User preferences repository class.
 *
 * @author marlonlom
 *
 * @property dataStore Preferences datastore.
 */
class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {

  /** User preferences information as Flow. */
  val userPreferencesFlow: Flow<UserSettings> = dataStore.data
    .catch { throwable ->
      if (throwable is IOException) {
        emit(emptyPreferences())
      } else {
        throw throwable
      }
    }
    .map { prefs ->
      val useDarkTheme = prefs[UserPreferenceKeys.DARK_THEME] ?: false
      val useDynamicColors = prefs[UserPreferenceKeys.DYNAMIC_COLORS] ?: true
      val isOnboarding = prefs[UserPreferenceKeys.IS_ONBOARDING] ?: true
      val colorContrast = prefs[UserPreferenceKeys.COLOR_CONTRAST] ?: UserColorContrasts.STANDARD.name

      UserSettings(
        useDarkTheme = useDarkTheme,
        useDynamicColor = useDynamicColors,
        isOnboarding = isOnboarding,
        colorContrast = UserColorContrasts.valueOf(colorContrast),
      )
    }

  /**
   * Updates the string value for selected setting key.
   *
   * @param stringKey String preference key.
   * @param newStringValue String preference value for update.
   */
  suspend fun updateStringSetting(stringKey: String, newStringValue: String) {
    dataStore.edit { preferences ->
      val stringPref = UserPreferenceKeys.getStringPref(stringKey)
      if (stringPref != null) preferences[stringPref] = newStringValue
    }
  }

  /**
   * Toggles the boolean value for selected setting key.
   *
   * @param booleanKey Boolean preference key.
   * @param newBooleanValue Boolean preference value for update.
   */
  suspend fun toggleBooleanSetting(booleanKey: String, newBooleanValue: Boolean) {
    dataStore.edit { preferences ->
      val booleanPref = UserPreferenceKeys.getBooleanPref(booleanKey)
      if (booleanPref != null) preferences[booleanPref] = newBooleanValue
    }
  }
}
