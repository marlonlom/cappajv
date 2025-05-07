/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.di

import dev.marlonlom.cappajv.core.catalog.CatalogDataService
import dev.marlonlom.cappajv.core.database.di.databaseKoinModule
import dev.marlonlom.cappajv.core.preferences.di.preferencesKoinModule
import dev.marlonlom.cappajv.tv.features.catalog.details.CatalogDetailRepository
import dev.marlonlom.cappajv.tv.features.catalog.details.CatalogDetailViewModel
import dev.marlonlom.cappajv.tv.features.catalog.favorites.CatalogFavoritesRepository
import dev.marlonlom.cappajv.tv.features.catalog.favorites.CatalogFavoritesViewModel
import dev.marlonlom.cappajv.tv.features.catalog.home.CatalogHomeRepository
import dev.marlonlom.cappajv.tv.features.catalog.home.CatalogHomeViewModel
import dev.marlonlom.cappajv.tv.ui.main.TvActivityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
@OptIn(ExperimentalCoroutinesApi::class)
val tvAppModule = module {
  single<CatalogDataService> { CatalogDataService(Locale.getDefault().language) } bind CatalogDataService::class

  includes(databaseKoinModule, preferencesKoinModule)

  /* Repositories */
  single<CatalogHomeRepository> {
    CatalogHomeRepository(
      localDataSource = get(),
      catalogDataService = get(),
    )
  }
  single<CatalogFavoritesRepository> {
    CatalogFavoritesRepository(
      localDataSource = get(),
    )
  }
  single<CatalogDetailRepository> {
    CatalogDetailRepository(
      localDataSource = get(),
    )
  }
  /* View models */
  viewModelOf(::CatalogHomeViewModel)
  viewModelOf(::CatalogFavoritesViewModel)
  viewModelOf(::CatalogDetailViewModel)
  viewModelOf(::TvActivityViewModel)
}
