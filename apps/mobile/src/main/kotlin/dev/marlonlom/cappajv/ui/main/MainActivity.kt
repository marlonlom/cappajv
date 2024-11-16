/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.ui.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import dev.marlonlom.cappajv.ui.layout.DevicePosture
import dev.marlonlom.cappajv.ui.layout.DevicePostureDetector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.contracts.ExperimentalContracts

/**
 * Main activity class.
 *
 * @author marlonlom
 */
@ExperimentalContracts
@ExperimentalFoundationApi
@ExperimentalLayoutApi
@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalMaterial3WindowSizeClassApi
class MainActivity : ComponentActivity() {

  private val mainActivityViewModel: MainActivityViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val splashScreen = installSplashScreen()

    var mainActivityUiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

    lifecycleScope.launch {
      lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        mainActivityViewModel.uiState.onEach { mainActivityUiState = it }.collect()
      }
    }

    splashScreen.setKeepOnScreenCondition {
      when (mainActivityUiState) {
        MainActivityUiState.Loading -> true
        is MainActivityUiState.Success -> false
      }
    }

    enableEdgeToEdge()

    val devicePostureFlow = WindowInfoTracker
      .getOrCreate(this@MainActivity)
      .windowLayoutInfo(this@MainActivity)
      .flowWithLifecycle(lifecycle)
      .map { layoutInfo ->
        val foldingFeature = layoutInfo.displayFeatures
          .filterIsInstance<FoldingFeature>()
          .firstOrNull()
        DevicePostureDetector.fromLayoutInfo(foldingFeature)
      }.stateIn(
        scope = lifecycleScope,
        started = SharingStarted.Eagerly,
        initialValue = DevicePosture.Normal,
      )

    setContent {
      val devicePosture by devicePostureFlow.collectAsStateWithLifecycle(
        lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current,
      )
      val appState = rememberCappajvAppState(
        windowSizeClass = calculateWindowSizeClass(this),
        devicePosture = devicePosture,
        screenWidthDp = LocalConfiguration.current.smallestScreenWidthDp,
        isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE,
      )

      AppContent(
        mainActivityUiState = mainActivityUiState,
        appUiState = appState,
        appContentCallbacks = newAppContentCallbacks(applicationContext),
        onOnboardingComplete = mainActivityViewModel::setOnboardingComplete,
      )
    }
  }
}
