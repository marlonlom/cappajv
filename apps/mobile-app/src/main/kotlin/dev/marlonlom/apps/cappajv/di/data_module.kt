/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.di

import dev.marlonlom.apps.cappajv.core.catalog_source.CatalogDataService
import dev.marlonlom.apps.cappajv.core.database.CappaDatabase
import dev.marlonlom.apps.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.apps.cappajv.core.database.datasource.LocalDataSourceImpl
import dev.marlonlom.apps.cappajv.core.preferences.UserPreferencesRepository
import dev.marlonlom.apps.cappajv.dataStore
import dev.marlonlom.apps.cappajv.features.catalog_list.CatalogListRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
  single<LocalDataSource> {
    CappaDatabase.getInstance(androidContext()).let { db ->
      LocalDataSourceImpl(
        catalogItemsDao = db.catalogProductsDao(),
        catalogPunctuationsDao = db.catalogPunctuationsDao(),
        catalogFavoriteItemsDao = db.catalogFavoriteItemsDao()
      )
    }
  }
  single<CatalogDataService> {
    CatalogDataService()
  }
  single<CatalogListRepository> {
    CatalogListRepository(
      localDataSource = get(),
      catalogDataService = get()
    )
  }
  single {
    UserPreferencesRepository(androidContext().dataStore)
  }
}