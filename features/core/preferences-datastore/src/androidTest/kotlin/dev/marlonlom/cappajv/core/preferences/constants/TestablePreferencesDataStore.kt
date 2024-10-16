/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.core.preferences.constants

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.CoroutineScope

/**
 * Testable preferences datastore singleton object.
 *
 * @author marlonlom
 *
 */
object TestablePreferencesDataStore {
  private var instance: DataStore<Preferences>? = null

  /**
   * Returns a single preferences datastore instance for ui testing.
   *
   * @param testCoroutineScope Coroutine scope for ui testing.
   * @return Single preferences datastore instance.
   */
  fun getInstance(testCoroutineScope: CoroutineScope): DataStore<Preferences> {
    return instance ?: synchronized(this) {
      instance ?: PreferenceDataStoreFactory.create(
        scope = testCoroutineScope,
        produceFile = {
          val testContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
          testContext.preferencesDataStoreFile(name = "test_cappajv_settings")
        }
      ).also { instance = it }
    }
  }
}
