/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.core.preferences.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dev.marlonlom.cappajv.core.preferences.repository.UserPreferencesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Instance of preferences datastore.
 *
 * @author marlonlom
 */
internal val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "cappajv-settings")

/**
 * Preferences Koin module.
 *
 * @author marlonlom
 */
val preferencesKoinModule = module {
  single<UserPreferencesRepository> {
    UserPreferencesRepository(
      androidContext().dataStore,
    )
  } bind UserPreferencesRepository::class
}
