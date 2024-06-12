/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.welcome.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.R

/**
 * Poster image for welcome screen composable ui.
 *
 * @author marlonlom
 *
 */
@Composable
internal fun WelcomePosterImage() {
  Image(
    painter = painterResource(id = R.drawable.img_welcome_poster),
    contentDescription = null,
    modifier = Modifier
      .height(200.dp),
    contentScale = ContentScale.Crop
  )
}
