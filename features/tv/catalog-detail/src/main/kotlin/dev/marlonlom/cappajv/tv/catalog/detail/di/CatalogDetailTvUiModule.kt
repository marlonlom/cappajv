/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.catalog.detail.di

import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.domain.catalog.detail.CatalogDetailRepository
import dev.marlonlom.cappajv.tv.catalog.detail.domain.CatalogDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Koin module providing dependencies related to the Catalog Detail TV screen.
 *
 * @author marlonlom
 */
val catalogDetailTvUiModule = module {
  single<CatalogDetailRepository> {
    CatalogDetailRepository(
      localDataSource = get<LocalDataSource>(),
    )
  } bind CatalogDetailRepository::class

  viewModel {
    CatalogDetailViewModel(get())
  } bind CatalogDetailViewModel::class
}
