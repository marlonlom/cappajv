/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.di

import dev.marlonlom.cappajv.core.catalog_source.CatalogDataService
import dev.marlonlom.cappajv.core.database.CappaDatabase
import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.core.database.datasource.LocalDataSourceImpl
import dev.marlonlom.cappajv.core.preferences.repository.UserPreferencesRepository
import dev.marlonlom.cappajv.dataStore
import dev.marlonlom.cappajv.features.catalog_detail.CatalogDetailRepository
import dev.marlonlom.cappajv.features.catalog_favorites.CatalogFavoritesRepository
import dev.marlonlom.cappajv.features.catalog_list.CatalogListRepository
import dev.marlonlom.cappajv.features.catalog_search.CatalogSearchRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.util.Locale

val dataModule = module {
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
  single<CatalogListRepository> {
    CatalogListRepository(
      localDataSource = get(),
      catalogDataService = get(),
    )
  }
  single<CatalogFavoritesRepository> {
    CatalogFavoritesRepository(
      localDataSource = get(),
    )
  }
  single<CatalogSearchRepository> {
    CatalogSearchRepository(
      localDataSource = get(),
    )
  }
  single<CatalogDetailRepository> {
    CatalogDetailRepository(
      localDataSource = get(),
    )
  }
  single {
    UserPreferencesRepository(androidContext().dataStore)
  }
}
