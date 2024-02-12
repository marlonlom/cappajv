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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class SettingsViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var viewModel: SettingsViewModel

  @Test
  fun `Should return valid settings from local storage`() = runTest {
    async {
      viewModel = SettingsViewModel(
        UserPreferencesRepository(
          FakeSettingsDatastore(flowOf(emptyPreferences()))
        )
      )

      try {
        val state = viewModel.uiState.first()
        Assert.assertNotNull(state)
      } catch (e: Exception) {
        println(e.message)
        Assert.fail()
      }
    }.await()
  }
}


internal class FakeSettingsDatastore(
  override val data: Flow<Preferences>
) : DataStore<Preferences> {
  override suspend fun updateData(
    transform: suspend (t: Preferences) -> Preferences
  ): Preferences {
    return data.first()
  }

}
