/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.ui.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.apps.cappajv.core.preferences.UserPreferencesRepository
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailViewModel
import dev.marlonlom.apps.cappajv.features.catalog_list.CatalogListViewModel
import dev.marlonlom.apps.cappajv.ui.main.scaffold.MainScaffold
import dev.marlonlom.apps.cappajv.ui.theme.CappajvTheme
import dev.marlonlom.apps.cappajv.ui.util.DevicePosture
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Returns true/false if dynamic colors are applied to the ui.
 *
 * @param mainActivityUiState Main activity ui state.
 * @return true/false
 */
@Composable
private fun shouldUseDynamicColor(
  mainActivityUiState: MainActivityUiState
): Boolean = when (mainActivityUiState) {
  MainActivityUiState.Loading -> false
  is MainActivityUiState.Success -> mainActivityUiState.userData.useDynamicColor
}

/**
 * Returns true/false if dark theme is applied to the ui.
 *
 * @param mainActivityUiState Main activity ui state.
 * @return true/false
 */
@Composable
private fun shouldUseDarkTheme(
  mainActivityUiState: MainActivityUiState
): Boolean = when (mainActivityUiState) {
  MainActivityUiState.Loading -> isSystemInDarkTheme()
  is MainActivityUiState.Success -> {
    val useDarkTheme = mainActivityUiState.userData.useDarkTheme
    if (useDarkTheme.not()) isSystemInDarkTheme() else useDarkTheme
  }
}

/**
 * Application main content composable ui.
 *
 * @author marlonlom
 *
 * @param mainActivityUiState Main activity ui state.
 * @param windowSizeClass Window size class.
 * @param userPreferencesRepository User preferences repository.
 * @param catalogListViewModel Catalog list viewmodel.
 * @param catalogDetailViewModel Catalog detail viewmodel.
 */
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalCoroutinesApi
@Composable
fun AppContent(
  mainActivityUiState: MainActivityUiState,
  windowSizeClass: WindowSizeClass,
  devicePosture: DevicePosture,
  userPreferencesRepository: UserPreferencesRepository,
  catalogListViewModel: CatalogListViewModel,
  catalogDetailViewModel: CatalogDetailViewModel,
  onOnboardingComplete: () -> Unit,
) = CappajvTheme(
  darkTheme = shouldUseDarkTheme(mainActivityUiState),
  dynamicColor = false//shouldUseDynamicColor(mainActivityUiState)
) {

  val catalogListState by catalogListViewModel.uiState.collectAsStateWithLifecycle()

  val appContentCallbacks = newAppContentCallbacks(
    activityContext = LocalContext.current,
    catalogDetailViewModel = catalogDetailViewModel
  )

  MainScaffold(
    mainActivityUiState = mainActivityUiState,
    windowSizeClass = windowSizeClass,
    devicePosture = devicePosture,
    appContentCallbacks = appContentCallbacks,
    userPreferencesRepository = userPreferencesRepository,
    onOnboardingComplete = onOnboardingComplete,
    catalogListState = catalogListState
  )
}
