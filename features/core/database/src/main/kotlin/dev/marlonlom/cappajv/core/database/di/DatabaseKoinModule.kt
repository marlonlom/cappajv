/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.core.database.di

import dev.marlonlom.cappajv.core.database.CappaDatabase
import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.core.database.datasource.LocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Database Koin module.
 *
 * @author marlonlom
 */
val databaseKoinModule = module {
  single<LocalDataSource> {
    CappaDatabase.getInstance(androidContext()).let { db ->
      LocalDataSourceImpl(
        catalogItemsDao = db.catalogProductsDao(),
        catalogPunctuationsDao = db.catalogPunctuationsDao(),
        catalogFavoriteItemsDao = db.catalogFavoriteItemsDao(),
        catalogSearchDao = db.catalogSearchDao(),
      )
    }
  } bind LocalDataSource::class
}
