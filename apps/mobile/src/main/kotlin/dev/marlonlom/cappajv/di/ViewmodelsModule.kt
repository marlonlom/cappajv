/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.di

import dev.marlonlom.cappajv.features.catalog_detail.CatalogDetailViewModel
import dev.marlonlom.cappajv.features.catalog_favorites.CatalogFavoritesViewModel
import dev.marlonlom.cappajv.features.catalog_list.CatalogListViewModel
import dev.marlonlom.cappajv.features.catalog_search.CatalogSearchViewModel
import dev.marlonlom.cappajv.features.settings.SettingsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val viewModelsModule = module {
  includes(dataModule)
  viewModel { CatalogListViewModel(get()) }
  viewModel { CatalogFavoritesViewModel(get()) }
  viewModel { CatalogSearchViewModel(get()) }
  viewModel { CatalogDetailViewModel(get()) }
  viewModel { SettingsViewModel(get()) }
}
