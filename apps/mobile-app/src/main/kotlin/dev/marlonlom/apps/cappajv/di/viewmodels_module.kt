/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.di

import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailViewModel
import dev.marlonlom.apps.cappajv.features.catalog_list.CatalogListViewModel
import dev.marlonlom.apps.cappajv.features.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
  includes(dataModule)
  viewModelOf(::CatalogListViewModel)
  viewModelOf(::CatalogDetailViewModel)
  viewModelOf(::SettingsViewModel)
}
