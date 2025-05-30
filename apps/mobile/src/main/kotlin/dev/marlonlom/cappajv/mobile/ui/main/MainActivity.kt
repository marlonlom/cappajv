/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * The main entry point of the Cappajv android mobile application.
 *
 * This activity is responsible for setting up the Compose UI content and managing
 * app-wide configurations such as theming, state, and navigation.
 *
 * It extends [ComponentActivity], integrating Jetpack Compose into the activity lifecycle.
 *
 * @author marlonlom
 */
class MainActivity : ComponentActivity() {

  private val viewModel: MainActivityViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val splashScreen = installSplashScreen()

    var mainActivityUiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

    lifecycleScope.launch {
      lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.uiState.onEach { mainActivityUiState = it }.collect()
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
      MainContent(
        uiState = mainActivityUiState,
        onOnboardingComplete = viewModel::setOnboardingComplete,
      )
    }
  }
}
