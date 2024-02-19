/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.settings

import dev.marlonlom.apps.cappajv.core.preferences.UserPreferencesRepository
import dev.marlonlom.apps.cappajv.core.preferences.UserSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.util.concurrent.Executors

@ExperimentalCoroutinesApi
internal class SettingsViewModelTest {

  private val testDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

  private lateinit var viewModel: SettingsViewModel

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
      val mockPreferencesRepository = mock(UserPreferencesRepository::class.java)
      `when`(mockPreferencesRepository.userPreferencesFlow).thenReturn(
        flowOf(
          UserSettings(
            useDarkTheme = false,
            useDynamicColor = true,
            isOnboarding = true
          )
        )
      )

      viewModel = SettingsViewModel(mockPreferencesRepository)

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
