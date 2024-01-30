/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dev.marlonlom.apps.cappajv.core.catalog_source.CatalogDataService
import dev.marlonlom.apps.cappajv.core.database.CappaDatabase
import dev.marlonlom.apps.cappajv.core.database.LocalDataSource
import dev.marlonlom.apps.cappajv.core.database.LocalDataSourceImpl
import dev.marlonlom.apps.cappajv.core.preferences.UserPreferencesRepository
import dev.marlonlom.apps.cappajv.dataStore
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailRepository
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailViewModel
import dev.marlonlom.apps.cappajv.features.catalog_list.CatalogListRepository
import dev.marlonlom.apps.cappajv.features.catalog_list.CatalogListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Main activity class.
 *
 * @author marlonlom
 */
@ExperimentalLayoutApi
@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalMaterial3WindowSizeClassApi
class MainActivity : ComponentActivity() {

  private val newUserPreferencesRepository: UserPreferencesRepository get() = UserPreferencesRepository(dataStore)

  private val mainActivityViewModel: MainActivityViewModel by viewModels(
    factoryProducer = {
      MainActivityViewModel.factory(newUserPreferencesRepository)
    })

  private val catalogListViewModel: CatalogListViewModel by viewModels(
    factoryProducer = {
      CatalogListViewModel.factory(CatalogListRepository(
        localDataSource = newLocalDataSource(),
        catalogDataService = CatalogDataService()
      ))
    })

  private val catalogDetailViewModel: CatalogDetailViewModel by viewModels(
    factoryProducer = {
      CatalogDetailViewModel.factory(CatalogDetailRepository(newLocalDataSource()))
    })

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val splashScreen = installSplashScreen()

    var mainActivityUiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

    lifecycleScope.launch {
      lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        mainActivityViewModel.uiState
          .onEach { mainActivityUiState = it }
          .collect()
      }
    }

    splashScreen.setKeepOnScreenCondition {
      when (mainActivityUiState) {
        MainActivityUiState.Loading -> true
        is MainActivityUiState.Success -> false
      }
    }

    enableEdgeToEdge()

    setContent {
      val windowSizeClass = calculateWindowSizeClass(this)

      AppContent(
        mainActivityUiState = mainActivityUiState,
        windowSizeClass = windowSizeClass,
        userPreferencesRepository = newUserPreferencesRepository,
        catalogListViewModel = catalogListViewModel,
        catalogDetailViewModel = catalogDetailViewModel,
        onOnboardingComplete = {
          mainActivityViewModel.setOnboardingComplete()
        },
      )
    }
  }

  private fun newLocalDataSource(): LocalDataSource {
    val cappaDatabase = CappaDatabase.getInstance(applicationContext)
    return LocalDataSourceImpl(
      catalogProductsDao = cappaDatabase.catalogProductsDao(),
      catalogPunctuationsDao = cappaDatabase.catalogPunctuationsDao()
    )
  }

}
