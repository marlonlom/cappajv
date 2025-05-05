/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.di

import dev.marlonlom.cappajv.core.database.datasource.LocalDataSource
import dev.marlonlom.cappajv.domain.catalog.detail.CatalogDetailRepository
import dev.marlonlom.cappajv.mobile.catalog.detail.domain.CatalogDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Koin module providing dependencies related to the Catalog Detail screen.
 *
 * @author marlonlom
 */
@OptIn(ExperimentalCoroutinesApi::class)
val catalogDetailUiModule = module {
  single<CatalogDetailRepository> {
    CatalogDetailRepository(
      get<LocalDataSource>(),
    )
  } bind CatalogDetailRepository::class

  viewModel {
    CatalogDetailViewModel(
      get<CatalogDetailRepository>(),
    )
  } bind CatalogDetailViewModel::class
}
