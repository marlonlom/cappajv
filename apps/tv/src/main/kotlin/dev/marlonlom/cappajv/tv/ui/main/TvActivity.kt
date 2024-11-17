/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.marlonlom.cappajv.tv.ui.browse.TvBrowseScreen
import dev.marlonlom.cappajv.tv.ui.theme.CappajvTvTheme

/**
 * Main TV Activity class.
 *
 * @author marlonlom
 *
 */
class TvActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CappajvTvTheme(isInDarkTheme = false) {
        TvBrowseScreen()
      }
    }
  }
}
