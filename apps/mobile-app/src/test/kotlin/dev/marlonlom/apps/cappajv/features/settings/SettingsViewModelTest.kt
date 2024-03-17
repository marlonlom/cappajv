/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.mutablePreferencesOf
import dev.marlonlom.apps.cappajv.core.preferences.UserPreferencesRepository
import dev.marlonlom.apps.cappajv.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
internal class SettingsViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var viewModel: SettingsViewModel

  @Mock
  private lateinit var mockDataStore: DataStore<Preferences>

  @Before
  fun setup() {
    MockitoAnnotations.openMocks(this)
  }


  @Test
  fun `Should return sampled settings from local storage`() = runTest {
    `when`(mockDataStore.data).thenReturn(
      flowOf(
        mutablePreferencesOf(
          booleanPreferencesKey("dark_theme") to true,
          booleanPreferencesKey("dynamic_colors") to true,
          booleanPreferencesKey("is_onboarding") to false
        )
      )
    )
    viewModel = SettingsViewModel(UserPreferencesRepository(mockDataStore))
    val uiState = viewModel.uiState.first()
    assertNotNull(uiState)
    when (uiState) {
      is SettingsUiState.Success -> {
        assertNotNull(uiState.settings)
        assertTrue(uiState.settings.useDarkTheme)
        assertTrue(uiState.settings.useDynamicColor)
        assertFalse(uiState.settings.isOnboarding)
      }

      else -> fail()
    }
  }

  @Test
  fun `Should return default settings from local storage`() = runTest {
    `when`(mockDataStore.data).thenReturn(flowOf(emptyPreferences()))
    viewModel = SettingsViewModel(UserPreferencesRepository(mockDataStore))
    val uiState = viewModel.uiState.first()
    assertNotNull(uiState)
    when (uiState) {
      is SettingsUiState.Success -> {
        assertNotNull(uiState.settings)
        assertFalse(uiState.settings.useDarkTheme)
        assertTrue(uiState.settings.useDynamicColor)
        assertTrue(uiState.settings.isOnboarding)
      }

      else -> fail()
    }
  }
}
