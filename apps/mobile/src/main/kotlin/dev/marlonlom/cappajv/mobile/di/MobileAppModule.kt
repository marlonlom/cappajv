/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.di

import dev.marlonlom.cappajv.core.catalog.CatalogDataService
import dev.marlonlom.cappajv.core.database.di.databaseKoinModule
import dev.marlonlom.cappajv.core.preferences.di.preferencesKoinModule
import dev.marlonlom.cappajv.mobile.catalog.detail.di.catalogDetailUiModule
import dev.marlonlom.cappajv.mobile.catalog.favorites.di.catalogFavoritesUiModule
import dev.marlonlom.cappajv.mobile.catalog.home.di.catalogHomeUiModule
import dev.marlonlom.cappajv.mobile.settings.di.settingsUiModule
import dev.marlonlom.cappajv.mobile.ui.main.MainActivityViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import java.util.Locale

/**
 * Koin module providing dependencies related to the mobile app module.
 *
 * @author marlonlom
 */
val mobileAppModule = module {
  single<CatalogDataService> { CatalogDataService(Locale.getDefault().language) } bind CatalogDataService::class

  includes(databaseKoinModule, preferencesKoinModule)
  includes(catalogHomeUiModule, catalogFavoritesUiModule, catalogDetailUiModule, settingsUiModule)

  viewModelOf(::MainActivityViewModel)
}
