/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.di

import dev.marlonlom.apps.cappajv.ui.main.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val mainActivityModule = module {
  viewModelOf(::MainActivityViewModel)
}

val appModule = module {
  includes(viewModelsModule)
  includes(mainActivityModule)
}
