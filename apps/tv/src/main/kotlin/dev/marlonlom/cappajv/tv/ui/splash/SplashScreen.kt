/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.tv.material3.MaterialTheme
import dev.marlonlom.cappajv.tv.R
import kotlinx.coroutines.delay

/**
 * Displays the splash screen UI.
 *
 * @author marlonlom
 *
 * @param onTimeout Callback invoked when the splash screen timeout period is complete.
 */
@Composable
internal fun SplashScreen(onTimeout: () -> Unit) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    contentAlignment = Alignment.Center,
  ) {
    Image(
      painter = painterResource(R.drawable.img_splash),
      contentDescription = stringResource(R.string.text_splash_screen_image_cd),
    )
  }

  LaunchedEffect(key1 = true) {
    delay(2000)
    onTimeout()
  }
}
