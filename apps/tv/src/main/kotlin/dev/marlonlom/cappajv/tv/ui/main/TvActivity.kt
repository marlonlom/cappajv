/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dev.marlonlom.cappajv.tv.R
import dev.marlonlom.cappajv.tv.designsystem.theme.CappajvTvTheme
import dev.marlonlom.cappajv.tv.onboarding.OnboardingScreen
import dev.marlonlom.cappajv.tv.ui.main.TvActivityUiState.Loading
import dev.marlonlom.cappajv.tv.ui.main.TvActivityUiState.Success
import dev.marlonlom.cappajv.tv.ui.navigation.TvScaffold
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * An Activity specifically designed for TV devices.
 *
 * @author marlonlom
 *
 */
class TvActivity : ComponentActivity() {

  private val viewModel: TvActivityViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    var uiState by mutableStateOf<TvActivityUiState>(Loading)

    lifecycleScope.launch {
      lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.uiState.onEach { uiState = it }.collect()
      }
    }

    setContent {
      CappajvTvTheme {
        KoinAndroidContext {
          when (uiState) {
            Loading -> Unit
            is Success -> {
              val (userData) = uiState as Success
              if (userData.isOnboarding) {
                OnboardingScreen(
                  brandImage = R.drawable.img_logo,
                  onOnboardingComplete = viewModel::setOnboardingComplete,
                )
              } else {
                TvScaffold()
              }
            }
          }
        }
      }
    }
  }
}
