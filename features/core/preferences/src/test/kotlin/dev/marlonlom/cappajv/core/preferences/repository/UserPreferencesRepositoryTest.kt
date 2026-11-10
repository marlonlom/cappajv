/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.core.preferences.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import dev.marlonlom.cappajv.core.preferences.entities.UserColorContrasts
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import java.io.File
import java.io.IOException

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ExperimentalCoroutinesApi
internal class UserPreferencesRepositoryTest {

  private val testDispatcher = UnconfinedTestDispatcher()
  private val testScope = TestScope(testDispatcher)
  private lateinit var testFile: File
  private lateinit var dataStore: DataStore<Preferences>
  private lateinit var repository: UserPreferencesRepository

  @Before
  fun setUp() {
    testFile = File.createTempFile("test_preferences", ".preferences_pb")
    dataStore = PreferenceDataStoreFactory.create(
      scope = testScope,
      produceFile = { testFile },
    )
    repository = UserPreferencesRepository(dataStore)
  }

  @After
  fun tearDown() {
    testFile.delete()
    testScope.cancel()
  }

  @Test
  fun `Should return settings as flow with default values`() = testScope.runTest {
    val settings = repository.userPreferencesFlow.first()
    assertNotNull(settings)
    assertFalse(settings.useDarkTheme)
    assertTrue(settings.useDynamicColor)
    assertTrue(settings.isOnboarding)
    assertEquals(UserColorContrasts.STANDARD, settings.colorContrast)
  }

  @Test
  fun `Should emit empty preferences when IOException occurs in data flow`() = testScope.runTest {
    val mockDataStore = mockk<DataStore<Preferences>>()
    every { mockDataStore.data } returns flow {
      throw IOException("DataStore read error")
    }
    val mockRepo = UserPreferencesRepository(mockDataStore)
    val settings = mockRepo.userPreferencesFlow.first()
    assertNotNull(settings)
    assertFalse(settings.useDarkTheme)
    assertTrue(settings.useDynamicColor)
    assertTrue(settings.isOnboarding)
    assertEquals(UserColorContrasts.STANDARD, settings.colorContrast)
  }

  @Test(expected = RuntimeException::class)
  fun `Should rethrow non-IOException in data flow`() = testScope.runTest {
    val mockDataStore = mockk<DataStore<Preferences>>()
    every { mockDataStore.data } returns flow {
      throw RuntimeException("Unexpected error")
    }
    val mockRepo = UserPreferencesRepository(mockDataStore)
    mockRepo.userPreferencesFlow.first()
  }

  @Test
  fun `Should toggle boolean setting successfully`() = testScope.runTest {
    repository.toggleBooleanSetting("dark_theme", true)
    val settings = repository.userPreferencesFlow.first()
    assertTrue(settings.useDarkTheme)
  }

  @Test
  fun `Should not toggle boolean setting when key is invalid`() = testScope.runTest {
    repository.toggleBooleanSetting("invalid_key", true)
    val settings = repository.userPreferencesFlow.first()
    assertFalse(settings.useDarkTheme)
    assertTrue(settings.useDynamicColor)
  }

  @Test
  fun `Should update string setting successfully`() = testScope.runTest {
    repository.updateStringSetting("color_contrast", UserColorContrasts.MEDIUM.name)
    val settings = repository.userPreferencesFlow.first()
    assertEquals(UserColorContrasts.MEDIUM, settings.colorContrast)
  }

  @Test
  fun `Should not update string setting when key is invalid`() = testScope.runTest {
    repository.updateStringSetting("invalid_key", "some_value")
    val settings = repository.userPreferencesFlow.first()
    assertEquals(UserColorContrasts.STANDARD, settings.colorContrast)
  }
}
