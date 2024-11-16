/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.di

import dev.marlonlom.cappajv.ui.main.MainActivityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainActivityModule = module {
  viewModelOf(::MainActivityViewModel)
}

@OptIn(ExperimentalCoroutinesApi::class)
val appModule = module {
  includes(viewModelsModule)
  includes(mainActivityModule)
}
