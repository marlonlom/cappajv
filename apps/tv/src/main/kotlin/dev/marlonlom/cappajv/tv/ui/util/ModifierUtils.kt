/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.tv.ui.util

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Extension function that returns the safe content padding for screen composable uis.
 *
 * @author marlonlom
 *
 */
fun Modifier.tvSafeContentPadding() = padding(
  start = 60.dp,
  end = 60.dp,
  top = 30.dp,
  bottom = 0.dp
)

/**
 * Extension function that returns the safe content padding for screen composable uis.
 *
 * @author marlonlom
 *
 * @param startPadding Start padding value in DP.
 */
fun Modifier.tvSafeContentPadding(startPadding: Dp) = padding(
  start = startPadding,
  end = 60.dp,
  top = 30.dp,
  bottom = 0.dp
)
