/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.ui.main

import dev.marlonlom.cappajv.core.preferences.entities.UserColorContrasts
import dev.marlonlom.cappajv.core.preferences.entities.UserSettings
import dev.marlonlom.cappajv.core.preferences.repository.UserPreferencesRepository
import dev.marlonlom.cappajv.mobile.ui.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@OptIn(ExperimentalCoroutinesApi::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class MainActivityViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val repository = mockk<UserPreferencesRepository>()
  private lateinit var viewModel: MainActivityViewModel

  @Test(expected = RuntimeException::class)
  fun `Should emit loading result after throw exception when finding user preferences`() = runTest {
    coEvery { repository.userPreferencesFlow } throws RuntimeException("huh")

    viewModel = MainActivityViewModel(repository)

    val result = mutableListOf<MainActivityUiState>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.uiState.toList(result)
    }

    advanceUntilIdle()

    val foundItem = result.last()
    assertNotNull(foundItem)
    if (foundItem != MainActivityUiState.Loading) {
      fail()
    }

    job.cancel()
  }

  @Test
  fun `Should emit success result when finding user preferences`() = runTest {
    coEvery { repository.userPreferencesFlow } returns flowOf(
      UserSettings(
        isOnboarding = true,
        useDarkTheme = false,
        useDynamicColor = false,
        colorContrast = UserColorContrasts.STANDARD,
      ),
    )

    viewModel = MainActivityViewModel(repository)

    val result = mutableListOf<MainActivityUiState>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.uiState.toList(result)
    }

    advanceUntilIdle()

    val foundItem = result.last()
    assertNotNull(foundItem)
    when (foundItem) {
      MainActivityUiState.Loading -> {
        fail()
      }

      is MainActivityUiState.Success -> {
        assertTrue(foundItem.userData.isOnboarding)
        assertFalse(foundItem.userData.useDarkTheme)
        assertFalse(foundItem.userData.useDynamicColor)
        assertEquals(UserColorContrasts.STANDARD, foundItem.userData.colorContrast)
      }
    }

    job.cancel()
  }

  @Test
  fun `Should emit success result after onboarding completed`() = runTest {
    coEvery { repository.toggleBooleanSetting(any<String>(), any<Boolean>()) } returns Unit
    coEvery { repository.userPreferencesFlow } returns flowOf(
      UserSettings(
        isOnboarding = false,
        useDarkTheme = false,
        useDynamicColor = false,
        colorContrast = UserColorContrasts.STANDARD,
      ),
    )

    viewModel = MainActivityViewModel(repository)
    viewModel.setOnboardingComplete()

    val result = mutableListOf<MainActivityUiState>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.uiState.toList(result)
    }

    advanceUntilIdle()

    val foundItem = result.last()
    assertNotNull(foundItem)

    when (foundItem) {
      MainActivityUiState.Loading -> {
        fail()
      }

      is MainActivityUiState.Success -> {
        assertFalse(foundItem.userData.isOnboarding)
        assertFalse(foundItem.userData.useDarkTheme)
        assertFalse(foundItem.userData.useDynamicColor)
        assertEquals(UserColorContrasts.STANDARD, foundItem.userData.colorContrast)
      }
    }

    job.cancel()
  }
}
