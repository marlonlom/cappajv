/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.tv.material3.MaterialTheme

/**
 * Brand theme composable function for Cappajv app.
 *
 * @author marlonlom
 *
 * @param isInDarkTheme True/False if dark theme is applied.
 * @param content Composable ui contents.
 */
@Composable
fun CappajvTvTheme(isInDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) = MaterialTheme(
  colorScheme = if (isInDarkTheme) CappajvColorSchemes.STANDARD.dark else CappajvColorSchemes.STANDARD.light,
  typography = CappajvFont.appTypography,
  shapes = CappajvShapes.shapes,
  content = content,
)
