/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.ui.main.scaffold

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import dev.marlonlom.apps.cappajv.ui.navigation.MainNavHost
import dev.marlonlom.apps.cappajv.ui.navigation.NavigationType
import timber.log.Timber


/**
 * Main scaffold content composable ui.
 *
 * @author marlonlom
 *
 * @param paddingValues Padding values.
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 * @param selectedPosition
 * @param onSelectedPositionChanged
 */
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
internal fun MainScaffoldContent(
  paddingValues: PaddingValues,
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  selectedPosition: Int,
  onSelectedPositionChanged: (Int, String) -> Unit
) {
  Timber.d(
    """
    [MainScaffoldContent]
     scaffoldContentType=${appState.scaffoldContentType}
     devicePosture=${appState.devicePosture}
  """.trimIndent()
  )
  Box(
    modifier = Modifier
        .safeDrawingPadding()
        .padding(paddingValues),
    contentAlignment = Alignment.Center,
  ) {
    when (appState.navigationType) {
      NavigationType.EXPANDED_NAV -> {
        ExpandedNavigationDrawer(
          selectedPosition = selectedPosition,
          onSelectedPositionChanged = onSelectedPositionChanged,
        ) {
          MainNavHost(
            appState = appState, appContentCallbacks
          )
        }
      }

      NavigationType.NAVIGATION_RAIL -> {
        Row {
          MainNavigationRail(
            selectedPosition = selectedPosition,
            onSelectedPositionChanged = onSelectedPositionChanged,
          )

          MainNavHost(
            appState = appState, appContentCallbacks
          )
        }
      }

      NavigationType.BOTTOM_NAV -> {
        MainNavHost(
          appState = appState, appContentCallbacks
        )
      }
    }
  }
}
