/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.main

import dev.marlonlom.cappajv.core.preferences.entities.UserColorContrasts
import dev.marlonlom.cappajv.core.preferences.entities.UserSettings
import dev.marlonlom.cappajv.core.preferences.repository.UserPreferencesRepository
import dev.marlonlom.cappajv.tv.ui.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@OptIn(ExperimentalCoroutinesApi::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TvActivityViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val repository = mockk<UserPreferencesRepository>()
  private lateinit var viewModel: TvActivityViewModel

  @Test
  fun `Should return Starting uiState on init`() = runTest {
    viewModel = TvActivityViewModel(repository)
    assertEquals(TvActivityUiState.Starting, viewModel.uiState.value)
  }

  @Test
  fun `Should return Failed uiState on fetch exception`() = runTest {
    val exception = IllegalStateException("Failed to read flow")
    coEvery { repository.userPreferencesFlow } returns flow { throw exception }
    viewModel = TvActivityViewModel(repository)
    viewModel.onStarted()
    advanceUntilIdle()
    val expectedUiState = TvActivityUiState.Failed(exception)
    assertEquals(expectedUiState, viewModel.uiState.value)
    coVerify(exactly = 1) { repository.userPreferencesFlow }
  }

  @Test
  fun `Should return Loading then Ready uiStates on successful fetch`() = runTest {
    val expectedData = UserSettings(
      isOnboarding = true,
      useDarkTheme = false,
      useDynamicColor = false,
      colorContrast = UserColorContrasts.STANDARD,
    )
    val preferencesFlow = MutableSharedFlow<UserSettings>(replay = 1)
    coEvery { repository.userPreferencesFlow } returns preferencesFlow
    viewModel = TvActivityViewModel(repository)
    viewModel.onStarted()
    assertEquals(TvActivityUiState.Loading, viewModel.uiState.value)
    preferencesFlow.emit(expectedData)
    advanceUntilIdle()
    val expectedUiState = TvActivityUiState.Ready(expectedData)
    assertEquals(expectedUiState, viewModel.uiState.value)
  }

  @Test
  fun `Should return Ready uiStates when onboarding finished`() = runTest {
    val expectedData = UserSettings(
      isOnboarding = true,
      useDarkTheme = false,
      useDynamicColor = false,
      colorContrast = UserColorContrasts.STANDARD,
    )
    val preferencesFlow = MutableSharedFlow<UserSettings>(replay = 1)
    val keySlot = slot<String>()
    val valueSlot = slot<Boolean>()
    coEvery { repository.toggleBooleanSetting(capture(keySlot), capture(valueSlot)) } returns Unit
    coEvery { repository.userPreferencesFlow } returns preferencesFlow
    viewModel = TvActivityViewModel(repository)
    viewModel.onStarted()
    assertEquals(TvActivityUiState.Loading, viewModel.uiState.value)
    preferencesFlow.emit(expectedData)
    viewModel.onOnboarded()
    advanceUntilIdle()
    val expectedUiState = TvActivityUiState.Ready(expectedData)
    assertEquals(expectedUiState, viewModel.uiState.value)
    coVerify(exactly = 1) { repository.toggleBooleanSetting("is_onboarding", false) }
    assertEquals("is_onboarding", keySlot.captured)
    assertFalse(valueSlot.captured)
  }
}
