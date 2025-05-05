/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.settings.di

import dev.marlonlom.cappajv.core.preferences.repository.UserPreferencesRepository
import dev.marlonlom.cappajv.mobile.settings.domain.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Koin module providing dependencies related to the Mobile app settings screen.
 *
 * @author marlonlom
 */
val settingsUiModule = module {
  viewModel {
    SettingsViewModel(
      get<UserPreferencesRepository>(),
    )
  } bind SettingsViewModel::class
}
