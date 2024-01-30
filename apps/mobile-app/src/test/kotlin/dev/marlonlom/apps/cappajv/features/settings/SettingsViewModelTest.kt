/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import dev.marlonlom.apps.cappajv.core.preferences.UserPreferencesRepository
import dev.marlonlom.apps.cappajv.util.MainDispatcherRule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class SettingsViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var viewModel: SettingsViewModel

  @Before
  fun setUp() {
    viewModel = SettingsViewModel(
      UserPreferencesRepository(
        FakeSettingsDatastore(flowOf(emptyPreferences()))
      )
    )
  }

  @Test
  fun `Should return valid settings from local storage`() = runTest {
    val setting = viewModel.uiState.value
    Assert.assertNotNull(setting)
  }

}

internal class FakeSettingsDatastore(override val data: Flow<Preferences>) : DataStore<Preferences> {
  override suspend fun updateData(transform: suspend (t: Preferences) -> Preferences): Preferences {
    return emptyPreferences()
  }

}
