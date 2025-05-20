/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.cappajv.tv.designsystem.theme.CappajvTvTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * The main entry point of the Cappajv tv mobile application.
 *
 * @author marlonlom
 */
class TvActivity : ComponentActivity() {

  private val viewModel: TvActivityViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      CappajvTvTheme {
        KoinAndroidContext {
          val uiState by viewModel.uiState.collectAsStateWithLifecycle()
          TvActivityContent(uiState, viewModel::onStarted, viewModel::onOnboarded)
        }
      }
    }
  }
}
