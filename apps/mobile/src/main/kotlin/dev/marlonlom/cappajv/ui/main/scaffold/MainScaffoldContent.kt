/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.ui.main.scaffold

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
import dev.marlonlom.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.cappajv.ui.main.CappajvAppState
import dev.marlonlom.cappajv.ui.navigation.MainNavHost
import dev.marlonlom.cappajv.ui.navigation.NavigationType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber


/**
 * Main scaffold content composable ui.
 *
 * @author marlonlom
 *
 * @param paddingValues Padding values.
 * @param appState Application ui state.
 * @param appContentCallbacks Application content callbacks.
 * @param isTopDestination True/False if current destination is home, favorites or search.
 * @param selectedPosition
 * @param onSelectedPositionChanged
 */
@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
internal fun MainScaffoldContent(
  paddingValues: PaddingValues,
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  isTopDestination: Boolean,
  selectedPosition: Int,
  onSelectedPositionChanged: (Int, String) -> Unit
) {
  Timber.d(
    """
    [MainScaffoldContent]
     scaffoldContentType=${appState.scaffoldContentType}
     devicePosture=${appState.devicePosture}
     isLandscape=${appState.isLandscape}
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
          isTopDestination = isTopDestination,
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
          if (isTopDestination) {
            MainNavigationRail(
              selectedPosition = selectedPosition,
              onSelectedPositionChanged = onSelectedPositionChanged,
            )
          }

          MainNavHost(
            appState = appState,
            appContentCallbacks = appContentCallbacks,
          )
        }
      }

      NavigationType.BOTTOM_NAV -> {
        MainNavHost(
          appState = appState,
          appContentCallbacks = appContentCallbacks,
        )
      }
    }
  }
}
