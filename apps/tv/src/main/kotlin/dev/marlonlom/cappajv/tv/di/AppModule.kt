/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.di

import dev.marlonlom.cappajv.core.catalog.CatalogDataService
import dev.marlonlom.cappajv.core.database.CappaDatabase
import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.core.database.datasource.LocalDataSourceImpl
import dev.marlonlom.cappajv.tv.features.catalog.details.CatalogDetailRepository
import dev.marlonlom.cappajv.tv.features.catalog.details.CatalogDetailViewModel
import dev.marlonlom.cappajv.tv.features.catalog.favorites.CatalogFavoritesRepository
import dev.marlonlom.cappajv.tv.features.catalog.favorites.CatalogFavoritesViewModel
import dev.marlonlom.cappajv.tv.features.catalog.home.CatalogHomeRepository
import dev.marlonlom.cappajv.tv.features.catalog.home.CatalogHomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import java.util.Locale

/**
 * Koin android tv application module definition object.
 *
 * @author marlonlom
 *
 */
@OptIn(ExperimentalCoroutinesApi::class)
val appModule = module {
  /* Data sources (local, remote) */
  single<LocalDataSource> {
    CappaDatabase.getInstance(androidContext()).let { db ->
      LocalDataSourceImpl(
        catalogItemsDao = db.catalogProductsDao(),
        catalogPunctuationsDao = db.catalogPunctuationsDao(),
        catalogFavoriteItemsDao = db.catalogFavoriteItemsDao(),
        catalogSearchDao = db.catalogSearchDao(),
      )
    }
  }
  single<CatalogDataService> {
    CatalogDataService(Locale.getDefault().language)
  }
  /* Repositories */
  single<CatalogHomeRepository> {
    CatalogHomeRepository(
      localDataSource = get(),
      catalogDataService = get(),
    )
  }
  single<CatalogFavoritesRepository> {
    CatalogFavoritesRepository(
      localDataSource = get()
    )
  }
  single<CatalogDetailRepository> {
    CatalogDetailRepository(
      localDataSource = get()
    )
  }
  /* View models */
  viewModelOf(::CatalogHomeViewModel)
  viewModelOf(::CatalogFavoritesViewModel)
  viewModelOf(::CatalogDetailViewModel)
}
