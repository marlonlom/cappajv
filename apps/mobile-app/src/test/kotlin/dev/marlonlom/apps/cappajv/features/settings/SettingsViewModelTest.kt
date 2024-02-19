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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.Executors

@ExperimentalCoroutinesApi
internal class SettingsViewModelTest {

  private val testDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

  private lateinit var viewModel: SettingsViewModel

  private val dummyDataStore = object : DataStore<Preferences> {
    val dummyData = MutableStateFlow(emptyPreferences())

    override val data: Flow<Preferences>
      get() = dummyData

    override suspend fun updateData(transform: suspend (t: Preferences) -> Preferences): Preferences {
      return transform(dummyData.value)
    }
  }

  @Before
  fun setup() {
    Dispatchers.setMain(testDispatcher)
  }

  @After
  fun tearUp() {
    Dispatchers.resetMain()
    testDispatcher.close()
  }

  @Test
  fun `Should return valid settings from local storage`() = runTest {
    async {
      viewModel = SettingsViewModel(UserPreferencesRepository(dummyDataStore))

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
