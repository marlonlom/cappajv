/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.home.di

import dev.marlonlom.cappajv.core.catalog.CatalogDataService
import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.domain.catalog.home.CatalogHomeRepository
import dev.marlonlom.cappajv.mobile.catalog.home.domain.CatalogHomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Koin module providing dependencies related to the Catalog Home screen.
 *
 * @author marlonlom
 */
val catalogHomeUiModule = module {
  single<CatalogHomeRepository> {
    CatalogHomeRepository(
      localDataSource = get<LocalDataSource>(),
      catalogDataService = get<CatalogDataService>(),
    )
  } bind CatalogHomeRepository::class

  viewModel {
    CatalogHomeViewModel(get())
  } bind CatalogHomeViewModel::class
}
