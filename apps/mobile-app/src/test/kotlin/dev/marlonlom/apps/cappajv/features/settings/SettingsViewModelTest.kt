/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.settings

import dev.marlonlom.apps.cappajv.core.preferences.UserPreferencesRepository
import dev.marlonlom.apps.cappajv.core.preferences.UserSettings
import dev.marlonlom.apps.cappajv.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
internal class SettingsViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var viewModel: SettingsViewModel

  @Test
  fun `Should return valid settings from local storage`() = runTest {
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
  }
}
