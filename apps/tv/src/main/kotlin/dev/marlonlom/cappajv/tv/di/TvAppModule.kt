/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.di

import dev.marlonlom.cappajv.core.catalog.CatalogDataService
import dev.marlonlom.cappajv.core.database.di.databaseKoinModule
import dev.marlonlom.cappajv.core.preferences.di.preferencesKoinModule
import dev.marlonlom.cappajv.tv.catalog.detail.di.catalogDetailTvUiModule
import dev.marlonlom.cappajv.tv.catalog.favorites.di.catalogFavoritesTvUiModule
import dev.marlonlom.cappajv.tv.catalog.home.di.catalogHomeTvUiModule
import dev.marlonlom.cappajv.tv.ui.main.TvActivityViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import java.util.Locale

/**
 * Koin module providing dependencies related to the tv app module.
 *
 * @author marlonlom
 *
 */
val tvAppModule = module {
  single<CatalogDataService> { CatalogDataService(Locale.getDefault().language) } bind CatalogDataService::class

  includes(databaseKoinModule, preferencesKoinModule)
  includes(catalogHomeTvUiModule, catalogFavoritesTvUiModule, catalogDetailTvUiModule)

  viewModelOf(::TvActivityViewModel)
}
