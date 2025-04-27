/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.favorites.di

import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.mobile.catalog.favorites.domain.CatalogFavoritesRepository
import dev.marlonlom.cappajv.mobile.catalog.favorites.domain.CatalogFavoritesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Koin module providing dependencies related to the Catalog Favorites screen.
 *
 * @author marlonlom
 */
val catalogFavoritesUiModule = module {
  single<CatalogFavoritesRepository> {
    CatalogFavoritesRepository(
      localDataSource = get<LocalDataSource>(),
    )
  } bind CatalogFavoritesRepository::class

  viewModel {
    CatalogFavoritesViewModel(get())
  } bind CatalogFavoritesViewModel::class
}
